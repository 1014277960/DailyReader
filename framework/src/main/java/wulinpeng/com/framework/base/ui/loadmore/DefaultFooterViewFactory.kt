package wulinpeng.com.framework.base.ui.loadmore

import android.content.Context

/**
 * @author wulinpeng
 * @datetime: 18/9/22 下午8:35
 * @description:
 */
class DefaultFooterViewFactory : IFooterViewFactory {
    override fun createFooterView(context: Context): IFooterView {
        return DefaultFooterView(context)
    }
}
