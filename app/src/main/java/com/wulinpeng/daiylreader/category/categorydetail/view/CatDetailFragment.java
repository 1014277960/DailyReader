package com.wulinpeng.daiylreader.category.categorydetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wulinpeng.daiylreader.R;

import com.wulinpeng.daiylreader.adapter.BookShortAdapter;
import com.wulinpeng.daiylreader.category.categorydetail.contract.ICatDetailPresenter;
import com.wulinpeng.daiylreader.category.categorydetail.contract.ICatDetailView;
import com.wulinpeng.daiylreader.category.categorydetail.presenter.CatDetailPresenterImpl;
import com.wulinpeng.daiylreader.bean.BookShort;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import wulinpeng.com.framework.base.ui.BaseFragment;
import wulinpeng.com.framework.base.ui.errorview.ErrorView;
import wulinpeng.com.framework.base.ui.loadmore.LoadMoreAdapter;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午2:55
 * @description:
 */
public class CatDetailFragment extends BaseFragment implements ICatDetailView, SwipeRefreshLayout.OnRefreshListener, LoadMoreAdapter.OnLoadMoreListener {

    @BindView(R.id.error_view)
    public ErrorView errorView;

    @BindView(R.id.refresh_layout)
    public SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private BookShortAdapter adapter;

    private LoadMoreAdapter loadMoreAdapter;

    private List<BookShort> data;

    private ICatDetailPresenter presenter;

    /**
     * fragment不能直接构造传值，只能这样
     * @param major
     * @param type
     * @return
     */
    public static CatDetailFragment newInstance(String major, String gender, String type) {
        CatDetailFragment catDetailFragment = new CatDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("major", major);
        bundle.putString("gender", gender);
        bundle.putString("type", type);
        catDetailFragment.setArguments(bundle);
        return catDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CatDetailPresenterImpl(this, getArguments().getString("major"),
                getArguments().getString("gender"),
                getArguments().getString("type"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_catdetail;
    }

    @Override
    protected void initViews() {
        data = new ArrayList<>();
        adapter = new BookShortAdapter(getContext(), data);
        loadMoreAdapter = LoadMoreAdapter.Companion.wrap(getContext(), adapter, this);
        recyclerView.setAdapter(loadMoreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        errorView.bindContentView(recyclerView);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                presenter.firstLoad();
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void showLoading(boolean loading) {
        refreshLayout.setRefreshing(loading);
    }

    @Override
    public void onFirstLoadError(String msg) {
        errorView.bindDefaultErrorView(msg);
        errorView.showError(true);
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFirstLoadFinish(List<BookShort> data) {
        this.data.clear();
        this.data.addAll(data);
        if (data.isEmpty()) {
            errorView.bindDefaultErrorView("empty");
            errorView.showError(true);
        } else {
            errorView.showError(false);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMoreError(String msg) {
        loadMoreAdapter.updateLoadMoreState(LoadMoreAdapter.LoadingState.LOADING_STATE_ERROR);
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreFinish(List<BookShort> data) {
        this.data.addAll(data);
        loadMoreAdapter.updateLoadMoreState(LoadMoreAdapter.LoadingState.LOADING_STATE_NORMAL);
    }

    @Override
    public void onLoadMoreEnd() {
        loadMoreAdapter.updateLoadMoreState(LoadMoreAdapter.LoadingState.LOADING_STATE_NO_MORE);
        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        presenter.firstLoad();
    }

    @Override
    public void onLoadMore() {
        presenter.loadMore();
    }
}
