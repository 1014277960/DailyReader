package wulinpeng.com.framework.base.ui.loadmore

import android.content.Context

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 9:03 PM
 * @description:
 */
interface IFooterViewFactory {
    fun createFooterView(context: Context): IFooterView
}