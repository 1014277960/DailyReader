package com.wulinpeng.daiylreader.category.categorydetail.contract;

import com.wulinpeng.daiylreader.bean.BookShort;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午2:16
 * @description:
 */
public interface ICatDetailView {

    public void showLoading(boolean loading);

    public void onFirstLoadError(String msg);

    public void onFirstLoadFinish(List<BookShort> data);

    public void onLoadMoreError(String msg);

    public void onLoadMoreFinish(List<BookShort> data);

    public void onLoadMoreEnd();
}
