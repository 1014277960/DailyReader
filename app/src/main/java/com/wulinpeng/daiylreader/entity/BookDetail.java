package com.wulinpeng.daiylreader.entity;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午9:49
 * @description:
 */
public class BookDetail {
    /**
     * {
     "_id": "565f903c433ac3f5197257f1",
     "title": "邪恶女神立志传",
     "author": "路人a",
     "longIntro": "他们，为了贯彻自己的原则、价值观，可以抛却一切，他们是最忠诚的狂信者，不屈的卫道士，他们是与世俗进行不懈斗争的真正英雄，解放陈规旧条的革命烈士，世人用充满敬畏的态度称呼这些有着不灭英魂的战士--变态。主角是个披着女神外皮的变态，很有爱的变态。剧情需要，人物和原版可能有所出入，认真你就输了。绝不tj，越后越精彩。变身百合小说，不喜勿入，免污贵眼。想看主角雄霸天下一身绝世神兵世所无敌谈笑间樯橹灰飞烟灭拯救世界的",
     "cover": "/agent/http://bj.bs.baidu.com/wise-novel-authority-logo/d9a36f4846fc0ce5c6972c15d22379ff.jpg",
     "cat": "其它",
     "majorCate": "其它",
     "minorCate": "",
     "_le": true,
     "allowMonthly": false,
     "allowVoucher": true,
     "allowBeanVoucher": false,
     "hasCp": false,
     "postCount": 4,
     "latelyFollower": 2745,
     "latelyFollowerBase": 0,
     "followerCount": 0,
     "wordCount": 0,
     "serializeWordCount": -1,
     "minRetentionRatio": 0,
     "retentionRatio": "7.11",
     "updated": "2015-12-03T00:43:40.598Z",
     "isSerial": false,
     "chaptersCount": 280,
     "lastChapter": "134 再会(end)(梅芙篇)",
     "gender": ["male"],
     "tags": ["玄幻"],
     "donate": false
     }
     */

    private String _id;
    private String title;
    private String author;
    private String longIntro;
    private String cover;
    private String cat;
    private String majorCate;
    private String minorCate;
    private boolean _le;
    private boolean allowMonthly;
    private boolean allowVoucher;
    private boolean allowBeanVoucher;
    private boolean hasCp;
    private int postCount;
    private int latelyFollower;
    private int latelyFollowerBase;
    private int followerCount;
    private int wordCount;
    private int serializeWordCount;
    private int minRetentionRatio;
    private String retentionRatio;
    private String updated;
    private boolean isSerial;
    private int chaptersCount;
    private String lastChapter;
    private List<String> gender;
    private List<String> tags;
    private boolean donate;

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

    public boolean is_le() {
        return _le;
    }

    public void set_le(boolean _le) {
        this._le = _le;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean isAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public boolean isAllowBeanVoucher() {
        return allowBeanVoucher;
    }

    public void setAllowBeanVoucher(boolean allowBeanVoucher) {
        this.allowBeanVoucher = allowBeanVoucher;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
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

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public int getMinRetentionRatio() {
        return minRetentionRatio;
    }

    public void setMinRetentionRatio(int minRetentionRatio) {
        this.minRetentionRatio = minRetentionRatio;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isSerial() {
        return isSerial;
    }

    public void setSerial(boolean serial) {
        isSerial = serial;
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

    public List<String> getGender() {
        return gender;
    }

    public void setGender(List<String> gender) {
        this.gender = gender;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isDonate() {
        return donate;
    }

    public void setDonate(boolean donate) {
        this.donate = donate;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "_id='" + _id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", longIntro='" + longIntro + '\'' +
                ", cover='" + cover + '\'' +
                ", cat='" + cat + '\'' +
                ", majorCate='" + majorCate + '\'' +
                ", minorCate='" + minorCate + '\'' +
                ", _le=" + _le +
                ", allowMonthly=" + allowMonthly +
                ", allowVoucher=" + allowVoucher +
                ", allowBeanVoucher=" + allowBeanVoucher +
                ", hasCp=" + hasCp +
                ", postCount=" + postCount +
                ", latelyFollower=" + latelyFollower +
                ", latelyFollowerBase=" + latelyFollowerBase +
                ", followerCount=" + followerCount +
                ", wordCount=" + wordCount +
                ", serializeWordCount=" + serializeWordCount +
                ", minRetentionRatio=" + minRetentionRatio +
                ", retentionRatio='" + retentionRatio + '\'' +
                ", updated='" + updated + '\'' +
                ", isSerial=" + isSerial +
                ", chaptersCount=" + chaptersCount +
                ", lastChapter='" + lastChapter + '\'' +
                ", gender=" + gender +
                ", tags=" + tags +
                ", donate=" + donate +
                '}';
    }
}
