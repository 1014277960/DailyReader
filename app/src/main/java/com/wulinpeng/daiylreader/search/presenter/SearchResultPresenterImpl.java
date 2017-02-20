package com.wulinpeng.daiylreader.search.presenter;

import android.content.Context;
import android.util.Log;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.search.contract.ISearchResultPresenter;
import com.wulinpeng.daiylreader.search.contract.ISearchResultView;
import com.wulinpeng.daiylreader.util.RxUtil;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午2:20
 * @description:
 */
public class SearchResultPresenterImpl implements ISearchResultPresenter {

    private ISearchResultView rootView;

    private Context context;

    private String content;

    private int pageCount = 20;

    private int currentCount = 0;

    private boolean isEnd;

    public SearchResultPresenterImpl(Context context, ISearchResultView rootView, String content) {
        this.rootView = rootView;
        this.context = context;
        this.content = content;
    }

    @Override
    public void firstLoad() {
        rootView.showLoading(true);
        currentCount = 0;
        ReaderApiManager.getInstance().searchBooks(content, currentCount, pageCount)
                .compose(RxUtil.rxScheduler())
                .subscribe(searchResponse -> {
                    rootView.onFirstLoadFinish(searchResponse.getBooks());
                    checkCount(searchResponse.getBooks().size());
                    rootView.showLoading(false);
                }, throwable -> {
                    rootView.onFirstLoadError(throwable.getMessage());
                    rootView.showLoading(false);
                });
    }

    @Override
    public void loadMore() {
        if (isEnd) {
            rootView.onLoadMoreEnd();
            return;
        }
        ReaderApiManager.getInstance().searchBooks(content, currentCount, pageCount)
                .compose(RxUtil.rxScheduler())
                .subscribe(searchResponse -> {
                    rootView.onLoadMoreFinish(searchResponse.getBooks());
                    checkCount(searchResponse.getBooks().size());
                    rootView.showLoading(false);
                }, throwable -> {
                    rootView.onLoadMoreError(throwable.getMessage());
                    rootView.showLoading(false);
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
