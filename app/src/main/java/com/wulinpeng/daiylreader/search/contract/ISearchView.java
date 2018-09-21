package com.wulinpeng.daiylreader.search.contract;

import java.util.List;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午10:12
 * @description:
 */
public interface ISearchView extends IBaseContract.IBaseView {

    public void onHotWordsFinish(List<List<String>> pages);

    public void onHistoryFinish(List<String> history);

    public void onError(String msg);
}
