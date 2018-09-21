package com.wulinpeng.daiylreader.bean;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午9:39
 * @description:
 */
public class SearchResponse {
    /**
     * {
     "_id":"5391d6dcc3ea3fee1ee7b335",
     "hasCp":true,
     "title":"随身带着星际争霸",
     "cat":"科幻",
     "author":"暴兵对A",
     "site":"zhuishuvip",
     "cover":"/agent/http://image.cmfu.com/books/3185575/3185575.jpg",
     "shortIntro":"一觉醒来，唐方穿越到253年后的星河时代，成为一名前线炮灰士兵。 他发现自己竟然随身带着一款即时战略游戏《星际争霸2》的三族基地。 更让他欣喜若狂的是，他能够召...",
     "lastChapter":"第一千一百三十一章 额外收获",
     "retentionRatio":44.46,
     "latelyFollower":1903,
     "wordCount":4912770
     }
     */

    private boolean ok;

    private List<BookShort> books;

    public boolean isOk() {
        return ok;
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

    @Override
    public String toString() {
        return "SearchResponse{" +
                "ok=" + ok +
                ", books=" + books +
                '}';
    }

}
