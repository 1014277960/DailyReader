package wulinpeng.com.framework.base.ui.loadmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 18/9/22 下午4:39
 * @description: Adapter的包装类，提供LoadMore功能
 */
public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public enum LoadingState {
        LOADING_STATE_NO_MORE,
        LOADING_STATE_LOADING,
        LOADING_STATE_ERROR,
        LOADING_STATE_NORMAL
    }

    private static final int TYPE_FOOT = 1;

    private RecyclerView.Adapter mAdapter;
    private OnLoadMoreListener mLoadMoreListener;
    private Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.OnScrollListener mScrollerListener;

    private IFooterViewFactory mFooterViewFactory;

    private LoadingState mState = LoadingState.LOADING_STATE_NORMAL;

    public static LoadMoreAdapter wrap(Context context, RecyclerView.Adapter adapter, OnLoadMoreListener loadMoreListener) {
        return new LoadMoreAdapter(context, adapter, loadMoreListener, null);
    }

    public static LoadMoreAdapter wrap(Context context, RecyclerView.Adapter adapter, OnLoadMoreListener loadMoreListener, IFooterViewFactory factory) {
        return new LoadMoreAdapter(context, adapter, loadMoreListener, factory);
    }

    private LoadMoreAdapter(Context context, RecyclerView.Adapter adapter, OnLoadMoreListener loadMoreListener, IFooterViewFactory factory) {
        this.mContext = context;
        this.mAdapter = adapter;
        this.mLoadMoreListener = loadMoreListener;
        if (factory == null) {
            factory = new DefaultFooterViewFactory();
        }
        this.mFooterViewFactory = factory;
        initScrollListener();
    }

    private void initScrollListener() {
        mScrollerListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean showLoadMore = false;
                showLoadMore = !recyclerView.canScrollVertically(1) && dy > 0;
                if (showLoadMore) {
                    if (mLoadMoreListener != null && mState != LoadingState.LOADING_STATE_LOADING) {
                        mState = LoadingState.LOADING_STATE_LOADING;
                        notifyDataSetChanged();
                        mLoadMoreListener.onLoadMore();
                    }
                }
            }
        };
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOT) {
            View footView = (View) mFooterViewFactory.createFooterView(mContext);
            return new FooterViewHolder(footView);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder viewHolder = (FooterViewHolder) holder;
            viewHolder.bind(mState);
            return;
        }
        mAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (holder instanceof FooterViewHolder) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        mAdapter.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
        mAdapter.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        if (position > mAdapter.getItemCount() - 1) {
            return super.getItemId(position);
        }
        return mAdapter.getItemId(position);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof FooterViewHolder) {
            super.onViewRecycled(holder);
            return;
        }
        mAdapter.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        if (holder instanceof FooterViewHolder) {
            return super.onFailedToRecycleView(holder);
        }
        return mAdapter.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof FooterViewHolder) {
            super.onViewAttachedToWindow(holder);
            return;
        }
        mAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if (holder instanceof FooterViewHolder) {
            super.onViewDetachedFromWindow(holder);
        }
        mAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + (mState == LoadingState.LOADING_STATE_NORMAL? 0: 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mAdapter.getItemCount()) {
            return TYPE_FOOT;
        }
        return mAdapter.getItemViewType(position);
    }

    /**
     * 调用mAdapter的registerAdapterDataObserver方法使得mAdapter的notify方法也可以触发RecyclerView的刷新
     * @param observer
     */
    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        mAdapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        mAdapter.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mAdapter.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(mScrollerListener);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mAdapter.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView.removeOnScrollListener(mScrollerListener);
        mRecyclerView = null;
    }

    /**
     * 提供外部改变状态的方法
     * @param state
     */
    public void updateLoadMoreState(LoadingState state) {
        if (!checkState(state)) {
            return;
        }
        mState = state;
        notifyDataSetChanged();
    }

    private boolean checkState(LoadingState state) {
        if (state == mState) {
            return false;
        }
        return true;
    }


    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

}
