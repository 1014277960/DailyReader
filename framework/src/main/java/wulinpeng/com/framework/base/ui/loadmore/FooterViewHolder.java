package wulinpeng.com.framework.base.ui.loadmore;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author wulinpeng
 * @datetime: 18/9/22 下午5:13
 * @description:
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {

    public IFooterView footView;

    public FooterViewHolder(View itemView) {
        super(itemView);
        if (!(itemView instanceof IFooterView)) {
            throw new RuntimeException("FooterViewHolder needs View instance of ILoadFootView!!");
        }
        footView = (IFooterView) itemView;
    }

    public void bind(LoadMoreAdapter.LoadingState state) {
        footView.changState(state);
    }
}
