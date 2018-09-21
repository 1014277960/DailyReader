package com.wulinpeng.daiylreader.bean;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午1:49
 * @description:
 */
public class CatResponse {
    /**
     *
     {
     "male":[
     {
     "name":"玄幻",
     "bookCount":395108
     },
     {
     "name":"奇幻",
     "bookCount":40579
     },
     {
     "name":"武侠",
     "bookCount":32111
     },
     {
     "name":"仙侠",
     "bookCount":106466
     },
     {
     "name":"都市",
     "bookCount":261755
     },
     {
     "name":"职场",
     "bookCount":12147
     },
     {
     "name":"历史",
     "bookCount":54106
     },
     {
     "name":"军事",
     "bookCount":12119
     },
     {
     "name":"游戏",
     "bookCount":64189
     },
     {
     "name":"竞技",
     "bookCount":4680
     },
     {
     "name":"科幻",
     "bookCount":83080
     },
     {
     "name":"灵异",
     "bookCount":27315
     },
     {
     "name":"同人",
     "bookCount":35166
     },
     {
     "name":"轻小说",
     "bookCount":4123
     }
     ],
     "female":[
     {
     "name":"古代言情",
     "bookCount":298563
     },
     {
     "name":"现代言情",
     "bookCount":340597
     },
     {
     "name":"青春校园",
     "bookCount":88617
     },
     {
     "name":"纯爱",
     "bookCount":76905
     },
     {
     "name":"玄幻奇幻",
     "bookCount":86266
     },
     {
     "name":"武侠仙侠",
     "bookCount":45822
     },
     {
     "name":"科幻",
     "bookCount":4281
     },
     {
     "name":"游戏竞技",
     "bookCount":3884
     },
     {
     "name":"悬疑灵异",
     "bookCount":7963
     },
     {
     "name":"同人",
     "bookCount":114532
     },
     {
     "name":"女尊",
     "bookCount":12135
     },
     {
     "name":"莉莉",
     "bookCount":16506
     }
     ],
     "press":[
     {
     "name":"传记名著",
     "bookCount":1018
     },
     {
     "name":"出版小说",
     "bookCount":1931
     },
     {
     "name":"人文社科",
     "bookCount":3731
     },
     {
     "name":"生活时尚",
     "bookCount":279
     },
     {
     "name":"经管理财",
     "bookCount":2517
     },
     {
     "name":"青春言情",
     "bookCount":2550
     },
     {
     "name":"外文原版",
     "bookCount":461
     },
     {
     "name":"政治军事",
     "bookCount":64
     },
     {
     "name":"成功励志",
     "bookCount":1074
     },
     {
     "name":"育儿健康",
     "bookCount":1374
     }
     ],
     "ok":true
     }
     */

    private List<Cat> male;

    private List<Cat> female;

    private List<Cat> press;

    public List<Cat> getMale() {
        return male;
    }

    public void setMale(List<Cat> male) {
        this.male = male;
    }

    public List<Cat> getFemale() {
        return female;
    }

    public void setFemale(List<Cat> female) {
        this.female = female;
    }

    public List<Cat> getPress() {
        return press;
    }

    public void setPress(List<Cat> press) {
        this.press = press;
    }

    @Override
    public String toString() {
        return "CatResponse{" +
                "male=" + male +
                ", female=" + female +
                ", press=" + press +
                '}';
    }

    public static class Cat {
        private String name;
        private int bookCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", bookCount=" + bookCount +
                    '}';
        }
    }

}
