package com.wulinpeng.daiylreader.search.presenter;

import android.content.Context;

import com.wulinpeng.daiylreader.net.ReaderApiManager;
import com.wulinpeng.daiylreader.search.contract.ISearchResultPresenter;
import com.wulinpeng.daiylreader.search.contract.ISearchResultView;
import com.wulinpeng.daiylreader.util.RxUtil;

import wulinpeng.com.framework.base.mvp.BasePresenter;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午2:20
 * @description:
 */
public class SearchResultPresenterImpl extends BasePresenter<ISearchResultView> implements ISearchResultPresenter {

    private Context context;

    private String content;

    private int pageCount = 20;

    private int currentCount = 0;

    private boolean isEnd;

    public SearchResultPresenterImpl(Context context, ISearchResultView rootView, String content) {
        super(rootView);
        this.context = context;
        this.content = content;
    }

    @Override
    public void firstLoad() {
        mRootView.showLoading(true);
        currentCount = 0;
        ReaderApiManager.INSTANCE.searchBooks(content, currentCount, pageCount)
                .compose(RxUtil.rxScheduler())
                .subscribe(searchResponse -> {
                    mRootView.onFirstLoadFinish(searchResponse.getBooks());
                    checkCount(searchResponse.getBooks().size());
                    mRootView.showLoading(false);
                }, throwable -> {
                    mRootView.onFirstLoadError(throwable.getMessage());
                    mRootView.showLoading(false);
                });
    }

    @Override
    public void loadMore() {
        if (isEnd) {
            mRootView.onLoadMoreEnd();
            return;
        }
        ReaderApiManager.INSTANCE.searchBooks(content, currentCount, pageCount)
                .compose(RxUtil.rxScheduler())
                .subscribe(searchResponse -> {
                    mRootView.onLoadMoreFinish(searchResponse.getBooks());
                    checkCount(searchResponse.getBooks().size());
                    mRootView.showLoading(false);
                }, throwable -> {
                    mRootView.onLoadMoreError(throwable.getMessage());
                    mRootView.showLoading(false);
                });
    }

    /**
     * 检查数据是否全部加载完毕，并更新计数
     * @param size
     */
    private void checkCount(int size) {
        if (size < pageCount) {
            isEnd = true;
        }
        currentCount += size;
    }
}
