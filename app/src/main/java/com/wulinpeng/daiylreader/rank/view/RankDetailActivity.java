package com.wulinpeng.daiylreader.rank.view;

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
import com.wulinpeng.daiylreader.base.BaseActivity;
import com.wulinpeng.daiylreader.entity.BookShort;
import com.wulinpeng.daiylreader.rank.contract.IRankDetailPresenter;
import com.wulinpeng.daiylreader.rank.contract.IRankDetailView;
import com.wulinpeng.daiylreader.rank.presenter.RankDetailPresenterImpl;
import com.wulinpeng.daiylreader.search.contract.ISearchResultPresenter;
import com.wulinpeng.daiylreader.search.presenter.SearchResultPresenterImpl;
import com.wulinpeng.daiylreader.search.view.SearchResultActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午4:16
 * @description:
 */
public class RankDetailActivity extends BaseActivity implements IRankDetailView {
    @BindView(R.id.common_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.refresh_layout)
    public SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private List<BookShort> data = new ArrayList<>();

    private BookShortAdapter adapter;

    private IRankDetailPresenter presenter;

    public static void startActivity(Context context, String id, String title) {
        Intent intent = new Intent(context, RankDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank_detail;
    }

    @Override
    protected void initViews() {

        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        presenter = new RankDetailPresenterImpl(this, this, getIntent().getStringExtra("id"));

        adapter = new BookShortAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(() -> presenter.loadData());

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                presenter.loadData();
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
    public void onLoadError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadFinish(List<BookShort> data) {
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
