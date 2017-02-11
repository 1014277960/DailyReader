package com.wulinpeng.daiylreader.search.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

    private int width, height = 500;

    private List<String> words = new ArrayList<>();

    private List<List<View>> formatChildren = new ArrayList<>();

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(OnTextClickListener listener) {
        this.listener = listener;
    }

    public void setWords(List<String> words) {
        this.words.clear();
        this.words.addAll(words);
        removeAllViews();
        addViews();
    }

    private void addViews() {
        for (String word : words) {
            addViewWithWord(word);
        }
    }

    private void addViewWithWord(String word) {
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);

        textView.setPadding(5, 5, 5, 5);
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);
        textView.setText(word);
        textView.setBackgroundColor(Color.BLUE);

        textView.setClickable(true);
        textView.setOnClickListener(this);

        addView(textView);
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        formatChildren.clear();
        width = calculateWidth();

        int currentLineWidth = 0;
        int currentLineHeight = 0;

        List<View> line = new ArrayList<>();
        View child = null;
        boolean canAdd = false;
        for (int i = 0; i != getChildCount(); i++) {
            child = getChildAt(i);
            int childWidth = child.getMeasuredWidth() + 2 * normalMargin;
            int childHeight = child.getMeasuredHeight() + 2 * normalMargin;
            canAdd = false;
            if (currentLineWidth + childWidth <= width && currentLineHeight + childHeight <= height) {
                // 本行可以再加
                line.add(child);
                currentLineWidth += childWidth;
                canAdd = true;
            } else if (currentLineHeight + childHeight <= height) {
                // 本行不能加，但是下一行可以，所以换行
                formatChildren.add(line);
                line = new ArrayList<>();
                line.add(child);
                currentLineHeight += child.getHeight();
                currentLineWidth = childWidth;
            } else {
                // 全部空间满了，结束
                formatChildren.add(line);
                break;
            }
        }

        if (canAdd) {
            formatChildren.add(line);
        }

        setMeasuredDimension(width, height);

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
