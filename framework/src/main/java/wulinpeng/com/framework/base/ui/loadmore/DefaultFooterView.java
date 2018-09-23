package wulinpeng.com.framework.base.ui.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wulinpeng.com.framework.R;

/**
 * @author wulinpeng
 * @datetime: 18/9/22 下午5:16
 * @description:
 */
public class DefaultFooterView extends RelativeLayout implements IFooterView {

    private ProgressBar mLoadingView;
    private TextView mNoMoreView;
    private TextView mErrorView;

    private LoadMoreAdapter.LoadingState mState = LoadMoreAdapter.LoadingState.LOADING_STATE_NORMAL;

    public DefaultFooterView(Context context) {
        this(context, null);
    }

    public DefaultFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.framework_default_footer_layout, this);
        mLoadingView = (ProgressBar) findViewById(R.id.loading_view);
        mNoMoreView = (TextView) findViewById(R.id.no_more_view);
        mErrorView = (TextView) findViewById(R.id.error_view);
    }
    @Override
    public void changState(LoadMoreAdapter.LoadingState state) {
        mState = state;
        mLoadingView.setVisibility(mState == LoadMoreAdapter.LoadingState.LOADING_STATE_LOADING? VISIBLE: GONE);
        mNoMoreView.setVisibility(mState == LoadMoreAdapter.LoadingState.LOADING_STATE_NO_MORE? VISIBLE: GONE);
        mErrorView.setVisibility(mState == LoadMoreAdapter.LoadingState.LOADING_STATE_ERROR? VISIBLE: GONE);
    }
}
