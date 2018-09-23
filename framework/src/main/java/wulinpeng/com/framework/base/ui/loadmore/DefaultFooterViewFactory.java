package wulinpeng.com.framework.base.ui.loadmore;

import android.content.Context;

/**
 * @author wulinpeng
 * @datetime: 18/9/22 下午8:35
 * @description:
 */
public class DefaultFooterViewFactory implements IFooterViewFactory {
    @Override
    public IFooterView createFooterView(Context context) {
        return new DefaultFooterView(context);
    }
}
