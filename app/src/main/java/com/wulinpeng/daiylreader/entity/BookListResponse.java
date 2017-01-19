package com.wulinpeng.daiylreader.entity;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午10:21
 * @description: 书单内容返回数据
 */
public class BookListResponse {
    /**
     *  {
     "bookList":{
     "_id":"57694b2c033448f7455240e1",
     "updated":"2016-06-21T14:11:56.103Z",
     "title":"韩娱小说",
     "author":{
     "_id":"55af6cfa3bf320d1146b0a3a",
     "avatar":"/avatar/dc/8f/dc8f26342a58467f6299d458d882a645",
     "nickname":"金泰妍",
     "type":"normal",
     "lv":9
     },
     "desc":"现在在追的小说",
     "gender":"male",
     "created":"2016-06-21T14:11:56.023Z",
     "tags":[
     "职场",
     "娱乐明星",
     "都市生活",
     "都市"
     ],
     "stickStopTime":null,
     "isDraft":false,
     "isDistillate":null,
     "collectorCount":2695,
     "shareLink":"http://share.zhuishushenqi.com/booklist/57694b2c033448f7455240e1",
     "id":"57694b2c033448f7455240e1",
     "books":[{
     "book":{
     "_id":"566445f61f0d92b60a80fa79",
     "title":"韩娱之演技大师",
     "author":"金印",
     "longIntro":"重生为一个韩国釜山小村庄的农村小子，老土是我的形象，邋遢是我的品味。 可一旦老子华丽转变，分分钟亮瞎你们的钛合金狗眼！ 一个农村小子一头扎进娱乐圈，从此高歌猛进！ 书友群：343510575",
     "cover":"/agent/http://image.cmfu.com/books/3663267/3663267.jpg",
     "cat":"都市",
     "site":"zhuishuvip",
     "majorCate":"职场",
     "minorCate":"娱乐明星",
     "banned":0,
     "latelyFollower":7227,
     "latelyFollowerBase":0,
     "wordCount":2280828,
     "minRetentionRatio":0,
     "retentionRatio":54.18
     },
     "comment":"好书"
     }
     ...
     ]
     },
     "ok":true
     }
     */

    private boolean ok;

    private BookList bookList;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public BookList getBookList() {
        return bookList;
    }

    public void setBookList(BookList bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "BookListResponse{" +
                "ok=" + ok +
                ", bookList=" + bookList +
                '}';
    }

    public static class BookList {
        private String _id;
        private String updated;
        private String title;
        private Author author;
        private String desc;
        private String gender;
        private String created;
        private List<String> tags;
        private int collectorCount;
        private List<BookWithComment> books;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public int getCollectorCount() {
            return collectorCount;
        }

        public void setCollectorCount(int collectorCount) {
            this.collectorCount = collectorCount;
        }

        public List<BookWithComment> getBooks() {
            return books;
        }

        public void setBooks(List<BookWithComment> books) {
            this.books = books;
        }

        @Override
        public String toString() {
            return "BookList{" +
                    "_id='" + _id + '\'' +
                    ", updated='" + updated + '\'' +
                    ", title='" + title + '\'' +
                    ", author=" + author +
                    ", desc='" + desc + '\'' +
                    ", gender='" + gender + '\'' +
                    ", created='" + created + '\'' +
                    ", tags=" + tags +
                    ", collectorCount=" + collectorCount +
                    ", books=" + books +
                    '}';
        }
    }

    public static class Author {
        private String _id;
        private String avatar;
        private String nickname;
        private String type;
        private int lv;
    }

    public static class Book {
        private String _id;
        private String title;
        private String author;
        private String longIntro;
        private String cover;
        private String cat;
        private String site;
        private String majorCate;
        private String minorCate;
        private int banned;
        private int latelyFollower;
        private int latelyFollowerBase;
        private long wordCount;
        private int minRetentionRatio;
        private float retentionRatio;

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

        public String getLongIntro() {
            return longIntro;
        }

        public void setLongIntro(String longIntro) {
            this.longIntro = longIntro;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
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

        public int getBanned() {
            return banned;
        }

        public void setBanned(int banned) {
            this.banned = banned;
        }

        public int getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(int latelyFollower) {
            this.latelyFollower = latelyFollower;
        }

        public int getLatelyFollowerBase() {
            return latelyFollowerBase;
        }

        public void setLatelyFollowerBase(int latelyFollowerBase) {
            this.latelyFollowerBase = latelyFollowerBase;
        }

        public long getWordCount() {
            return wordCount;
        }

        public void setWordCount(long wordCount) {
            this.wordCount = wordCount;
        }

        public int getMinRetentionRatio() {
            return minRetentionRatio;
        }

        public void setMinRetentionRatio(int minRetentionRatio) {
            this.minRetentionRatio = minRetentionRatio;
        }

        public float getRetentionRatio() {
            return retentionRatio;
        }

        public void setRetentionRatio(float retentionRatio) {
            this.retentionRatio = retentionRatio;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", longIntro='" + longIntro + '\'' +
                    ", cover='" + cover + '\'' +
                    ", cat='" + cat + '\'' +
                    ", site='" + site + '\'' +
                    ", majorCate='" + majorCate + '\'' +
                    ", minorCate='" + minorCate + '\'' +
                    ", banned=" + banned +
                    ", latelyFollower=" + latelyFollower +
                    ", latelyFollowerBase=" + latelyFollowerBase +
                    ", wordCount=" + wordCount +
                    ", minRetentionRatio=" + minRetentionRatio +
                    ", retentionRatio=" + retentionRatio +
                    '}';
        }
    }

    public static class BookWithComment {
        private Book book;
        private String comment;

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        @Override
        public String toString() {
            return "BookWithComment{" +
                    "book=" + book +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }
}
