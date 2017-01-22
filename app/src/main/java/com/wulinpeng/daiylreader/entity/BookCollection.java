package com.wulinpeng.daiylreader.entity;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/22 下午1:59
 * @description:
 */
public class BookCollection {

    private List<BookUpdateInfo> collection;

    public List<BookUpdateInfo> getCollection() {
        return collection;
    }

    public void setCollection(List<BookUpdateInfo> collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "BookCollection{" +
                "collection=" + collection +
                '}';
    }
}
