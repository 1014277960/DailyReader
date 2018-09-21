package com.wulinpeng.daiylreader.self.presenter;

import com.wulinpeng.daiylreader.net.ReaderApiManager;
import com.wulinpeng.daiylreader.bean.BookCollection;
import com.wulinpeng.daiylreader.manager.CacheManager;
import com.wulinpeng.daiylreader.self.contract.ISelfPresenter;
import com.wulinpeng.daiylreader.self.contract.ISelfView;
import com.wulinpeng.daiylreader.util.RxUtil;

import wulinpeng.com.framework.base.mvp.BasePresenter;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午9:16
 * @description:
 */
public class SelfPresenterImpl extends BasePresenter<ISelfView> implements ISelfPresenter {

    public SelfPresenterImpl(ISelfView mRootView) {
        super(mRootView);
    }

    @Override
    public void getCollection() {
        mRootView.showLoading(true);
        BookCollection collection = CacheManager.getInstance().getCollection();
        if (collection == null || collection.getBooks() == null || collection.getBooks().size() == 0) {
            mRootView.showLoading(false);
            mRootView.onLoadNull();
            return;
        }
        String id = "";
        for (int i = 0; i != collection.getBooks().size(); i++) {
            id += collection.getBooks().get(i).get_id();
            if (i != collection.getBooks().size() - 1) {
                id += ",";
            }
        }
        ReaderApiManager.getInstance().getBookUpdateInfo(id)
                .compose(RxUtil.rxScheduler())
                .subscribe(updateInfos -> {
                    mRootView.onCollectionFinish(collection, updateInfos);
                    mRootView.showLoading(false);
                }, throwable -> {
                    mRootView.onError(throwable.getMessage());
                    mRootView.showLoading(false);
                });
    }
}
