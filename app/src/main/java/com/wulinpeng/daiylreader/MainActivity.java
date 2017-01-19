package com.wulinpeng.daiylreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.base.Constant;
import com.wulinpeng.daiylreader.entity.BookListResponse;
import com.wulinpeng.daiylreader.entity.BookUpdateInfo;
import com.wulinpeng.daiylreader.entity.CatResponse;
import com.wulinpeng.daiylreader.entity.ChapterDetailResponse;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;
import com.wulinpeng.daiylreader.entity.RankingResponse;
import com.wulinpeng.daiylreader.entity.RecommendBookResponse;
import com.wulinpeng.daiylreader.entity.SearchResponse;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReaderApiManager.getInstance().getRanking(Constant.RANKING_HOT[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rankingResponse-> deal(rankingResponse));
    }

    private void deal(RankingResponse rankingResponse) {
        Log.d("Debug", rankingResponse.toString());
    }
}
