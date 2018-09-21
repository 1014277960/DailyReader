package com.wulinpeng.daiylreader.self.contract;

import com.wulinpeng.daiylreader.bean.BookCollection;
import com.wulinpeng.daiylreader.bean.BookUpdateInfo;

import java.util.List;

import wulinpeng.com.framework.base.mvp.IBaseContract;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午9:15
 * @description:
 */
public interface ISelfView extends IBaseContract.IBaseView {

    public void showLoading(boolean loading);

    public void onCollectionFinish(BookCollection collection, List<BookUpdateInfo> updateInfos);

    public void onLoadNull();

    public void onError(String msg);
}
