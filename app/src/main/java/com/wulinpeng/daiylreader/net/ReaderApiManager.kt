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

import retrofit2.Retrofit
import rx.Observable
import wulinpeng.com.framework.base.net.RetrofitClient

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午9:03
 * @description:
 */
object ReaderApiManager {

    private val mReaderApi: ReaderApi

    init {
        val retrofit = RetrofitClient.client
        mReaderApi = retrofit.create(ReaderApi::class.java)
    }

    fun getHotWords(): Observable<HotWordsResponse> {
        return mReaderApi.getHotWords()
    }

    fun getCategoryInfo(): Observable<CatResponse> {
        return mReaderApi.getCategoryInfo()
    }

    fun getRankingInfo(): Observable<RankingInfoResponse> {
        return mReaderApi.getRankingInfo()
    }

    fun getHotReview(bookId: String): Observable<ReviewResponse> {
        return mReaderApi.getHotReview(bookId)
    }

    fun getReview(bookId: String, start: Int, limit: Int): Observable<ReviewResponse> {
        return mReaderApi.getReview(bookId, start, limit)
    }

    fun searchBooks(word: String, start: Int, limit: Int): Observable<SearchResponse> {
        return mReaderApi.searchBooks(word, start, limit)
    }

    fun getBookDetail(id: String): Observable<BookDetail> {
        return mReaderApi.getBookDetail(id)
    }

    fun getRecommendBook(id: String): Observable<RecommendBookResponse> {
        return mReaderApi.getRecommendBook(id)
    }

    fun getRecommendBookList(id: String, limit: Int): Observable<RecommendBookListResponse> {
        return mReaderApi.getRecommendBookList(id, limit)
    }

    fun getBookListDetail(id: String): Observable<BookListResponse> {
        return mReaderApi.getBookListDetail(id)
    }

    fun getChapters(id: String): Observable<ChaptersResponse> {
        return mReaderApi.getChapters(id)
    }

    fun getChapterDetail(link: String): Observable<ChapterDetailResponse> {
        return mReaderApi.getChapterDetail(link)
    }

    fun getBookUpdateInfo(id: String): Observable<List<BookUpdateInfo>> {
        return mReaderApi.getBookUpdateInfo(id)
    }

    fun getCatDetail(major: String, gender: String, type: String, start: Int, limit: Int): Observable<CatDetailResponse> {
        return mReaderApi.getCatDetail(major, gender, type, start, limit)
    }

    fun getRanking(type: String): Observable<RankingResponse> {
        return mReaderApi.getRanking(type)
    }
}
