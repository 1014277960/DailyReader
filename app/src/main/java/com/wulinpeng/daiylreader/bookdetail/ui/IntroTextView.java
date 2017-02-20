package com.wulinpeng.daiylreader.bookdetail.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午7:41
 * @description: 显示缩略内容，点击后展开
 */
public class IntroTextView extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {

    private boolean isComplete = false;

    public IntroTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 设置最多行数和结尾使用。。。省略
        setMaxLines(4);
        setEllipsize(TextUtils.TruncateAt.END);
        setClickable(true);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 改变最高行数改变状态
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (isComplete) {
            isComplete = false;
            setMaxLines(4);
        } else {
            isComplete = true;
            setMaxLines(1000);
        }
        requestLayout();
    }
}
