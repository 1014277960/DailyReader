package wulinpeng.com.framework.base.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 8:59 PM
 * @description:
 */
abstract class BaseFragment: Fragment() {

    private var mUnBinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUnBinder = ButterKnife.bind(this, view)
        initViews()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnBinder?.unbind()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initViews()

    protected abstract fun initData()
}