package com.wulinpeng.daiylreader.category.presenter;

import com.wulinpeng.daiylreader.api.ReaderApi;
import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.category.contract.ICategoryPresenter;
import com.wulinpeng.daiylreader.category.contract.ICategoryView;
import com.wulinpeng.daiylreader.util.RxUtil;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 上午12:04
 * @description:
 */
public class CategoryPresenterImpl implements ICategoryPresenter {

    private ICategoryView rootView;

    public CategoryPresenterImpl(ICategoryView rootView) {
        this.rootView = rootView;
    }

    @Override
    public void getCatData() {
        ReaderApiManager.getInstance().getCategoryInfo()
                .compose(RxUtil.rxScheduler())
                .subscribe(catResponse -> rootView.onCatDataLoad(catResponse));
    }
}
