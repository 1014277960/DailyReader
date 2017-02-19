package com.wulinpeng.daiylreader.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午8:14
 * @description: 带有加载更多功能的Adapter, 子Adapter需要根据isFooterShow来绘制
 */
public abstract class FooterRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected boolean loading = false;

    protected OnLoadMoreListener mLoadMoreListener;

    public interface  OnLoadMoreListener {
        public void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mLoadMoreListener = listener;
    }

    public void setLoadingState(boolean loading) {
        this.loading = loading;
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(scrollListener);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // 锁定触发
            if (loading) {
                return;
            }
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();

            // 最后一个item刚出现且还能上滑的时候触发并锁定防止多次触发，提前触发以便无缝衔接，需要用户加载完数据后手动解锁
            if (recyclerView.canScrollVertically(1) && (lastVisiblePosition == itemCount - 1)) {
                Log.d("Debug", "OnLoadMore...");
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore();
                    loading = true;
                    notifyDataSetChanged();
                }
            }
        }
    };
}
