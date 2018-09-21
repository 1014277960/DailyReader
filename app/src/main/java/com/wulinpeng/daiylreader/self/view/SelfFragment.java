package com.wulinpeng.daiylreader.self.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wulinpeng.daiylreader.R;
import wulinpeng.com.framework.base.ui.BaseFragment;
import com.wulinpeng.daiylreader.bookdetail.event.CollectionChangeEvent;
import com.wulinpeng.daiylreader.bean.BookCollection;
import com.wulinpeng.daiylreader.bean.BookDetail;
import com.wulinpeng.daiylreader.bean.BookUpdateInfo;
import com.wulinpeng.daiylreader.self.adapter.SelfAdapter;
import com.wulinpeng.daiylreader.self.contract.ISelfPresenter;
import com.wulinpeng.daiylreader.self.contract.ISelfView;
import com.wulinpeng.daiylreader.self.presenter.SelfPresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wulinpeng
 * @datetime: 17/2/18 下午11:38
 * @description:
 */
public class SelfFragment extends BaseFragment implements ISelfView {

    @BindView(R.id.refresh_layout)
    public SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private List<BookDetail> books = new ArrayList<>();

    private List<BookUpdateInfo> updateInfos = new ArrayList<>();

    private SelfAdapter adapter;

    private ISelfPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_self;
    }

    @Override
    protected void initViews() {
        // TODO: 18/9/22 livedatabus
        EventBus.getDefault().register(this);
        adapter = new SelfAdapter(getContext(), books, updateInfos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter = new SelfPresenterImpl(this);
        refreshLayout.setOnRefreshListener(() -> presenter.getCollection());
    }

    @Override
    protected void initData() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                presenter.getCollection();
            }
        });
    }

    @Override
    public void showLoading(boolean loading) {
        refreshLayout.setRefreshing(loading);
    }

    @Override
    public void onCollectionFinish(BookCollection collection, List<BookUpdateInfo> updateInfos) {
        this.books.clear();
        this.books.addAll(collection.getBooks());
        this.updateInfos.clear();
        this.updateInfos.addAll(updateInfos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadNull() {
        this.books.clear();
        this.updateInfos.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {
    }

    @Subscribe
    public void onCollectionChange(CollectionChangeEvent event) {
        presenter.getCollection();
    }
}
