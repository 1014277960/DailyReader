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
        setContentView(R.layout.activity_main);
        StatusBarUtil.compat(this, getResources().getColor(R.color.colorPrimary));

        ReaderApiManager.getInstance().getChapterDetail("http://www.17k.com/chapter/367975/8098369.html")
                .compose(RxUtil.rxScheduler())
                .subscribe(chapterDetailResponse -> deal(chapterDetailResponse));
    }

    private void deal(ChapterDetailResponse chapterDetailResponse) {
        CacheManager.getInstance().saveChapter("51651e375a29ee6a5e0000af", 1, chapterDetailResponse.getChapter());
        Log.d("Debug", CacheManager.getInstance().getChapter("51651e375a29ee6a5e0000af", 1).toString());
    }
}
