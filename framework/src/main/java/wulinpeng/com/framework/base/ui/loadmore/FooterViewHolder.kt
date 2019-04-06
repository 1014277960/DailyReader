package wulinpeng.com.framework.base.ui.loadmore

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 9:04 PM
 * @description:
 */
class FooterViewHolder(var item: View): RecyclerView.ViewHolder(item) {

    var footView: IFooterView

    init {
        if (itemView !is IFooterView) {
            throw RuntimeException("FooterViewHolder needs View instance of ILoadFootView!!")
        }
        footView = itemView
    }

    fun bind(state: LoadMoreAdapter.LoadingState) {
        footView.changState(state)
    }
}