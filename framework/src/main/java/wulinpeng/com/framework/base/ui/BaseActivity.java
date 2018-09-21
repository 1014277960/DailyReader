package wulinpeng.com.framework.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wulinpeng
 * @datetime: 17/2/13 下午8:13
 * @description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);
        initViews();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
