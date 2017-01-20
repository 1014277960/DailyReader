package com.wulinpeng.daiylreader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.base.Constant;
import com.wulinpeng.daiylreader.base.TestRVAdapter;
import com.wulinpeng.daiylreader.entity.BookListResponse;
import com.wulinpeng.daiylreader.entity.BookUpdateInfo;
import com.wulinpeng.daiylreader.entity.CatResponse;
import com.wulinpeng.daiylreader.entity.ChapterDetailResponse;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;
import com.wulinpeng.daiylreader.entity.RankingResponse;
import com.wulinpeng.daiylreader.entity.RecommendBookResponse;
import com.wulinpeng.daiylreader.entity.SearchResponse;
import com.wulinpeng.daiylreader.util.RxUtil;
import com.wulinpeng.daiylreader.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    private List<String> data = new ArrayList<>();

    private TestRVAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.compat(this, getResources().getColor(R.color.colorPrimary));

        rv = (RecyclerView) findViewById(R.id.rv);
        initData();
        setupRv();
    }

    private void initData() {
        for (int i = 0; i != 20; i++) {
            data.add(i + "");
        }
    }

    private void setupRv() {
        adapter = new TestRVAdapter(this, data);
        adapter.setOnLoadMoreListener(() -> onLoad());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void onLoad() {
        data.add("loading...");
        adapter.notifyDataSetChanged();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                data.remove(data.size() - 1);
                data.add("a");
                data.add("a");
                data.add("a");
                data.add("a");
                data.add("a");
                data.add("a");
                data.add("a");
                data.add("a");
                data.add("a");
                adapter.notifyDataSetChanged();
                adapter.setLoadingState(false);
            }
        }, 10000);
    }

}
