package com.wulinpeng.daiylreader.search.contract;

import com.wulinpeng.daiylreader.entity.BookShort;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午2:20
 * @description:
 */
public interface ISearchResultView {

    public void showLoading(boolean loading);

    public void onFirstLoadError(String msg);

    public void onFirstLoadFinish(List<BookShort> data);

    public void onLoadMoreError(String msg);

    public void onLoadMoreFinish(List<BookShort> data);

    public void onLoadMoreEnd();
}
