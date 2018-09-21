package com.wulinpeng.daiylreader.bean;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午10:42
 * @description:
 */
public class ChaptersResponse {
    /**
     *   {
     "mixToc":{
     "_id":"53a2c9fbfda0a68d82ff5e51",
     "book":"51651e375a29ee6a5e0000af",
     "chaptersUpdated":"2016-07-08T12:05:45.737Z",
     "updated":"2016-09-29T20:41:01.440Z",
     "chapters":[{
     "title":"第一章 古洞",
     "link":"http://www.17k.com/chapter/367975/8098369.html",
     "unreadble":false
     },
     ...
     ]
     },
     "ok":true
     }
     */

    private MixToc mixToc;

    private boolean ok;


    public MixToc getMixToc() {
        return mixToc;
    }

    public void setMixToc(MixToc mixToc) {
        this.mixToc = mixToc;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }


    @Override
    public String toString() {
        return "ChaptersResponse{" +
                "mixToc=" + mixToc +
                ", ok=" + ok +
                '}';
    }

    public static class MixToc {
        private String _id;
        private String book;
        private String chaptersUpdated;
        private String updated;
        private List<Chapter> chapters;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getBook() {
            return book;
        }

        public void setBook(String book) {
            this.book = book;
        }

        public String getChaptersUpdated() {
            return chaptersUpdated;
        }

        public void setChaptersUpdated(String chaptersUpdated) {
            this.chaptersUpdated = chaptersUpdated;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public List<Chapter> getChapters() {
            return chapters;
        }

        public void setChapters(List<Chapter> chapters) {
            this.chapters = chapters;
        }

        @Override
        public String toString() {
            return "MixToc{" +
                    "_id='" + _id + '\'' +
                    ", book='" + book + '\'' +
                    ", chaptersUpdated='" + chaptersUpdated + '\'' +
                    ", updated='" + updated + '\'' +
                    ", chapters=" + chapters +
                    '}';
        }
    }

    public static class Chapter {
        private String title;
        private String link;
        private boolean unreadble;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public boolean isUnreadble() {
            return unreadble;
        }

        public void setUnreadble(boolean unreadble) {
            this.unreadble = unreadble;
        }

        @Override
        public String toString() {
            return "Chapter{" +
                    "title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    ", unreadble=" + unreadble +
                    '}';
        }
    }
}
