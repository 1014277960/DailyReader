package com.wulinpeng.daiylreader;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.entity.BookCollection;
import com.wulinpeng.daiylreader.entity.BookUpdateInfo;
import com.wulinpeng.daiylreader.entity.ChapterDetailResponse;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;
import com.wulinpeng.daiylreader.entity.SearchResponse;
import com.wulinpeng.daiylreader.manager.CacheManager;
import com.wulinpeng.daiylreader.read.ReadView;
import com.wulinpeng.daiylreader.util.CacheHelper;
import com.wulinpeng.daiylreader.util.FileUtil;
import com.wulinpeng.daiylreader.util.RxUtil;
import com.wulinpeng.daiylreader.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //StatusBarUtil.compat(this, getResources().getColor(R.color.colorPrimary));

        ReaderApiManager.getInstance().getChapters("51651e375a29ee6a5e0000af")
                .compose(RxUtil.rxScheduler())
                .subscribe(chaptersResponse -> deal(chaptersResponse));
    }

    private void deal(ChaptersResponse chaptersResponse) {
        ReadView readView = new ReadView(this, chaptersResponse.getMixToc());
        setContentView(readView);
    }
}
