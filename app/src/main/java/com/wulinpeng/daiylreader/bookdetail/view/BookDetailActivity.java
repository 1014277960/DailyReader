package com.wulinpeng.daiylreader.bookdetail.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.wulinpeng.daiylreader.R;

import wulinpeng.com.framework.base.ui.BaseActivity;
import com.wulinpeng.daiylreader.bookdetail.contract.IBookDetailPresenter;
import com.wulinpeng.daiylreader.bookdetail.contract.IBookDetailView;
import com.wulinpeng.daiylreader.bookdetail.presenter.BookDetailPresenterImpl;
import com.wulinpeng.daiylreader.bookdetail.ui.IntroTextView;
import com.wulinpeng.daiylreader.bean.BookDetail;

import wulinpeng.com.framework.base.ui.image.imageloader.ImageHelper;

import com.wulinpeng.daiylreader.read.view.ReadActivity;
import com.wulinpeng.daiylreader.util.TimeUtil;
import com.wulinpeng.daiylreader.util.UrlUtil;

import java.util.List;

import butterknife.BindView;
import wulinpeng.com.framework.base.ui.image.imageloader.ImageLoadEntity;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午7:11
 * @description:
 */
public class BookDetailActivity extends BaseActivity implements IBookDetailView {

    @BindView(R.id.common_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.img)
    public ImageView cover;

    @BindView(R.id.title)
    public TextView title;

    @BindView(R.id.author)
    public TextView author;

    @BindView(R.id.read_msg)
    public TextView readMsg;

    @BindView(R.id.time)
    public TextView timeView;

    @BindView(R.id.add)
    public TextView add;

    @BindView(R.id.read)
    public TextView read;

    @BindView(R.id.follow_count)
    public TextView followCount;

    @BindView(R.id.retention_ratio)
    public TextView retentionRatio;

    @BindView(R.id.serialize_count)
    public TextView serializeCount;

    @BindView(R.id.intro_view)
    public IntroTextView introTextView;

    private IBookDetailPresenter presenter;

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void initViews() {
        toolbar.setNavigationIcon(R.drawable.back_arrow_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("书籍详情");
    }

    @Override
    protected void initData() {
        presenter = new BookDetailPresenterImpl(this, this, getIntent().getStringExtra("id"));
        presenter.getBookDetail();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void onBookDetailFinish(BookDetail bookDetail) {
        ImageLoadEntity imageLoadEntity = new ImageLoadEntity(UrlUtil.getCoverUrl(bookDetail.getCover()), R.drawable.book_cover_default, cover);
        ImageHelper.INSTANCE.load(this, imageLoadEntity);
        title.setText(bookDetail.getTitle());
        author.setText(bookDetail.getAuthor());
        setReadMsg(bookDetail);
        timeView.setText(TimeUtil.getTimeInterval(bookDetail.getUpdated()));
        followCount.setText(bookDetail.getLatelyFollower() + "");
        retentionRatio.setText(bookDetail.getRetentionRatio() == null ? "暂无数据": bookDetail.getRetentionRatio() + "%");
        serializeCount.setText(bookDetail.getSerializeWordCount() == 0 ? "暂无数据": bookDetail.getSerializeWordCount() + "");
        introTextView.setText(bookDetail.getLongIntro());
        checkSelf();

        add.setOnClickListener(v -> {
            if (add.getText().toString() == "追更新") {
                presenter.addToSelf();
                add.setBackgroundResource(R.drawable.round_bg_gray);
                add.setText("不追了");
            } else {
                presenter.removeFromSelf();
                add.setBackgroundResource(R.drawable.round_bg_primary);
                add.setText("追更新");
            }
        });

        read.setOnClickListener(v -> {
            ReadActivity.startActivity(this, getIntent().getStringExtra("id"));
        });
    }

    private void checkSelf() {
        if (presenter.checkSelf()) {
            // 书架已有
            add.setBackgroundResource(R.drawable.round_bg_gray);
            add.setText("不追了");
        } else {
            add.setBackgroundResource(R.drawable.round_bg_primary);
            add.setText("追更新");
        }
    }

    @Override
    public void onError(String msg) {
    }

    private void setReadMsg(BookDetail bookDetail) {
        List<String> tags = bookDetail.getTags();
        String tag = "";
        if (tags != null && tags.size() > 0) {
            tag = tags.get(0);
        } else {
            tag = "其他";
        }
        long count = bookDetail.getWordCount();
        String wordCount = "";
        if (count > 10000) {
            wordCount = (count / 10000) + "万字";
        } else {
            wordCount = count + "字";
        }
        readMsg.setText(tag + "｜" + wordCount);
    }
}
