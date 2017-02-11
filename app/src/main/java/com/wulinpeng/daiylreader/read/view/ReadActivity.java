package com.wulinpeng.daiylreader.read.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;
import com.wulinpeng.daiylreader.read.BookFactory;
import com.wulinpeng.daiylreader.read.contract.ReadViewInterface;
import com.wulinpeng.daiylreader.read.event.RecycleBitmapEvent;
import com.wulinpeng.daiylreader.read.presenter.ReadPresenterImp;
import com.wulinpeng.daiylreader.read.ui.ReadView;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wulinpeng
 * @datetime: 17/2/11 下午8:11
 * @description:
 */
public class ReadActivity extends AppCompatActivity implements ReadViewInterface {

    private ReadPresenterImp presenter;

    private ReadView readView;

    private BookFactory bookFactory;

    private String bookId;

    public static void startActivity(Context context, String bookId) {
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra("book_id", bookId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_read);
        bookId = getIntent().getStringExtra("book_id");

        initViews();

        presenter = new ReadPresenterImp(this);
        presenter.getChaptersInfo(bookId);
    }

    private void initViews() {
        readView = (ReadView) findViewById(R.id.read_view);
    }


    @Override
    public void onChaptersInfoSuccess(ChaptersResponse.MixToc mixToc) {
        bookFactory = new BookFactory(mixToc);
        readView.setBookFactory(bookFactory);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new RecycleBitmapEvent());
    }
}
