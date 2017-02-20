package com.wulinpeng.daiylreader.self.presenter;

import com.wulinpeng.daiylreader.api.ReaderApi;
import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.entity.BookCollection;
import com.wulinpeng.daiylreader.entity.BookDetail;
import com.wulinpeng.daiylreader.entity.BookShort;
import com.wulinpeng.daiylreader.manager.CacheManager;
import com.wulinpeng.daiylreader.search.contract.ISearchView;
import com.wulinpeng.daiylreader.self.contract.ISelfPresenter;
import com.wulinpeng.daiylreader.self.contract.ISelfView;
import com.wulinpeng.daiylreader.util.RxUtil;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午9:16
 * @description:
 */
public class SelfPresenterImpl implements ISelfPresenter {

    private ISelfView rootView;

    public SelfPresenterImpl(ISelfView rootView) {
        this.rootView = rootView;
    }

    @Override
    public void getCollection() {
        rootView.showLoading(true);
        BookCollection collection = CacheManager.getInstance().getCollection();
        if (collection == null || collection.getBooks() == null || collection.getBooks().size() == 0) {
            rootView.showLoading(false);
            rootView.onLoadNull();
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
                    rootView.onCollectionFinish(collection, updateInfos);
                    rootView.showLoading(false);
                }, throwable -> {
                    rootView.onError(throwable.getMessage());
                    rootView.showLoading(false);
                });
    }
}
