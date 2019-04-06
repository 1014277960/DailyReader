package wulinpeng.com.framework.base.ui.loadmore

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * @author wulinpeng
 * @datetime: 18/9/22 下午4:39
 * @description: Adapter的包装类，提供LoadMore功能
 */
class LoadMoreAdapter private constructor(private val mContext: Context, private val mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, private val mLoadMoreListener: OnLoadMoreListener?, factory: IFooterViewFactory?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mRecyclerView: RecyclerView? = null
    private var mScrollerListener: RecyclerView.OnScrollListener? = null

    private val mFooterViewFactory: IFooterViewFactory

    private var mState = LoadingState.LOADING_STATE_NORMAL

    enum class LoadingState {
        LOADING_STATE_NO_MORE,
        LOADING_STATE_LOADING,
        LOADING_STATE_ERROR,
        LOADING_STATE_NORMAL
    }

    init {
        this.mFooterViewFactory = factory ?: DefaultFooterViewFactory()
        initScrollListener()
    }

    private fun initScrollListener() {
        mScrollerListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView ?: return
                val showLoadMore = !recyclerView.canScrollVertically(1) && dy > 0
                if (showLoadMore) {
                    if (mLoadMoreListener != null && mState != LoadingState.LOADING_STATE_LOADING && mState != LoadingState.LOADING_STATE_NO_MORE) {
                        mState = LoadingState.LOADING_STATE_LOADING
                        notifyDataSetChanged()
                        mLoadMoreListener.onLoadMore()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_FOOT) {
            val footView = mFooterViewFactory.createFooterView(mContext) as View
            return FooterViewHolder(footView)
        }
        return mAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FooterViewHolder) {
            holder.bind(mState)
            return
        }
        mAdapter.onBindViewHolder(holder, position)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (holder is FooterViewHolder) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        mAdapter.onBindViewHolder(holder, position, payloads)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
        mAdapter.setHasStableIds(hasStableIds)
    }

    override fun getItemId(position: Int): Long {
        return if (position > mAdapter.itemCount - 1) {
            super.getItemId(position)
        } else mAdapter.getItemId(position)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is FooterViewHolder) {
            super.onViewRecycled(holder)
            return
        }
        mAdapter.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return if (holder is FooterViewHolder) {
            super.onFailedToRecycleView(holder)
        } else mAdapter.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder is FooterViewHolder) {
            super.onViewAttachedToWindow(holder)
            return
        }
        mAdapter.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        if (holder is FooterViewHolder) {
            super.onViewDetachedFromWindow(holder)
        }
        mAdapter.onViewDetachedFromWindow(holder)
    }

    override fun getItemCount(): Int {
        return mAdapter.itemCount + if (mState == LoadingState.LOADING_STATE_NORMAL) 0 else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mAdapter.itemCount) {
            TYPE_FOOT
        } else mAdapter.getItemViewType(position)
    }

    /**
     * 调用mAdapter的registerAdapterDataObserver方法使得mAdapter的notify方法也可以触发RecyclerView的刷新
     * @param observer
     */
    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(observer)
        mAdapter.registerAdapterDataObserver(observer)
    }

    override fun unregisterAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.unregisterAdapterDataObserver(observer)
        mAdapter.unregisterAdapterDataObserver(observer)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mAdapter.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
        mRecyclerView!!.addOnScrollListener(mScrollerListener)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mAdapter.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView!!.removeOnScrollListener(mScrollerListener)
        mRecyclerView = null
    }

    /**
     * 提供外部改变状态的方法
     * @param state
     */
    fun updateLoadMoreState(state: LoadingState) {
        if (!checkState(state)) {
            return
        }
        mState = state
        notifyDataSetChanged()
    }

    private fun checkState(state: LoadingState): Boolean {
        return state != mState
    }


    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    companion object {

        private val TYPE_FOOT = 1

        fun wrap(context: Context, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, loadMoreListener: OnLoadMoreListener): LoadMoreAdapter {
            return LoadMoreAdapter(context, adapter, loadMoreListener, null)
        }

        fun wrap(context: Context, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, loadMoreListener: OnLoadMoreListener, factory: IFooterViewFactory): LoadMoreAdapter {
            return LoadMoreAdapter(context, adapter, loadMoreListener, factory)
        }
    }

}
