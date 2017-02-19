package com.wulinpeng.daiylreader.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wulinpeng.daiylreader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wulinpeng
 * @datetime: 17/2/13 下午8:13
 * @description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.common_toolbar)
    public Toolbar toolbar;

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.back_arrow);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.setTitleTextColor(Color.WHITE);
        }
        initViews();
        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

}
