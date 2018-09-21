package com.wulinpeng.daiylreader.rank.contract;

import com.wulinpeng.daiylreader.bean.BookShort;

import java.util.List;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午4:19
 * @description:
 */
public interface IRankDetailView extends IBaseContract.IBaseView {

    public void showLoading(boolean loading);

    public void onLoadError(String msg);

    public void onLoadFinish(List<BookShort> data);

}
