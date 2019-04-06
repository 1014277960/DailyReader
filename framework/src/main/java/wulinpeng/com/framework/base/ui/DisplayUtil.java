package wulinpeng.com.framework.base.ui;

/**
 * @author wulinpeng
 * @datetime: 18/10/4 下午11:19
 * @description:
 */
import android.content.Context;

public class DisplayUtil {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
