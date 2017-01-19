package com.wulinpeng.daiylreader.entity;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午1:56
 * @description:
 */
public class RankingResponse {
    /**
     * "ranking":{
     "_id":"564547c694f1c6a144ec979b",
     "updated":"2017-01-17T21:20:07.648Z",
     "title":"读者留存率 Top100",
     "tag":"zhuishuRetentionRatioMale",
     "cover":"/ranking-cover/144738093413066",
     "__v":436,
     "monthRank":"564d898f59fd983667a5e3fa",
     "totalRank":"564d8a004a15bb8369d9e28d",
     "isSub":false,
     "collapse":false,
     "new":true,
     "gender":"male",
     "priority":500,
     "created":"2015-11-13T02:15:34.000Z",
     "id":"564547c694f1c6a144ec979b"
     "books":[{
     "_id":"557e0b2bdcfc794e1a1cd8b2",
     "title":"雪鹰领主",
     "author":"我吃西红柿",
     "shortIntro":"深渊恶魔降临…… 异世界来客潜伏人间…… 神灵们在窥伺这座世界…… 然而，这是夏族统治的世界！夏族的强者们征战四方，巡守天地海洋，灭杀一切威胁！ 这群强者有一个...",
     "cover":"/cover/147521131159596",
     "cat":"玄幻",
     "site":"zhuishuvip",
     "banned":0,
     "latelyFollower":142894,
     "latelyFollowerBase":0,
     "minRetentionRatio":0,
     "retentionRatio":"76.2"
     },
     ...
     ]
     }
     */

    private Ranking ranking;

    public Ranking getRanking() {
        return ranking;
    }


    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "RankingResponse{" +
                "ranking=" + ranking +
                '}';
    }

    public static class Ranking {
        private List<BookShort> books;

        public List<BookShort> getBooks() {
            return books;
        }

        public void setBooks(List<BookShort> books) {
            this.books = books;
        }

        @Override
        public String toString() {
            return "RankingResponse{" +
                    "books=" + books +
                    '}';
        }
    }

}
