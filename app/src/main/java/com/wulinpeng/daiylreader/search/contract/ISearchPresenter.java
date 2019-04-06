package com.wulinpeng.daiylreader.search.contract;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午10:12
 * @description:
 */
public interface ISearchPresenter extends IBaseContract.IBasePresenter {

    public void getHotWords();

    public void getHistory();

    public void addHistory(String content);

    public void clearHistory();
}
