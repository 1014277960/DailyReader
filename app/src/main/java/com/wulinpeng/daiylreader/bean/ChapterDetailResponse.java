package com.wulinpeng.daiylreader.bean;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午1:17
 * @description:
 */
public class ChapterDetailResponse {
    /**
     *   {
     "ok": true,
     "chapter": {
     "title": "第1章 苏雪",
     "body": "\n\r\n\r\n\r请安装最新版追书 以便使用优质资源",
     "isVip": false,
     "cpContent": "“谢谢老板！”凌心开心的接过老板递过来的钱，跟老板道谢着。\n\n　　老板钟发白看着眼前的凌心，对于凌心这样的年轻人心中也是也很满意。在钟发白看来这小伙子为人机灵，任务每次都完成的不错，所以钟发白也乐得多照顾一下他，每月都多发了好几百工资给凌心。\n\n　　今天是月结工资的日子，在拘谨了半个月后，凌心终于又有钱开销了。\n\n　　3000华夏币的工资，这笔收入，在华夏低级穷人区里可是不多见的。像华夏的高级穷人区，他们的每个月最高收入也就差不多3000地球币，地球币与华夏币的兑率在10倍左右。\n\n　　而高级穷人区的生活质量可是低级穷人区的百倍，此刻凌心拿到的工资可是足有高级穷人区的十分之一，这可是非常不错的了，当然凌心干的活也不是什么体面的活。\n\n！\n\n",
     "currency": 15,
     "id": "586e14be3ceecbf110f72c42"
     }
     }
     */

    private boolean ok;
    private Chapter chapter;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "ChapterDetailResponse{" +
                "ok=" + ok +
                ", chapter=" + chapter +
                '}';
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public static class Chapter {
        private String title;
        private String body;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return "Chapter{" +
                    "title='" + title + '\'' +
                    ", body='" + body + '\'' +
                    '}';
        }
    }
}
