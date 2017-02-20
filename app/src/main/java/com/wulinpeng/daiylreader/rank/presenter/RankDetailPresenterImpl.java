package com.wulinpeng.daiylreader.rank.presenter;

import android.content.Context;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.rank.contract.IRankDetailPresenter;
import com.wulinpeng.daiylreader.rank.contract.IRankDetailView;
import com.wulinpeng.daiylreader.search.contract.ISearchResultView;
import com.wulinpeng.daiylreader.util.RxUtil;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午4:19
 * @description:
 */
public class RankDetailPresenterImpl implements IRankDetailPresenter {

    private IRankDetailView rootView;

    private Context context;

    private String id;

    public RankDetailPresenterImpl(Context context, IRankDetailView rootView, String id) {
        this.rootView = rootView;
        this.context = context;
        this.id = id;
    }

    @Override
    public void loadData() {
        rootView.showLoading(true);
        ReaderApiManager.getInstance().getRanking(id)
                .compose(RxUtil.rxScheduler())
                .subscribe(rankingResponse -> {
                    rootView.onLoadFinish(rankingResponse.getRanking().getBooks());
                    rootView.showLoading(false);
                }, throwable -> {
                    rootView.onLoadError(throwable.getMessage());
                    rootView.showLoading(false);
                });
    }

}
