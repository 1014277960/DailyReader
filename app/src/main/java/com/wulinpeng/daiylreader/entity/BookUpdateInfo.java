package com.wulinpeng.daiylreader.entity;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午1:26
 * @description:
 */
public class BookUpdateInfo {
    /**
     * {
     "_id": "577a639246e9f25f6138c408",
     "author": "尧帝A",
     "referenceSource": "default",
     "updated": "2017-01-18T11:14:48.399Z",
     "chaptersCount": 416,
     "lastChapter": "第416章 珑之殇"
     }
     */

    private String _id;
    private String author;
    private String referenceSource;
    private String updated;
    private int chaptersCount;
    private String lastChapter;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReferenceSource() {
        return referenceSource;
    }

    public void setReferenceSource(String referenceSource) {
        this.referenceSource = referenceSource;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    @Override
    public String toString() {
        return "BookUpdateInfo{" +
                "_id='" + _id + '\'' +
                ", author='" + author + '\'' +
                ", referenceSource='" + referenceSource + '\'' +
                ", updated='" + updated + '\'' +
                ", chaptersCount=" + chaptersCount +
                ", lastChapter='" + lastChapter + '\'' +
                '}';
    }
}
