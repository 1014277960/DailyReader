package com.wulinpeng.daiylreader.search.contract;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午2:20
 * @description:
 */
public interface ISearchResultPresenter extends IBaseContract.IBasePresenter {

    public void firstLoad();

    public void loadMore();
}
