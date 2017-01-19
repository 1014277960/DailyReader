package com.wulinpeng.daiylreader.entity;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午2:23
 * @description: 书籍简略信息
 */
public class BookShort {
    private String _id;
    private String title;
    private String cat;
    private String author;
    private String site;
    private String cover;
    private String shortIntro;
    private String longIntro;
    private String lastChapter;
    private String retentionRatio;
    private int latelyFollower;
    private long wordCount;

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

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
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

    public long getWordCount() {
        return wordCount;
    }

    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }

    @Override
    public String toString() {
        return "BookShort{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", cat='" + cat + '\'' +
                ", author='" + author + '\'' +
                ", site='" + site + '\'' +
                ", cover='" + cover + '\'' +
                ", shortIntro='" + shortIntro + '\'' +
                ", longIntro='" + longIntro + '\'' +
                ", lastChapter='" + lastChapter + '\'' +
                ", retentionRatio='" + retentionRatio + '\'' +
                ", latelyFollower=" + latelyFollower +
                ", wordCount=" + wordCount +
                '}';
    }
}
