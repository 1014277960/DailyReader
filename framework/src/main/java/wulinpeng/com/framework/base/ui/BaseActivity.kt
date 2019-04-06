package wulinpeng.com.framework.base.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 8:58 PM
 * @description:
 */
abstract class BaseActivity: AppCompatActivity() {

    private var mUnBinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mUnBinder = ButterKnife.bind(this)
        initViews()
        initData()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initViews()

    protected abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        mUnBinder?.unbind()
    }
}