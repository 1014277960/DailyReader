package com.wulinpeng.daiylreader.read.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.wulinpeng.daiylreader.Application;
import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.net.ReaderApiManager;
import com.wulinpeng.daiylreader.bean.ChapterDetailResponse;
import com.wulinpeng.daiylreader.bean.ChaptersResponse;
import com.wulinpeng.daiylreader.manager.CacheManager;
import com.wulinpeng.daiylreader.read.event.OnChapterLoadEvent;
import com.wulinpeng.daiylreader.util.RxUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wulinpeng.daiylreader.Application.getContext;

/**
 * @author wulinpeng
 * @datetime: 17/2/8 下午7:00
 * @description:
 */
public class BookFactory {

    /**
     * 翻页的状态,如果是新章节且内存中没有那么可能会有网络请求那就是异步，就不能直接draw了，等待读取完毕再调用接口通知draw
     */
    public static final int STATE_SUCCESS = 0;
    public static final int STATE_ASYN = 1;
    public static final int STATE_NULL = 2;

    private int mHeight;
    private int mWidth;

    public int mNormalSize = 45;
    public int mTitleSize = 64;
    public Paint mNormalPaint;
    public Paint mTitlePaint;
    public int mNormalMargin = 30;
    private String mTitle;

    private Bitmap mBackgroundBitmap;

    // 当前章节数
    private int mCurrentChapterIndex;
    //当前是第几页
    public int mCurrentPage;
    
    private ChaptersResponse.MixToc mChaptersInfo;
    // 根据当前格式处理后的章节内容
    private Map<Integer, List<List<String>>> mFormatChapters = new HashMap<>();
    private ChapterDetailResponse.Chapter mCurrentChapter;

    public BookFactory(ChaptersResponse.MixToc chaptersInfo){
        this.mChaptersInfo = chaptersInfo;
        getWidthAndHeight();
        mNormalPaint = new Paint();
        mNormalPaint.setTextSize(mNormalSize);
        mNormalPaint.setColor(Color.BLACK);
        mNormalPaint.setTextAlign(Paint.Align.LEFT);

        mTitlePaint = new Paint();
        mTitlePaint.setTextSize(mTitleSize);
        mTitlePaint.setColor(Color.DKGRAY);
        mTitlePaint.setTextAlign(Paint.Align.CENTER);
        mTitlePaint.setTypeface(Typeface.DEFAULT_BOLD);

        mCurrentPage = 0;
        mBackgroundBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bg_read);
    }

    private void getWidthAndHeight() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;
    }

    /**
     * 打开小说某一章
     * @param chapter
     * @param start 0首页还是1尾页
     * @return
     */
    public int openBook(int chapter, int start) {
        mCurrentChapterIndex = chapter;
        mTitle = mChaptersInfo.getChapters().get(chapter).getTitle();
        if (mFormatChapters.get(mCurrentChapterIndex) == null) {
            // 缓存没有到内存拿
            if (getChapterFromCache(chapter, start)) {
                return STATE_SUCCESS;
            } else {
                getChapterFromNet(chapter, start);
                return STATE_ASYN;
            }
        } else {
            // 是0就就是0，否则1不影响
            mCurrentPage = (mFormatChapters.get(mCurrentChapterIndex).size() - 1) * start;
            return STATE_SUCCESS;
        }
    }

    private boolean getChapterFromCache(int chapter, int start) {
        ChapterDetailResponse.Chapter c = CacheManager.getInstance().getChapter(mChaptersInfo.getBook(), chapter);
        if (c != null) {
            formatChapter(c);
            mCurrentPage = (mFormatChapters.get(mCurrentChapterIndex).size() - 1) * start;
            return true;
        } else {
            return false;
        }
    }

    private void getChapterFromNet(int chapter, int start) {
        ReaderApiManager.INSTANCE.getChapterDetail(mChaptersInfo.getChapters().get(chapter).getLink())
                .compose(RxUtil.rxScheduler())
                .subscribe(chapterDetailResponse -> dealResponse(chapterDetailResponse, start),
                        throwable -> Toast.makeText(Application.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void dealResponse(ChapterDetailResponse response, int start) {
        mCurrentPage = 0;
        mCurrentChapter = response.getChapter();
        formatChapter(mCurrentChapter);
        mCurrentPage = (mFormatChapters.get(mCurrentChapterIndex).size() - 1) * start;

        EventBus.getDefault().post(new OnChapterLoadEvent());

        // TODO: 17/2/20 通过读取设置来判断是否应该缓存
        // CacheManager.getInstance().saveChapter(mChaptersInfo.getBook(), mCurrentChapterIndex, response.getChapter());
    }

    /**
     * 格式化章节内容
     * @param chapter
     */
    private void formatChapter(ChapterDetailResponse.Chapter chapter) {
        boolean isFirstPage = true;
        boolean isEnded = false;
        //当前正在显示的页的第一个字符，并且作为游标累加
        int currentCursor = -1;
        float contentHeight = 0;
        String content = chapter.getBody();
        int length = content.length();

        List<List<String>> pages = new ArrayList<>();

        int perLineMaxCount = (mWidth - 2 * mNormalMargin) / mNormalSize;
        while (currentCursor < length) {
            // 每一轮一页
            List<String> lines = new ArrayList<>();
            contentHeight = mNormalSize + mNormalMargin;
            if (isFirstPage) {
                //这一章的首页
                contentHeight = mTitleSize + mNormalMargin * 2;
                isFirstPage = false;
            }
            while ((contentHeight + mNormalMargin + mNormalSize) <= mHeight && !isEnded) {
                // 每一轮一行
                String lineContent = "";
                for(int i = 0; i < perLineMaxCount; i++) {
                    currentCursor++;
                    //可能结束的情况
                    if(currentCursor >= length){
                        isEnded = true;
                        break;
                    }
                    if(content.charAt(currentCursor) == '\n') {
                        lines.add(lineContent);
                        contentHeight += mNormalMargin;
                        contentHeight += mNormalSize;
                        lines.add(" ");
                        contentHeight += mNormalMargin;
                        contentHeight += mNormalSize;
                        lineContent = "\n";
                        break;
                    } else if (content.charAt(currentCursor) == ' ') {
                        lineContent += " ";
                    } else {
                        lineContent += content.charAt(currentCursor);
                    }
                }
                if(lineContent != "\n"){
                    lines.add(lineContent);
                    contentHeight += mNormalMargin;
                    contentHeight += mNormalSize;
                }
            }
            pages.add(lines);
        }
        mFormatChapters.put(mCurrentChapterIndex, pages);
    }

    // 将当前页绘制到canvas上
    public void draw(Canvas canvas) {
        List<List<String>> pages = mFormatChapters.get(mCurrentChapterIndex);
        if (pages == null || pages.size() == 0) {
            return;
        }
        List<String> lines = pages.get(mCurrentPage);

        if(null != lines || lines.size() > 0){
            canvas.drawBitmap(mBackgroundBitmap, null, new RectF(0, 0, mWidth, mHeight), null);
            int y = mNormalMargin;
            //绘制标题
            if(mCurrentPage == 0){
                y += mTitleSize;
                canvas.drawText(mTitle, mWidth / 2, y, mTitlePaint);
                y += mNormalMargin;
            }
            //绘制正文
            for(int i = 0; i < lines.size(); i++){
                String line = lines.get(i);
                y += mNormalSize;
                canvas.drawText(line, mNormalMargin, y, mNormalPaint);
                y += mNormalMargin;
            }
        }
    }

    //下一页
    public int nextPage(){
        if(mCurrentPage == mFormatChapters.get(mCurrentChapterIndex).size() - 1){
            //该下一章了
            if (mCurrentChapterIndex + 1 > mChaptersInfo.getChapters().size() - 1) {
                // 没有下一章了
                return STATE_NULL;
            }
            mCurrentChapterIndex++;
            return openBook(mCurrentChapterIndex, 0);
        } else {
            mCurrentPage++;
            return STATE_SUCCESS;
        }
    }

    //上一页
    public int prePage(){
        if(mCurrentPage == 0){
            // 上一章
            if(mCurrentChapterIndex == 0){
                // 没有上一章了
                return STATE_NULL;
            }
            mCurrentChapterIndex--;
            return openBook(mCurrentChapterIndex, 1);
        }else{
            mCurrentPage--;
            return STATE_SUCCESS;
        }
    }
    //是否是小说的末尾
    public boolean isEnd(){
        if(mCurrentChapterIndex == mChaptersInfo.getChapters().size() - 1){
            if(mCurrentPage == mFormatChapters.get(mCurrentChapterIndex).size() - 1){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    //是否是小说的第一页
    public boolean isFirst(){
        if(mCurrentChapterIndex == 0 && mCurrentPage == 0){
            return true;
        }else{
            return false;
        }
    }

    public String getTitle(){
        return mTitle;
    }

    public Bitmap getmBackgroundBitmap() {
        return mBackgroundBitmap;
    }

}
