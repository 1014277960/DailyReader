package com.wulinpeng.daiylreader.api;

import com.wulinpeng.daiylreader.entity.BookDetail;
import com.wulinpeng.daiylreader.entity.BookListResponse;
import com.wulinpeng.daiylreader.entity.BookUpdateInfo;
import com.wulinpeng.daiylreader.entity.CatResponse;
import com.wulinpeng.daiylreader.entity.ChapterDetailResponse;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;
import com.wulinpeng.daiylreader.entity.RankingResponse;
import com.wulinpeng.daiylreader.entity.RecommendBookListResponse;
import com.wulinpeng.daiylreader.entity.RecommendBookResponse;
import com.wulinpeng.daiylreader.entity.ReviewResponse;
import com.wulinpeng.daiylreader.entity.SearchResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午8:57
 * @description:
 */
public interface ReaderApi {

    /**
     * 热门书评
     * @param bookId
     * @return
     */
    @GET("post/review/best-by-book")
    Observable<ReviewResponse> getHotReview(@Query("book") String bookId);

    /**
     * 书评
     * @param bookId
     * @param start
     * @param limit
     * @return
     */
    @GET("post/review/by-book?sort=updated")
    Observable<ReviewResponse> getReview(@Query("book") String bookId, @Query("start") int start, @Query("limit") int limit);

    /**
     * 搜索小说
     * @param word
     * @param start
     * @param limit
     * @return
     */
    @GET("book/fuzzy-search")
    Observable<SearchResponse> searchBooks(@Query("query") String word, @Query("start") int start, @Query("limit") int limit);

    /**
     * 获取书籍详细信息
     * @param id
     * @return
     */
    @GET("book/{id}")
    Observable<BookDetail> getBookDetail(@Path("id") String id);

    /**
     * 获取推荐书籍
     * @param id
     * @return
     */
    @GET("/book/{id}/recommend")
    Observable<RecommendBookResponse> getRecommendBook(@Path("id") String id);

    /**
     * 获取推荐书单
     * @param id
     * @param limit
     * @return
     */
    @GET("book-list/{id}/recommend")
    Observable<RecommendBookListResponse> getRecommendBookList(@Path("id") String id, @Query("limit") int limit);

    /**
     * 获取书单内容
     * @param id
     * @return
     */
    @GET("book-list/{id}")
    Observable<BookListResponse> getBookListDetail(@Path("id") String id);

    /**
     * 获取小说章节列表
     * @param id
     * @return
     */
    @GET("mix-atoc/{id}?view=chapters")
    Observable<ChaptersResponse> getChapters(@Path("id") String id);

    /**
     * 获取小说章节内容
     * @param link
     * @return
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/{link}")
    Observable<ChapterDetailResponse> getChapterDetail(@Path("link") String link);

    /**
     * 获取小说更新信息，返回数组，id可以是多个小说id用'，'隔开
     * @param id
     * @return
     */
    @GET("book?view=updated")
    Observable<List<BookUpdateInfo>> getBookUpdateInfo(@Query("id") String id);

    /**
     * 获取分类信息
     * @return
     */
    @GET("cats/lv2/statistics")
    Observable<CatResponse> getCategoryInfo();



    @GET("ranking/{type}")
    Observable<RankingResponse> getRanking(@Path("type") String type);

}
