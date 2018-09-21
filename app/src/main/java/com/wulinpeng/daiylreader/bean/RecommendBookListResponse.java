package com.wulinpeng.daiylreader.bean;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午10:12
 * @description: 推荐书单
 */
public class RecommendBookListResponse {
    /**
     *  {
     "booklists": [{
     "id": "57694b2c033448f7455240e1",
     "title": "韩娱小说",
     "author": "金泰妍",
     "desc": "现在在追的小说 \n有卫星群",
     "bookCount": 227,
     "cover": "/agent/http://image.cmfu.com/books/1002400818/1002400818.jpg",
     "collectorCount": 2607
     },
     ...
     ],
     "ok": true
     }
     */

    private boolean ok;

    private List<BookList> booklists;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<BookList> getBooklists() {
        return booklists;
    }

    public void setBooklists(List<BookList> booklists) {
        this.booklists = booklists;
    }

    @Override
    public String toString() {
        return "RecommendBookListResponse{" +
                "ok=" + ok +
                ", booklists=" + booklists +
                '}';
    }

    public static class BookList {
        private String id;
        private String title;
        private String author;
        private String desc;
        private int bookCount;
        private String cover;
        private int collectorCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getCollectorCount() {
            return collectorCount;
        }

        public void setCollectorCount(int collectorCount) {
            this.collectorCount = collectorCount;
        }

        @Override
        public String toString() {
            return "BookList{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", desc='" + desc + '\'' +
                    ", bookCount=" + bookCount +
                    ", cover='" + cover + '\'' +
                    ", collectorCount=" + collectorCount +
                    '}';
        }
    }
}
