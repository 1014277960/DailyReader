package com.wulinpeng.daiylreader.read.presenter;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;
import com.wulinpeng.daiylreader.manager.CacheManager;
import com.wulinpeng.daiylreader.read.contract.ReadPresenterInterface;
import com.wulinpeng.daiylreader.read.contract.ReadViewInterface;
import com.wulinpeng.daiylreader.util.RxUtil;

import okhttp3.Cache;
import rx.Observable;

/**
 * @author wulinpeng
 * @datetime: 17/2/11 下午8:02
 * @description:
 */
public class ReadPresenterImp implements ReadPresenterInterface {

    private ReadViewInterface rootView;

    private ReaderApiManager apiManager;

    private CacheManager cacheManager;

    public ReadPresenterImp(ReadViewInterface rootView) {
        this.rootView = rootView;
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
            rootView.onChaptersInfoSuccess(info);
        }
    }

    private void onChaptersSuccess(ChaptersResponse.MixToc mixToc) {
        rootView.onChaptersInfoSuccess(mixToc);
        // 缓存
        cacheManager.saveChapters(mixToc);
    }
}
