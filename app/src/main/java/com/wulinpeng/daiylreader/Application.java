package com.wulinpeng.daiylreader;

import android.content.Context;

import com.wulinpeng.daiylreader.api.ReaderApiManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by wulinpeng on 16/9/7.
 */
public class Application extends android.app.Application {

     private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
