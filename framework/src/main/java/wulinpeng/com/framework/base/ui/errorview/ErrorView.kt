package wulinpeng.com.framework.base.ui.errorview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import java.lang.ref.WeakReference

/**
 * author：wulinpeng
 * date：2019-11-16 15:34
 * desc: TODO 整合一套通用的小说列表页，支持LoadMore & error & empty
 */
class ErrorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr){

    private var contentView: WeakReference<View>? = null

    fun bindDefaultErrorView(content: String) {
        val textView = TextView(context)
        textView.text = content
        bindErrorView(textView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER
        })
    }

    fun bindContentView(view: View) {
        contentView = WeakReference(view)
    }

    fun bindErrorView(view: View) {
        bindErrorView(view, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }

    fun bindErrorView(view: View, lp: FrameLayout.LayoutParams) {
        removeAllViews()
        addView(view, lp)
    }

    fun showError(showError: Boolean) {
        contentView?.get()?.visibility = if (showError) View.GONE else View.VISIBLE
        visibility = if (showError) View.VISIBLE else View.GONE
    }
}