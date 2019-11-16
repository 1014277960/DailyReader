package wulinpeng.com.framework.base.ui.loadmore

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView

import wulinpeng.com.framework.R

/**
 * @author wulinpeng
 * @datetime: 18/9/22 下午5:16
 * @description:
 */
class DefaultFooterView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr), IFooterView {

    private var mLoadingView: ProgressBar? = null
    private var mNoMoreView: TextView? = null
    private var mErrorView: TextView? = null

    private var mState: LoadMoreAdapter.LoadingState = LoadMoreAdapter.LoadingState.LOADING_STATE_NORMAL

    init {
        init()
    }

    private fun init() {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.framework_default_footer_layout, this)
        mLoadingView = findViewById<View>(R.id.loading_view) as ProgressBar
        mNoMoreView = findViewById<View>(R.id.no_more_view) as TextView
        mErrorView = findViewById<View>(R.id.error_view) as TextView
    }

    override fun changState(state: LoadMoreAdapter.LoadingState) {
        mState = state
        mLoadingView?.visibility = if (mState == LoadMoreAdapter.LoadingState.LOADING_STATE_LOADING) View.VISIBLE else View.GONE
        mNoMoreView?.visibility = if (mState == LoadMoreAdapter.LoadingState.LOADING_STATE_NO_MORE) View.VISIBLE else View.GONE
        mErrorView?.visibility = if (mState == LoadMoreAdapter.LoadingState.LOADING_STATE_ERROR) View.VISIBLE else View.GONE
    }
}
