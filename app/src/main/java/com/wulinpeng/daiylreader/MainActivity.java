package com.wulinpeng.daiylreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.entity.BookListResponse;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReaderApiManager.getInstance().getChapters("51651e375a29ee6a5e0000af")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chaptersResponse-> deal(chaptersResponse));
    }

    private void deal(ChaptersResponse chaptersResponse) {
        Log.d("Debug", chaptersResponse.toString());
    }
}
