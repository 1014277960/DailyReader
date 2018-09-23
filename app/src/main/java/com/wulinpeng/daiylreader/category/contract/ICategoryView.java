package com.wulinpeng.daiylreader.category.contract;

import com.wulinpeng.daiylreader.bean.CatResponse;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 上午12:03
 * @description:
 */
public interface ICategoryView extends IBaseContract.IBaseView {

    public void onCatDataLoad(CatResponse response);

    public void onDataLoadFail(String msg);
}
