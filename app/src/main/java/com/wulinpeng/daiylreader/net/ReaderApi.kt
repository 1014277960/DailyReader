package com.wulinpeng.daiylreader.net

import com.wulinpeng.daiylreader.bean.BookDetail
import com.wulinpeng.daiylreader.bean.BookListResponse
import com.wulinpeng.daiylreader.bean.BookUpdateInfo
import com.wulinpeng.daiylreader.bean.CatDetailResponse
import com.wulinpeng.daiylreader.bean.CatResponse
import com.wulinpeng.daiylreader.bean.ChapterDetailResponse
import com.wulinpeng.daiylreader.bean.ChaptersResponse
import com.wulinpeng.daiylreader.bean.HotWordsResponse
import com.wulinpeng.daiylreader.bean.RankingInfoResponse
import com.wulinpeng.daiylreader.bean.RankingResponse
import com.wulinpeng.daiylreader.bean.RecommendBookListResponse
import com.wulinpeng.daiylreader.bean.RecommendBookResponse
import com.wulinpeng.daiylreader.bean.ReviewResponse
import com.wulinpeng.daiylreader.bean.SearchResponse

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午8:57
 * @description:
 */
interface ReaderApi {

    @GET("book/hot-word")
    fun getHotWords(): Observable<HotWordsResponse>

    /**
     * 获取分类信息
     * @return
     */
    @GET("cats/lv2/statistics")
    fun getCategoryInfo(): Observable<CatResponse>

    /**
     * 获取榜单信息
     * @return
     */
    @GET("ranking/gender")
    fun getRankingInfo(): Observable<RankingInfoResponse>

    /**
     * 热门书评
     * @param bookId
     * @return
     */
    @GET("post/review/best-by-book")
    fun getHotReview(@Query("book") bookId: String): Observable<ReviewResponse>

    /**
     * 书评
     * @param bookId
     * @param start
     * @param limit
     * @return
     */
    @GET("post/review/by-book?sort=updated")
    fun getReview(@Query("book") bookId: String, @Query("start") start: Int, @Query("limit") limit: Int): Observable<ReviewResponse>

    /**
     * 搜索小说
     * @param word
     * @param start
     * @param limit
     * @return
     */
    @GET("book/fuzzy-search")
    fun searchBooks(@Query("query") word: String, @Query("start") start: Int, @Query("limit") limit: Int): Observable<SearchResponse>

    /**
     * 获取书籍详细信息
     * @param id
     * @return
     */
    @GET("book/{id}")
    fun getBookDetail(@Path("id") id: String): Observable<BookDetail>

    /**
     * 获取推荐书籍
     * @param id
     * @return
     */
    @GET("/book/{id}/recommend")
    fun getRecommendBook(@Path("id") id: String): Observable<RecommendBookResponse>

    /**
     * 获取推荐书单
     * @param id
     * @param limit
     * @return
     */
    @GET("book-list/{id}/recommend")
    fun getRecommendBookList(@Path("id") id: String, @Query("limit") limit: Int): Observable<RecommendBookListResponse>

    /**
     * 获取书单内容
     * @param id
     * @return
     */
    @GET("book-list/{id}")
    fun getBookListDetail(@Path("id") id: String): Observable<BookListResponse>

    /**
     * 获取小说章节列表
     * @param id
     * @return
     */
    @GET("mix-atoc/{id}?view=chapters")
    fun getChapters(@Path("id") id: String): Observable<ChaptersResponse>

    /**
     * 获取小说章节内容
     * @param link
     * @return
     */
    @GET("http://chapter2.zhuishushenqi.com/chapter/{link}")
    fun getChapterDetail(@Path("link") link: String): Observable<ChapterDetailResponse>

    /**
     * 获取小说更新信息，返回数组，id可以是多个小说id用'，'隔开
     * @param id
     * @return
     */
    @GET("book?view=updated")
    fun getBookUpdateInfo(@Query("id") id: String): Observable<List<BookUpdateInfo>>

    /**
     * 获取具体类型小说
     * @param major
     * @param type
     * @param start
     * @param limit
     * @return
     */
    @GET("book/by-categories")
    fun getCatDetail(@Query("major") major: String, @Query("gender") gender: String, @Query("type") type: String,
                     @Query("start") start: Int, @Query("limit") limit: Int): Observable<CatDetailResponse>

    /**
     * 获取具体榜单
     * @param type
     * @return
     */
    @GET("ranking/{type}")
    fun getRanking(@Path("type") type: String): Observable<RankingResponse>

}
