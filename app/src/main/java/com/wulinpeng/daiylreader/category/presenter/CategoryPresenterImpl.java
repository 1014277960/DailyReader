package com.wulinpeng.daiylreader.category.presenter;

import com.wulinpeng.daiylreader.net.ReaderApiManager;
import com.wulinpeng.daiylreader.category.contract.ICategoryPresenter;
import com.wulinpeng.daiylreader.category.contract.ICategoryView;
import com.wulinpeng.daiylreader.util.RxUtil;

import wulinpeng.com.framework.base.mvp.BasePresenter;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 上午12:04
 * @description:
 */
public class CategoryPresenterImpl extends BasePresenter<ICategoryView> implements ICategoryPresenter {

    public CategoryPresenterImpl(ICategoryView rootView) {
        super(rootView);
    }

    @Override
    public void getCatData() {
        ReaderApiManager.getInstance().getCategoryInfo()
                .compose(RxUtil.rxScheduler())
                .subscribe(catResponse -> mRootView.onCatDataLoad(catResponse),
                        throwable -> mRootView.onDataLoadFail(throwable.getMessage()));
    }
}
