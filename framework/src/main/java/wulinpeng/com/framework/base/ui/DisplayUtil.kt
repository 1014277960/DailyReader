package wulinpeng.com.framework.base.ui

import android.content.Context

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 8:56 PM
 * @description:
 */
object DisplayUtil {

    @JvmStatic
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}