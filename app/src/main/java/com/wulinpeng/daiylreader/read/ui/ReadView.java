package com.wulinpeng.daiylreader.read.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Scroller;
import android.widget.Toast;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.read.event.OnChapterLoadEvent;
import com.wulinpeng.daiylreader.read.event.RecycleBitmapEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author wulinpeng
 * @datetime: 17/2/8 下午6:37
 * @description:
 */
public class ReadView extends View {

    private int mWidth;
    private int mHeight;
    private int mCornerX = 0;
    private int mCornerY = 0;
    private Path mPath1;
    private Path mPath2;
    Bitmap mCurrentBitmap;
    Bitmap mNextBitmap;
    Canvas mCurrentCanvas;
    Canvas mNextCanvas;

    Bitmap mBackgroundBitmap;

    Context mContext;

    PointF mTouch = new PointF();
    PointF mBzStart1 = new PointF();
    PointF mBzControl1 = new PointF();
    PointF mBzVertex1 = new PointF();
    PointF mBzEnd1 = new PointF();
    PointF mBzStart2 = new PointF();
    PointF mBzControl2 = new PointF();
    PointF mBzVertex2 = new PointF();
    PointF mBzEnd2 = new PointF();

    float mMiddleX;
    float mMiddleY;
    float mDegress;
    float mTouchCornerDis;
    ColorMatrixColorFilter colorMatrixColorFilter;
    Matrix matrix;

    float[] matrixArr = { 0, 0, 0, 0, 0, 0, 0, 0, 1.0f };
    boolean mIsRtOrLb;
    float mMaxLength;
    int[] mBackShadowColors;
    int[] mFrontShadowColors;
    GradientDrawable mBackDrawableLR;
    GradientDrawable mBackDrawableRL;
    GradientDrawable mFolderDrawableLR;
    GradientDrawable mFolderDrawableRL;

    GradientDrawable mFrontDrawble_h_bt;
    GradientDrawable mFrontDrawable_h_tb;
    GradientDrawable mFrontDrawable_v_lr;
    GradientDrawable mFrontDrawable_v_rl;

    Paint mPaint;

    Scroller mScroller;

    BookFactory mBookFactory;

    /**
     * 在等待异步数据
     */
    boolean mIsAsyn = false;

    public ReadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        EventBus.getDefault().register(this);

        mPath1 = new Path();
        mPath2 = new Path();
        createDrawable();
        // 取消硬件加速，避免clipPath失效
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        getWidthAndHeight();

        // 两个canvas绑定bitmap，通过对canvas的绘制来直接改变两个bitmap
        mCurrentBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mCurrentCanvas = new Canvas(mCurrentBitmap);
        mNextBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mNextCanvas = new Canvas(mNextBitmap);

        mBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_read);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        ColorMatrix cm = new ColorMatrix();
        float array[] = { 0.55f, 0, 0, 0, 80.0f, 0, 0.55f, 0, 0, 80.0f, 0, 0,
                0.55f, 0, 80.0f, 0, 0, 0, 0.2f, 0 };
        cm.set(array);
        colorMatrixColorFilter = new ColorMatrixColorFilter(cm);
        matrix = new Matrix();
        mScroller = new Scroller(getContext());
        mTouch.x = 0.1f;
        mTouch.y = 0.1f;

    }

    public void setBookFactory(BookFactory bookFactory) {
        mBookFactory = bookFactory;
        int state = mBookFactory.openBook(0, 0);
        if (state == BookFactory.STATE_SUCCESS) {
            mBookFactory.draw(mCurrentCanvas);
            invalidate();
        } else if (state == BookFactory.STATE_ASYN) {
            mIsAsyn = true;
        }
    }

    private void getWidthAndHeight() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        mMaxLength = (float) Math.hypot(mWidth, mHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mBookFactory == null || mIsAsyn) {
            // 还未设置bookFactory
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouch.x = event.getRawX();
            mTouch.y = event.getRawY();
            calcCornerXY(mTouch.x, mTouch.y);
            // 绘制当前页面
            mBookFactory.draw(mCurrentCanvas);
            int state = -1;
            //向右
            if(DragToRight()) {
                state = mBookFactory.prePage();
            } else {
                state = mBookFactory.nextPage();
            }
            if (state == BookFactory.STATE_SUCCESS) {
                // 中断动画
                abortAnimation();
                mBookFactory.draw(mNextCanvas);
            } else if (state == BookFactory.STATE_NULL) {
                if (DragToRight()) {
                    Toast.makeText(getContext(), "当前是第一页哦", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "最后一页了哦", Toast.LENGTH_SHORT).show();
                }
                // 直接返回false，不invalidate,不接受接下来的action
                return false;
            } else {
                // 下一章或者上一章的时候需要时间加载，那么直接启动动画翻页，然后当前页就是bg,然后等待
                // todo 禁止操作直到加载出来
                mIsAsyn = true;
                mNextCanvas.drawBitmap(mBookFactory.getmBackgroundBitmap(), 0, 0, null);
                startAnimation(700);
                Toast.makeText(getContext(), "正在加载", Toast.LENGTH_SHORT).show();
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            Log.d("Debug", "move" + System.currentTimeMillis());
            mTouch.x = event.getRawX();
            mTouch.y = event.getRawY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (canDragOver()) {
                startAnimation(700);
            } else {
                // 恢复原样
                mTouch.x = mCornerX +0.1f;
                mTouch.y = mCornerY +0.1f;
                // 没有翻页，恢复bookFactory的当前页
                if (DragToRight()) {
                    mBookFactory.nextPage();
                } else {
                    mBookFactory.prePage();
                }
            }
        }
        this.invalidate();
        return true;
    }

    /**
     * 异步加载完成
     * @param event
     */
    @Subscribe
    public void onChapterLoad(OnChapterLoadEvent event) {
        mBookFactory.draw(mCurrentCanvas);
        mBookFactory.draw(mNextCanvas);
        invalidate();
        mIsAsyn = false;
    }

    @Subscribe
    public void recycleBitmap(RecycleBitmapEvent event) {
        mCurrentBitmap.recycle();
        mNextBitmap.recycle();
        mBackgroundBitmap.recycle();

        mCurrentBitmap = null;
        mNextBitmap = null;
        mBackgroundBitmap = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xFFAAAAAA);
        calcPoints();
        drawCurrentPageArea(canvas, mCurrentBitmap);
        drawNextPageAreaAndShadow(canvas, mNextBitmap);
        drawCurrentPageShadow(canvas);
        drawCurrentBackArea(canvas, mCurrentBitmap);
    }

    /**
     * 点击的时候判断页脚位置
     * @param x
     * @param y
     */
    public void calcCornerXY(float x, float y) {
        if (x <= mWidth / 2)
            mCornerX = 0;
        else
            mCornerX = mWidth;
        if (y <= mHeight / 2)
            mCornerY = 0;
        else
            mCornerY = mHeight;
        if ((mCornerX == 0 && mCornerY == mHeight)
                || (mCornerX == mWidth && mCornerY == 0))
            mIsRtOrLb = true;
        else
            mIsRtOrLb = false;
    }

    /**
     * 获得两条条直线的交点
     * @param P1
     * @param P2
     * @param P3
     * @param P4
     * @return
     */
    public PointF getCross(PointF P1, PointF P2, PointF P3, PointF P4) {
        PointF CrossP = new PointF();
        // 二元函数通式： y=ax+b
        float a1 = (P2.y - P1.y) / (P2.x - P1.x);
        float b1 = ((P1.x * P2.y) - (P2.x * P1.y)) / (P1.x - P2.x);

        float a2 = (P4.y - P3.y) / (P4.x - P3.x);
        float b2 = ((P3.x * P4.y) - (P4.x * P3.y)) / (P3.x - P4.x);
        CrossP.x = (b2 - b1) / (a1 - a2);
        CrossP.y = a1 * CrossP.x + b1;
        return CrossP;
    }

    private void calcPoints() {
        mMiddleX = (mTouch.x + mCornerX) / 2;
        mMiddleY = (mTouch.y + mCornerY) / 2;
        mBzControl1.x = mMiddleX - (mCornerY - mMiddleY)
                * (mCornerY - mMiddleY) / (mCornerX - mMiddleX);
        mBzControl1.y = mCornerY;
        mBzControl2.x = mCornerX;
        mBzControl2.y = mMiddleY - (mCornerX - mMiddleX)
                * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);
        mBzStart1.x = mBzControl1.x - (mCornerX - mBzControl1.x)
                / 2;
        mBzStart1.y = mCornerY;
        if (mTouch.x > 0 && mTouch.x < mWidth) {
            if (mBzStart1.x < 0 || mBzStart1.x > mWidth) {
                // 这个时候start1出页面了，这时候等比例转换touch
                // 先把start1的x变为距离corner的实际距离
                if (mBzStart1.x < 0)
                    mBzStart1.x = mWidth - mBzStart1.x;
                // f1表示touch距离corner的x距离
                float f1 = Math.abs(mCornerX - mTouch.x);
                // 我们以corner为中心缩小，我们的start.x此时表示start点距离corner的x距离，比例就是width / start.x，
                // 乘以原来的touch的x距离f1得出现在应该的touchx距离f2，然后corner.x - f2 就是现在touch的x坐标
                float f2 = mWidth * f1 / mBzStart1.x;
                mTouch.x = Math.abs(mCornerX - f2);
                // 然后根据原来touch的xy比例（距离corner的xy比例），得出现在的比例，根据已经求的的x距离f2得出y距离，然后减去就得到y坐标
                // 接下来需要根据新的touch，所有重新计算
                float f3 = Math.abs(mCornerX - mTouch.x)
                        * Math.abs(mCornerY - mTouch.y) / f1;
                mTouch.y = Math.abs(mCornerY - f3);

                mMiddleX = (mTouch.x + mCornerX) / 2;
                mMiddleY = (mTouch.y + mCornerY) / 2;

                mBzControl1.x = mMiddleX - (mCornerY - mMiddleY)
                        * (mCornerY - mMiddleY) / (mCornerX - mMiddleX);
                mBzControl1.y = mCornerY;

                mBzControl2.x = mCornerX;
                mBzControl2.y = mMiddleY - (mCornerX - mMiddleX)
                        * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);
                mBzStart1.x = mBzControl1.x
                        - (mCornerX - mBzControl1.x) / 2;

                /**
                 * 说白了也就是新的touch在原来touch和corner连线上，缩小的比例就是width的距离与start.x距离corner的距离，
                 */
            }
        }
        mBzStart2.x = mCornerX;
        mBzStart2.y = mBzControl2.y - (mCornerY - mBzControl2.y)
                / 2;

        mTouchCornerDis = (float) Math.hypot((mTouch.x - mCornerX),
                (mTouch.y - mCornerY));

        mBzEnd1 = getCross(mTouch, mBzControl1, mBzStart1,
                mBzStart2);
        mBzEnd2 = getCross(mTouch, mBzControl2, mBzStart1,
                mBzStart2);

        mBzVertex1.x = (mBzStart1.x + 2 * mBzControl1.x + mBzEnd1.x) / 4;
        mBzVertex1.y = (2 * mBzControl1.y + mBzStart1.y + mBzEnd1.y) / 4;
        mBzVertex2.x = (mBzStart2.x + 2 * mBzControl2.x + mBzEnd2.x) / 4;
        mBzVertex2.y = (2 * mBzControl2.y + mBzStart2.y + mBzEnd2.y) / 4;
    }

    private void drawCurrentPageArea(Canvas canvas, Bitmap bitmap) {
        mPath1.reset();
        mPath1.moveTo(mBzStart1.x, mBzStart1.y);
        mPath1.quadTo(mBzControl1.x, mBzControl1.y, mBzEnd1.x,
                mBzEnd1.y);
        mPath1.lineTo(mTouch.x, mTouch.y);
        mPath1.lineTo(mBzEnd2.x, mBzEnd2.y);
        mPath1.quadTo(mBzControl2.x, mBzControl2.y, mBzStart2.x,
                mBzStart2.y);
        mPath1.lineTo(mCornerX, mCornerY);
        mPath1.close();

        canvas.save();
        canvas.clipPath(mPath1, Region.Op.XOR);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.restore();
    }

    private void drawNextPageAreaAndShadow(Canvas canvas, Bitmap bitmap) {
        mPath2.reset();
        mPath2.moveTo(mBzStart1.x, mBzStart1.y);
        mPath2.lineTo(mBzVertex1.x, mBzVertex1.y);
        mPath2.lineTo(mBzVertex2.x, mBzVertex2.y);
        mPath2.lineTo(mBzStart2.x, mBzStart2.y);
        mPath2.lineTo(mCornerX, mCornerY);
        mPath2.close();

        mDegress = (float) Math.toDegrees(Math.atan2(mBzControl1.x
                - mCornerX, mBzControl2.y - mCornerY));
        int leftx;
        int rightx;
        GradientDrawable mBackShadowDrawable;
        if (mIsRtOrLb) {
            leftx = (int) (mBzStart1.x);
            rightx = (int) (mBzStart1.x + mTouchCornerDis / 4);
            mBackShadowDrawable = mBackDrawableLR;
        } else {
            leftx = (int) (mBzStart1.x - mTouchCornerDis / 4);
            rightx = (int) mBzStart1.x;
            mBackShadowDrawable = mBackDrawableRL;
        }
        canvas.save();
        canvas.clipPath(mPath1);
        canvas.clipPath(mPath2, Region.Op.INTERSECT);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.rotate(mDegress, mBzStart1.x, mBzStart1.y);
        // rotate就是旋转坐标，顺时针，默认原点旋转，可以指定旋转点，然后按照专选后的的坐标绘制，不会对之前的绘制有影响
        // 其实可以这样看，我们先按原来坐标绘制出来东西，在把这个东西按照点来旋转角度，效果是一样的，我们的阴影绘制在(start.x - touchCornerDic / 4, mHeight, start.x, maxLen)矩形中
        // 最后按照start点来旋转就可以变成原来当前页和下一页的交汇线下面的阴影 touchCornerDic / 4就是阴影的宽度
        mBackShadowDrawable.setBounds(leftx, (int) mBzStart1.y, rightx,
                (int) (mMaxLength + mBzStart1.y));
        mBackShadowDrawable.draw(canvas);
        canvas.restore();
    }


    private void createDrawable() {
        int[] color = { 0x333333, 0xB0333333 };
        mFolderDrawableRL = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT, color);
        mFolderDrawableRL
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFolderDrawableLR = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, color);
        mFolderDrawableLR
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mBackShadowColors = new int[] { 0xFF111111, 0x111111 };
        mBackDrawableRL = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT, mBackShadowColors);
        mBackDrawableRL.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mBackDrawableLR = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, mBackShadowColors);
        mBackDrawableLR.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontShadowColors = new int[] { 0x80111111, 0x111111 };
        mFrontDrawable_v_lr = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, mFrontShadowColors);
        mFrontDrawable_v_lr
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);
        mFrontDrawable_v_rl = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT, mFrontShadowColors);
        mFrontDrawable_v_rl
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontDrawable_h_tb = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, mFrontShadowColors);
        mFrontDrawable_h_tb
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontDrawble_h_bt = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP, mFrontShadowColors);
        mFrontDrawble_h_bt
                .setGradientType(GradientDrawable.LINEAR_GRADIENT);
    }


    public void drawCurrentPageShadow(Canvas canvas) {
        double degree;
        if (mIsRtOrLb) {
            degree = Math.PI
                    / 4
                    - Math.atan2(mBzControl1.y - mTouch.y, mTouch.x
                    - mBzControl1.x);
        } else {
            degree = Math.PI
                    / 4
                    - Math.atan2(mTouch.y - mBzControl1.y, mTouch.x
                    - mBzControl1.x);
        }

        double d1 = (float) 25 * 1.414 * Math.cos(degree);
        double d2 = (float) 25 * 1.414 * Math.sin(degree);
        float x = (float) (mTouch.x + d1);
        float y;
        if (mIsRtOrLb) {
            y = (float) (mTouch.y + d2);
        } else {
            y = (float) (mTouch.y - d2);
        }
        mPath2.reset();
        mPath2.moveTo(x, y);
        mPath2.lineTo(mTouch.x, mTouch.y);
        mPath2.lineTo(mBzControl1.x, mBzControl1.y);
        mPath2.lineTo(mBzStart1.x, mBzStart1.y);
        mPath2.close();
        float rotateDegrees;
        canvas.save();

        canvas.clipPath(mPath1, Region.Op.XOR);
        canvas.clipPath(mPath2, Region.Op.INTERSECT);
        int leftx;
        int rightx;
        GradientDrawable mCurrentPageShadow;
        if (mIsRtOrLb) {
            leftx = (int) (mBzControl1.x);
            rightx = (int) mBzControl1.x + 25;
            mCurrentPageShadow = mFrontDrawable_v_lr;
        } else {
            leftx = (int) (mBzControl1.x - 25);
            rightx = (int) mBzControl1.x + 1;
            mCurrentPageShadow = mFrontDrawable_v_rl;
        }
        rotateDegrees = (float) Math.toDegrees(Math.atan2(mTouch.x
                - mBzControl1.x, mBzControl1.y - mTouch.y));
        canvas.rotate(rotateDegrees, mBzControl1.x, mBzControl1.y);
        mCurrentPageShadow.setBounds(leftx,
                (int) (mBzControl1.y - mMaxLength), rightx,
                (int) (mBzControl1.y));
        mCurrentPageShadow.draw(canvas);
        canvas.restore();

        mPath2.reset();
        mPath2.moveTo(x, y);
        mPath2.lineTo(mTouch.x, mTouch.y);
        mPath2.lineTo(mBzControl2.x, mBzControl2.y);
        mPath2.lineTo(mBzStart2.x, mBzStart2.y);
        mPath2.close();
        canvas.save();
        canvas.clipPath(mPath1, Region.Op.XOR);
        canvas.clipPath(mPath2, Region.Op.INTERSECT);
        if (mIsRtOrLb) {
            leftx = (int) (mBzControl2.y);
            rightx = (int) (mBzControl2.y + 25);
            mCurrentPageShadow = mFrontDrawable_h_tb;
        } else {
            leftx = (int) (mBzControl2.y - 25);
            rightx = (int) (mBzControl2.y + 1);
            mCurrentPageShadow = mFrontDrawble_h_bt;
        }
        rotateDegrees = (float) Math.toDegrees(Math.atan2(mBzControl2.y
                - mTouch.y, mBzControl2.x - mTouch.x));
        canvas.rotate(rotateDegrees, mBzControl2.x, mBzControl2.y);
        float temp;
        if (mBzControl2.y < 0)
            temp = mBzControl2.y - mHeight;
        else
            temp = mBzControl2.y;

        int hmg = (int) Math.hypot(mBzControl2.x, temp);
        if (hmg > mMaxLength)
            mCurrentPageShadow
                    .setBounds((int) (mBzControl2.x - 25) - hmg, leftx,
                            (int) (mBzControl2.x + mMaxLength) - hmg,
                            rightx);
        else
            mCurrentPageShadow.setBounds(
                    (int) (mBzControl2.x - mMaxLength), leftx,
                    (int) (mBzControl2.x), rightx);

        mCurrentPageShadow.draw(canvas);
        canvas.restore();
    }

    private void drawCurrentBackArea(Canvas canvas, Bitmap bitmap) {
        mPath2.reset();
        mPath2.moveTo(mBzVertex1.x, mBzVertex1.y);
        mPath2.lineTo(mBzVertex2.x, mBzVertex2.y);
        mPath2.lineTo(mBzEnd2.x, mBzEnd2.y);
        mPath2.lineTo(mTouch.x, mTouch.y);
        mPath2.lineTo(mBzEnd1.x, mBzEnd1.y);
        mPath2.close();

        Matrix matrix = getSymmetricalMatrix();


        canvas.save();
        canvas.clipPath(mPath1);
        canvas.clipPath(mPath2, Region.Op.INTERSECT);
        // 由于对称过来的时候会有一部分不能填满，所以先画背景填满
        canvas.drawBitmap(mBackgroundBitmap, 0, 0, null);
        canvas.drawBitmap(bitmap, matrix, null);
        canvas.restore();
    }

    /**
     * 关于两个control连线的对称矩阵
     * @return
     */
    private Matrix getSymmetricalMatrix() {
        // 根据网上资料，有一点错误，就是先－b，再b
        float k = (mBzControl1.y - mBzControl2.y) / (mBzControl1.x - mBzControl2.x);
        float[] values = new float[9];
        values[0] = -1 * (k * k - 1) / (k * k + 1);
        values[1] = 2 * k / (k * k + 1);
        values[3] = values[1];
        values[4] = -values[0];
        values[8] = 1;

        float b = mBzControl1.y - mBzControl1.x * k;
        Matrix matrix = new Matrix();
        matrix.setValues(values);
        matrix.preTranslate(0, -b);
        matrix.postTranslate(0, b);

        return matrix;
    }

    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            float x = mScroller.getCurrX();
            float y = mScroller.getCurrY();
            mTouch.x = x;
            mTouch.y = y;
            //必须重绘调用onDraw方法
            postInvalidate();
        }
    }

    private void startAnimation(int delayMillis) {
        int dx, dy;

        if (mCornerX > 0) {
            dx = -(int) (mWidth + mTouch.x);
        } else {
            dx = (int) (mWidth - mTouch.x + mWidth);
        }
        if (mCornerY > 0) {
            dy = (int) (mHeight - mTouch.y) - 1;
        } else {
            dy = (int) (1 - mTouch.y); // 防止mTouch.y最终变为0
        }
        mScroller.startScroll((int) mTouch.x, (int) mTouch.y, dx, dy,
                delayMillis);

        invalidate();
    }

    public void abortAnimation() {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
        }
    }

    public boolean canDragOver() {
        if (mTouchCornerDis > mWidth / 6)
            return true;
        return false;
    }
    public boolean DragToRight() {
        if (mCornerX > 0)
            return false;
        return true;
    }

}
