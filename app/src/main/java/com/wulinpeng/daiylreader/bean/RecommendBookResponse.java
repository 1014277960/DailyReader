package com.wulinpeng.daiylreader.bean;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午10:00
 * @description: 推荐书籍
 */
public class RecommendBookResponse {
    /**
     * "_id": "567274d3d0112f174788fea8",
     "title": "从一件手办开始的奇妙冒险",
     "author": "想笔名真麻烦",
     "site": "w8kana",
     "cover": "/agent/http://c.8kana.com/201510/106/13/10613_ec790_355_m.jpg",
     "shortIntro": "六条莲是一个普通高中生(笑)兼宅男。虽然世人一般对宅男的评价并不好,不过莲却莫名地对自己宅男的身份挺感到自豪的，然而...... “你不是没有手办吗？连一个手办...",
     "lastChapter": "72  池面洋平的故事（...",
     "retentionRatio": null,
     "latelyFollower": 371,
     "cat": "轻小说",
     "majorCate": "轻小说",
     "minorCate": ""
     */

    private boolean ok;

    private List<BookShort> books;

    public boolean isOk() {
        return ok;
    }

    @Override
    public String toString() {
        return "RecommendResponse{" +
                "ok=" + ok +
                ", books=" + books +
                '}';
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<BookShort> getBooks() {
        return books;
    }

    public void setBooks(List<BookShort> books) {
        this.books = books;
    }

}
