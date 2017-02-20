package com.wulinpeng.daiylreader.rank.contract;

import com.wulinpeng.daiylreader.entity.BookShort;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午4:19
 * @description:
 */
public interface IRankDetailView {

    public void showLoading(boolean loading);

    public void onLoadError(String msg);

    public void onLoadFinish(List<BookShort> data);

}
