package com.wulinpeng.daiylreader.bookdetail.contract;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午7:57
 * @description:
 */
public interface IBookDetailPresenter extends IBaseContract.IBasePresenter {

    public void getBookDetail();

    public boolean checkSelf();

    public void removeFromSelf();

    public void addToSelf();
}
