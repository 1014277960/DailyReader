package com.wulinpeng.daiylreader.categorydetail.presenter;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.category.presenter.CategoryPresenterImpl;
import com.wulinpeng.daiylreader.categorydetail.contract.ICatDetailPresenter;
import com.wulinpeng.daiylreader.categorydetail.contract.ICatDetailView;
import com.wulinpeng.daiylreader.util.RxUtil;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午2:36
 * @description:
 */
public class CatDetailPresenterImpl implements ICatDetailPresenter {

    private ICatDetailView rootView;

    private String major;

    private String gender;

    private String type;

    private int pageCount = 20;

    private int currentCount = 0;

    private boolean isEnd = false;

    public CatDetailPresenterImpl(ICatDetailView rootView, String major, String gender, String type) {
        this.rootView = rootView;
        this.major = major;
        this.gender = gender;
        this.type = type;
    }

    @Override
    public void firstLoad() {
        rootView.showLoading(true);
        ReaderApiManager.getInstance().getCatDetail(major, gender, type, 1, pageCount)
                .compose(RxUtil.rxScheduler())
                .subscribe(catDetailResponse -> {
                    rootView.showLoading(false);
                    rootView.onFirstLoadFinish(catDetailResponse.getBooks());
                    // 重置计数
                    currentCount = 0;
                    checkCount(catDetailResponse.getBooks().size());
                }, throwable -> {
                    rootView.showLoading(false);
                    rootView.onFirstLoadError(throwable.getMessage());
                });
    }

    @Override
    public void loadMore() {
        if (isEnd) {
            // 全部数据已经加载完毕
            rootView.onLoadMoreEnd();
        }
        ReaderApiManager.getInstance().getCatDetail(major, gender, type, currentCount + 1, currentCount + pageCount)
                .compose(RxUtil.rxScheduler())
                .subscribe(catDetailResponse -> {
                    rootView.showLoading(false);
                    rootView.onLoadMoreFinish(catDetailResponse.getBooks());
                    checkCount(catDetailResponse.getBooks().size());
                }, throwable -> {
                    rootView.showLoading(false);
                    rootView.onLoadMoreError(throwable.getMessage());
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
