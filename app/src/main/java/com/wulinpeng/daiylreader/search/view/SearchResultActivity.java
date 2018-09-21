package com.wulinpeng.daiylreader.search.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.adapter.BookShortAdapter;
import wulinpeng.com.framework.base.ui.BaseActivity;
import com.wulinpeng.daiylreader.bean.BookShort;
import com.wulinpeng.daiylreader.search.contract.ISearchResultPresenter;
import com.wulinpeng.daiylreader.search.contract.ISearchResultView;
import com.wulinpeng.daiylreader.search.presenter.SearchResultPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午2:15
 * @description:
 */
public class SearchResultActivity extends BaseActivity implements ISearchResultView {

    @BindView(R.id.common_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.refresh_layout)
    public SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private List<BookShort> data = new ArrayList<>();

    private BookShortAdapter adapter;

    private ISearchResultPresenter presenter;

    public static void startActivity(Context context, String content) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initViews() {

        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("搜索");

        presenter = new SearchResultPresenterImpl(this, this, getIntent().getStringExtra("content"));

        adapter = new BookShortAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(() -> presenter.firstLoad());

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return false;
    }

    @Override
    public void showLoading(boolean loading) {
        refreshLayout.setRefreshing(loading);
    }

    @Override
    public void onFirstLoadError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMoreFinish(List<BookShort> data) {
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreEnd() {
        adapter.setLoadingState(false);
        Toast.makeText(this, "没有更多了", Toast.LENGTH_SHORT).show();
    }
}
