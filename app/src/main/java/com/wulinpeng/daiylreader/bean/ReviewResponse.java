package com.wulinpeng.daiylreader.bean;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午8:49
 * @description:
 */
public class ReviewResponse {
    /**
     * {
     "_id": "5792892ea495cb5d0e54f10a",
     "rating": 1,
     "content": "垃圾，太太太监了已经。。。。",
     "title": "别看，一点狗比用都没有",
     "author": {
     "_id": "54e57100c8b611b640a69013",
     "avatar": "/avatar/fa/f4/faf439d05513edb2a049b1643c287093",
     "nickname": "中二",
     "type": "normal",
     "lv": 6,
     "gender": "male"
     },
     "helpful": {
     "total": 7,
     "yes": 9,
     "no": 2
     },
     "likeCount": 0,
     "state": "normal",
     "updated": "2017-01-07T04:19:46.666Z",
     "created": "2016-07-22T20:59:26.159Z",
     "commentCount": 1
     }
     */

    private boolean ok;

    private List<Review> reviews;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "ok=" + ok +
                ", reviews=" + reviews +
                '}';
    }

    public static class Review {
        private String _id;
        private int rating;
        private String content;
        private String title;
        private Author author;
        private Helpful helpful;
        private int likeCount;
        private String state;
        private String updated;
        private String created;
        private int commentCount;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public Helpful getHelpful() {
            return helpful;
        }

        public void setHelpful(Helpful helpful) {
            this.helpful = helpful;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        @Override
        public String toString() {
            return "Review{" +
                    "_id='" + _id + '\'' +
                    ", rating=" + rating +
                    ", content='" + content + '\'' +
                    ", title='" + title + '\'' +
                    ", author=" + author +
                    ", helpful=" + helpful +
                    ", likeCount=" + likeCount +
                    ", state='" + state + '\'' +
                    ", updated='" + updated + '\'' +
                    ", created='" + created + '\'' +
                    ", commentCount=" + commentCount +
                    '}';
        }
    }

    public static class Author {
        private String _id;
        private String avatar;
        private String nickname;
        private String type;
        private int lv;
        private String gender;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getLv() {
            return lv;
        }

        public void setLv(int lv) {
            this.lv = lv;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "_id='" + _id + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", type='" + type + '\'' +
                    ", lv=" + lv +
                    ", gender='" + gender + '\'' +
                    '}';
        }
    }

    public static class Helpful {
        private int yes;
        private int total;
        private int no;

        public int getYes() {
            return yes;
        }

        public void setYes(int yes) {
            this.yes = yes;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "Helpful{" +
                    "yes=" + yes +
                    ", total=" + total +
                    ", no=" + no +
                    '}';
        }
    }
}
