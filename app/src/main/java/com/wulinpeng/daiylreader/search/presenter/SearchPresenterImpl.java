package com.wulinpeng.daiylreader.search.presenter;

import android.content.Context;

import com.wulinpeng.daiylreader.net.ReaderApiManager;
import com.wulinpeng.daiylreader.manager.HistoryManager;
import com.wulinpeng.daiylreader.search.contract.ISearchPresenter;
import com.wulinpeng.daiylreader.search.contract.ISearchView;
import com.wulinpeng.daiylreader.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import wulinpeng.com.framework.base.mvp.BasePresenter;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午10:15
 * @description:
 */
public class SearchPresenterImpl extends BasePresenter<ISearchView> implements ISearchPresenter {

    private Context context;

    private List<String> history = new ArrayList<>();

    public SearchPresenterImpl(Context context, ISearchView rootView) {
        super(rootView);
        this.context = context;
    }

    @Override
    public void getHotWords() {
        ReaderApiManager.INSTANCE.getHotWords()
                .compose(RxUtil.rxScheduler())
                .subscribe(hotWordsResponse -> {
                    mRootView.onHotWordsFinish(getPages(hotWordsResponse.getHotWords()));
                }, throwable -> mRootView.onError(throwable.getMessage()));
    }

    private List<List<String>> getPages(String[] words) {
        int size = 7;
        List<List<String>> pages = new ArrayList<>();
        int currentIndex = 0;
        List<String> page = new ArrayList<>();
        for (int i = 0; i != words.length; i++) {
            if (i / size == currentIndex) {
                page.add(words[i]);
            } else {
                pages.add(page);
                page = new ArrayList<String>();
                currentIndex++;
                page.add(words[i]);
            }
        }
        if (page.size() > 0) {
            pages.add(page);
        }
        return pages;
    }

    @Override
    public void getHistory() {
        history.clear();
        List<String> h = HistoryManager.getHistory();
        if (h != null) {
            history.addAll(HistoryManager.getHistory());
        }
        mRootView.onHistoryFinish(history);
    }

    @Override
    public void addHistory(String content) {
        if (!history.contains(content)) {
            history.add(content);
            HistoryManager.saveHistory(history);
            mRootView.onHistoryFinish(history);
        }
    }

    @Override
    public void clearHistory() {
        history.clear();
        HistoryManager.saveHistory(history);
        mRootView.onHistoryFinish(history);
    }
}
