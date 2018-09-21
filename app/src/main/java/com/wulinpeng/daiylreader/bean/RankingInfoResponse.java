package com.wulinpeng.daiylreader.bean;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午3:45
 * @description:
 */
public class RankingInfoResponse {

    private List<Rank> male;

    private List<Rank> female;

    public List<Rank> getMale() {
        return male;
    }

    public void setMale(List<Rank> male) {
        this.male = male;
    }

    public List<Rank> getFemale() {
        return female;
    }

    public void setFemale(List<Rank> female) {
        this.female = female;
    }

    @Override
    public String toString() {
        return "RankingInfoResponse{" +
                "male=" + male +
                ", female=" + female +
                '}';
    }

    public class Rank {
        private String _id;
        private String title;
        private String cover;

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        @Override
        public String toString() {
            return "Ranks{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", cover='" + cover + '\'' +
                    '}';
        }
    }
}
