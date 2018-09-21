package com.wulinpeng.daiylreader.rank.presenter;

import android.content.Context;

import com.wulinpeng.daiylreader.net.ReaderApiManager;
import com.wulinpeng.daiylreader.rank.contract.IRankDetailPresenter;
import com.wulinpeng.daiylreader.rank.contract.IRankDetailView;
import com.wulinpeng.daiylreader.util.RxUtil;

import wulinpeng.com.framework.base.mvp.BasePresenter;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午4:19
 * @description:
 */
public class RankDetailPresenterImpl extends BasePresenter<IRankDetailView> implements IRankDetailPresenter {

    private Context context;

    private String id;

    public RankDetailPresenterImpl(Context context, IRankDetailView rootView, String id) {
        super(rootView);
        this.context = context;
        this.id = id;
    }

    @Override
    public void loadData() {
        mRootView.showLoading(true);
        ReaderApiManager.getInstance().getRanking(id)
                .compose(RxUtil.rxScheduler())
                .subscribe(rankingResponse -> {
                    mRootView.onLoadFinish(rankingResponse.getRanking().getBooks());
                    mRootView.showLoading(false);
                }, throwable -> {
                    mRootView.onLoadError(throwable.getMessage());
                    mRootView.showLoading(false);
                });
    }

}
