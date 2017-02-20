package com.wulinpeng.daiylreader.self.contract;

import com.wulinpeng.daiylreader.entity.BookCollection;
import com.wulinpeng.daiylreader.entity.BookDetail;
import com.wulinpeng.daiylreader.entity.BookShort;
import com.wulinpeng.daiylreader.entity.BookUpdateInfo;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午9:15
 * @description:
 */
public interface ISelfView {

    public void showLoading(boolean loading);

    public void onCollectionFinish(BookCollection collection, List<BookUpdateInfo> updateInfos);

    public void onLoadNull();

    public void onError(String msg);
}
