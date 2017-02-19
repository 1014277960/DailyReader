package com.wulinpeng.daiylreader.search.presenter;

import android.content.Context;

import com.wulinpeng.daiylreader.api.ReaderApi;
import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.search.contract.ISearchPresenter;
import com.wulinpeng.daiylreader.search.contract.ISearchView;
import com.wulinpeng.daiylreader.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午10:15
 * @description:
 */
public class SearchPresenterImpl implements ISearchPresenter {

    private Context context;

    private ISearchView rootView;

    public SearchPresenterImpl(Context context, ISearchView rootView) {
        this.context = context;
        this.rootView = rootView;
    }

    @Override
    public void getHotWords() {
        ReaderApiManager.getInstance().getHotWords()
                .compose(RxUtil.rxScheduler())
                .subscribe(hotWordsResponse -> {
                    rootView.onHotWordsFinish(getPages(hotWordsResponse.getHotWords()));
                }, throwable -> rootView.onError(throwable.getMessage()));
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
    public void getHistory(List<String> history) {

    }

    @Override
    public void addHistory(String content) {

    }

    @Override
    public void clearHistory() {

    }
}
