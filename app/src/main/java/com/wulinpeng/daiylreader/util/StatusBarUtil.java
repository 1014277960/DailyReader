package com.wulinpeng.daiylreader.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wulinpeng.daiylreader.R;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午5:41
 * @description:
 */
public class StatusBarUtil {

    /**
     * 修改状态栏的颜色
     * @param activity
     * @param statusColor
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static View compat(Activity activity, int statusColor) {
        int color = ContextCompat.getColor(activity, R.color.colorPrimaryDark);
        if (statusColor != -1) {
            color = statusColor;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(color);
            return null;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // 手动添加
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();

            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);

            decorView.addView(statusBarView, lp);

            ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);
            return statusBarView;
        }
        return null;

    }

    public static void compat(Activity activity) {
        compat(activity, -1);
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
