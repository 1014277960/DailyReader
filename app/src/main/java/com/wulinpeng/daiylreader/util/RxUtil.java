package com.wulinpeng.daiylreader.util;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午6:55
 * @description:
 */
public class RxUtil {

    public static <T> Observable.Transformer<T, T> rxScheduler() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
