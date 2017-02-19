package com.wulinpeng.daiylreader.entity;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午2:21
 * @description:
 */
public class CatDetailResponse {

    private List<BookShort> books;

    public List<BookShort> getBooks() {
        return books;
    }

    public void setBooks(List<BookShort> books) {
        this.books = books;
    }
}
