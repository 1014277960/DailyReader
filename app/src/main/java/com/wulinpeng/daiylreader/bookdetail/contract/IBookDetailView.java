package com.wulinpeng.daiylreader.bookdetail.contract;

import com.wulinpeng.daiylreader.bean.BookDetail;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午7:57
 * @description:
 */
public interface IBookDetailView extends IBaseContract.IBaseView {

    public void onBookDetailFinish(BookDetail bookDetail);

    public void onError(String msg);
}
