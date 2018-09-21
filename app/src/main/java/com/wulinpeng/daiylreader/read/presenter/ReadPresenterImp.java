package com.wulinpeng.daiylreader.read.presenter;

import com.wulinpeng.daiylreader.net.ReaderApiManager;
import com.wulinpeng.daiylreader.bean.ChaptersResponse;
import com.wulinpeng.daiylreader.manager.CacheManager;
import com.wulinpeng.daiylreader.read.contract.IReadPresenter;
import com.wulinpeng.daiylreader.read.contract.IReadView;
import com.wulinpeng.daiylreader.util.RxUtil;

import rx.Observable;
import wulinpeng.com.framework.base.mvp.BasePresenter;

/**
 * @author wulinpeng
 * @datetime: 17/2/11 下午8:02
 * @description:
 */
public class ReadPresenterImp extends BasePresenter<IReadView> implements IReadPresenter {

    private ReaderApiManager apiManager;

    private CacheManager cacheManager;

    public ReadPresenterImp(IReadView rootView) {
        super(rootView);
        apiManager = ReaderApiManager.getInstance();
        cacheManager = CacheManager.getInstance();
    }

    @Override
    public void getChaptersInfo(String bookId) {
        // 从本地获取
        ChaptersResponse.MixToc info = cacheManager.getChapters(bookId);
        if (info == null || info.getChapters() == null || info.getChapters().size() == 0) {
            // 从网络获取
            apiManager.getChapters(bookId)
                    .compose(RxUtil.rxScheduler())
                    .flatMap(chaptersResponse -> Observable.just(chaptersResponse.getMixToc()))
                    .subscribe(mixToc -> onChaptersSuccess(mixToc));
        } else {
            mRootView.onChaptersInfoSuccess(info);
        }
    }

    private void onChaptersSuccess(ChaptersResponse.MixToc mixToc) {
        mRootView.onChaptersInfoSuccess(mixToc);
        // 缓存章节信息
        cacheManager.saveChapters(mixToc);
    }
}
