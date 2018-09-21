package com.wulinpeng.daiylreader.read.contract;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/11 下午7:59
 * @description:
 */
public interface IReadPresenter extends IBaseContract.IBasePresenter {
    public void getChaptersInfo(String bookId);
}
