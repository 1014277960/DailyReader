package com.wulinpeng.daiylreader;

import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by wulinpeng on 16/9/7.
 */
public class Application extends android.app.Application {

     private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CrashReport.initCrashReport(getApplicationContext(), "869beebc76", false);
    }

    public static Context getContext() {
        return context;
    }
}
