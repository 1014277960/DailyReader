package com.wulinpeng.daiylreader.search.contract;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午10:12
 * @description:
 */
public interface ISearchPresenter {

    public void getHotWords();

    public void getHistory(List<String> history);

    public void addHistory(String content);

    public void clearHistory();
}
