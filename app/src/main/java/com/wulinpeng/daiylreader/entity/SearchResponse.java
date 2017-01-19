package com.wulinpeng.daiylreader.entity;

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

    private List<Book> books;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "ok=" + ok +
                ", books=" + books +
                '}';
    }

    public static class Book {
        private String _id;
        private boolean hasCp;
        private String title;
        private String cat;
        private String author;
        private String site;
        private String cover;
        private String shortIntro;
        private String lastChapter;
        private String retentionRatio;
        private String latelyFollower;
        private long wordCount;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public boolean isHasCp() {
            return hasCp;
        }

        public void setHasCp(boolean hasCp) {
            this.hasCp = hasCp;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getShortIntro() {
            return shortIntro;
        }

        public void setShortIntro(String shortIntro) {
            this.shortIntro = shortIntro;
        }

        public String getLastChapter() {
            return lastChapter;
        }

        public void setLastChapter(String lastChapter) {
            this.lastChapter = lastChapter;
        }

        public String getRetentionRatio() {
            return retentionRatio;
        }

        public void setRetentionRatio(String retentionRatio) {
            this.retentionRatio = retentionRatio;
        }

        public String getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(String latelyFollower) {
            this.latelyFollower = latelyFollower;
        }

        public long getWordCount() {
            return wordCount;
        }

        public void setWordCount(long wordCount) {
            this.wordCount = wordCount;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "_id='" + _id + '\'' +
                    ", hasCp=" + hasCp +
                    ", title='" + title + '\'' +
                    ", cat='" + cat + '\'' +
                    ", author='" + author + '\'' +
                    ", site='" + site + '\'' +
                    ", cover='" + cover + '\'' +
                    ", shortIntro='" + shortIntro + '\'' +
                    ", lastChapter='" + lastChapter + '\'' +
                    ", retentionRatio='" + retentionRatio + '\'' +
                    ", latelyFollower='" + latelyFollower + '\'' +
                    ", wordCount=" + wordCount +
                    '}';
        }
    }
}
