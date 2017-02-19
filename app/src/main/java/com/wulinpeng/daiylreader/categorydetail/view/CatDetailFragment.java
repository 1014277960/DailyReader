package com.wulinpeng.daiylreader.categorydetail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.base.BaseFragment;
import com.wulinpeng.daiylreader.base.FooterRVAdapter;
import com.wulinpeng.daiylreader.categorydetail.adapter.CatDetailAdapter;
import com.wulinpeng.daiylreader.categorydetail.contract.ICatDetailPresenter;
import com.wulinpeng.daiylreader.categorydetail.contract.ICatDetailView;
import com.wulinpeng.daiylreader.categorydetail.presenter.CatDetailPresenterImpl;
import com.wulinpeng.daiylreader.entity.BookShort;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午2:55
 * @description:
 */
public class CatDetailFragment extends BaseFragment implements ICatDetailView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refresh_layout)
    public SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private CatDetailAdapter adapter;

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
        adapter = new CatDetailAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                presenter.firstLoad();
            }
        });

        adapter.setOnLoadMoreListener(new FooterRVAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
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
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFirstLoadFinish(List<BookShort> data) {
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreError(String msg) {
        adapter.setLoadingState(false);
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreFinish(List<BookShort> data) {
        this.data.addAll(data);
        adapter.setLoadingState(false);
    }

    @Override
    public void onLoadMoreEnd() {
        adapter.setLoadingState(false);
        Toast.makeText(getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        presenter.firstLoad();
    }
}
