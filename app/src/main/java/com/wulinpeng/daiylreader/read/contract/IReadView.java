package com.wulinpeng.daiylreader.read.contract;

import com.wulinpeng.daiylreader.bean.ChaptersResponse;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/11 下午7:59
 * @description:
 */
public interface IReadView extends IBaseContract.IBaseView {
    public void onChaptersInfoSuccess(ChaptersResponse.MixToc mixToc);
}
