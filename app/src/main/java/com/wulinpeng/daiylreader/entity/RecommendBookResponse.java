package com.wulinpeng.daiylreader.entity;

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

    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public static class Book {
        private String _id;
        private String title;
        private String author;
        private String site;
        private String cover;
        private String shortIntro;
        private String lastChapter;
        private String retentionRatio;
        private int latelyFollower;
        private String cat;
        private String majorCate;
        private String minorCate;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(int latelyFollower) {
            this.latelyFollower = latelyFollower;
        }

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public String getMajorCate() {
            return majorCate;
        }

        public void setMajorCate(String majorCate) {
            this.majorCate = majorCate;
        }

        public String getMinorCate() {
            return minorCate;
        }

        public void setMinorCate(String minorCate) {
            this.minorCate = minorCate;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", site='" + site + '\'' +
                    ", cover='" + cover + '\'' +
                    ", shortIntro='" + shortIntro + '\'' +
                    ", lastChapter='" + lastChapter + '\'' +
                    ", retentionRatio='" + retentionRatio + '\'' +
                    ", latelyFollower=" + latelyFollower +
                    ", cat='" + cat + '\'' +
                    ", majorCate='" + majorCate + '\'' +
                    ", minorCate='" + minorCate + '\'' +
                    '}';
        }
    }
}
