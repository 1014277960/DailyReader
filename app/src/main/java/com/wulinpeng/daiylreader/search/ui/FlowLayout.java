package com.wulinpeng.daiylreader.search.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wulinpeng
 * @datetime: 17/2/11 下午11:56
 * @description:
 */
public class FlowLayout extends ViewGroup implements View.OnClickListener {

    public interface OnTextClickListener {
        public void onTextClick(String content);
    }

    private OnTextClickListener listener;

    private int normalMargin = 20;

    private int width;

    /**
     * 存放热词，按页分
     */
    private List<List<String>> pages = new ArrayList<>();

    /**
     * 当前展示的热词页
     */
    private int currentWordsIndex = 0;

    private List<List<View>> formatChildren = new ArrayList<>();

    private int[] colors = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(OnTextClickListener listener) {
        this.listener = listener;
    }

    /**
     * 供外部调用
     * @param pages
     */
    public void setPages(List<List<String>> pages) {
        this.pages.clear();
        this.pages.addAll(pages);
        if (pages.size() == 0) {
            return;
        }
        removeAllViews();
        currentWordsIndex = 0;
        addViews();
    }

    /**
     * 换一批--下一页数据
     */
    public void nextPage() {
        if (pages.size() <= 1) {
            return;
        }
        currentWordsIndex = (currentWordsIndex + 1) % pages.size();
        removeAllViews();
        addViews();
    }

    private void addViews() {
        for (String word : pages.get(currentWordsIndex)) {
            addChildWithWord(word);
        }
    }

    private void addChildWithWord(String word) {
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);

        textView.setPadding(25, 15, 25, 15);
        textView.setTextSize(15);
        textView.setTextColor(Color.WHITE);
        textView.setText(word);
        textView.setBackgroundColor(getRandomColor());

        textView.setClickable(true);
        textView.setOnClickListener(this);

        addView(textView);
        requestLayout();
    }

    private int getRandomColor() {
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        formatChildren.clear();
        width = calculateWidth();

        int currentLineWidth = 0;

        List<View> line = new ArrayList<>();
        View child = null;
        for (int i = 0; i != getChildCount(); i++) {
            child = getChildAt(i);
            int childWidth = child.getMeasuredWidth() + 2 * normalMargin;
            int childHeight = child.getMeasuredHeight() + 2 * normalMargin;

            if (currentLineWidth + childWidth <= width) {
                // 本行可以再加
                line.add(child);
                currentLineWidth += childWidth;
            } else {
                // 本行不能加，但是下一行可以，所以换行
                formatChildren.add(line);
                line = new ArrayList<>();
                line.add(child);
                currentLineWidth = childWidth;
            }
        }

        formatChildren.add(line);

        View view = getChildAt(0);
        if (view != null) {
            setMeasuredDimension(width, (view.getMeasuredHeight() + 2 * normalMargin) * formatChildren.size());
        }

    }

    private int calculateWidth() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
       return metrics.widthPixels;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentHeight = 0;
        for (List<View> line : formatChildren) {
            int currentWidth = 0;
            // 全部childHeight是一样的
            int childHeight = 0;
            for (View child : line) {
                int childWidth = child.getMeasuredWidth() + 2 * normalMargin;
                childHeight = child.getMeasuredHeight() + 2 * normalMargin;
                child.layout(currentWidth + normalMargin, currentHeight + normalMargin,
                        currentWidth + normalMargin + child.getMeasuredWidth(), currentHeight + normalMargin + child.getMeasuredHeight());
                currentWidth += childWidth;
            }
            currentHeight += childHeight;
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            TextView textView = (TextView) v;
            listener.onTextClick(textView.getText().toString());
        }
    }
}
