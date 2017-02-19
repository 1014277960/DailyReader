package com.wulinpeng.daiylreader.manager;

import com.wulinpeng.daiylreader.util.CacheHelper;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午10:53
 * @description:
 */
public class HistoryManager {

    public static List<String> getHistory() {
        return CacheHelper.getInstance().getObject("history", List.class);
    }

    public static void saveHistory(List<String> history) {
        CacheHelper.getInstance().saveObject("history", history);
    }
}
