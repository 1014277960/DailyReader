package com.wulinpeng.daiylreader.net;

import com.wulinpeng.daiylreader.bean.BookDetail;
import com.wulinpeng.daiylreader.bean.BookListResponse;
import com.wulinpeng.daiylreader.bean.BookUpdateInfo;
import com.wulinpeng.daiylreader.bean.CatDetailResponse;
import com.wulinpeng.daiylreader.bean.CatResponse;
import com.wulinpeng.daiylreader.bean.ChapterDetailResponse;
import com.wulinpeng.daiylreader.bean.ChaptersResponse;
import com.wulinpeng.daiylreader.bean.HotWordsResponse;
import com.wulinpeng.daiylreader.bean.RankingInfoResponse;
import com.wulinpeng.daiylreader.bean.RankingResponse;
import com.wulinpeng.daiylreader.bean.RecommendBookListResponse;
import com.wulinpeng.daiylreader.bean.RecommendBookResponse;
import com.wulinpeng.daiylreader.bean.ReviewResponse;
import com.wulinpeng.daiylreader.bean.SearchResponse;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import wulinpeng.com.framework.base.net.RetrofitClient;

/**
 * @author wulinpeng
 * @datetime: 17/1/18 下午9:03
 * @description:
 */
public class ReaderApiManager {

    private ReaderApi mReaderApi;

    private static volatile ReaderApiManager instance;

    /**
     * 单例模式
     * @return
     */
    public static ReaderApiManager getInstance() {
        if (instance == null) {
            synchronized (ReaderApiManager.class) {
                if (instance == null) {
                    instance = new ReaderApiManager();
                }
            }
        }
        return instance;
    }

    private ReaderApiManager() {
        Retrofit retrofit = RetrofitClient.INSTANCE.getClient();
        mReaderApi = retrofit.create(ReaderApi.class);
    }

    public Observable<ReviewResponse> getHotReview(String bookId) {
        return mReaderApi.getHotReview(bookId);
    }

    public Observable<ReviewResponse> getReview(String bookId, int start, int limit) {
        return mReaderApi.getReview(bookId, start, limit);
    }

    public Observable<SearchResponse> searchBooks(String word, int start, int limit) {
        return mReaderApi.searchBooks(word, start, limit);
    }

    public Observable<HotWordsResponse> getHotWords() {
        return mReaderApi.getHotWords();
    }

    public Observable<BookDetail> getBookDetail(String id) {
        return mReaderApi.getBookDetail(id);
    }

    public Observable<RecommendBookResponse> getRecommendBook(String id) {
        return mReaderApi.getRecommendBook(id);
    }

    public Observable<RecommendBookListResponse> getRecommendBookList(String id, int limit) {
        return mReaderApi.getRecommendBookList(id, limit);
    }

    public Observable<BookListResponse> getBookListDetail( String id) {
        return mReaderApi.getBookListDetail(id);
    }

    public Observable<ChaptersResponse> getChapters(String id) {
        return mReaderApi.getChapters(id);
    }

    public Observable<ChapterDetailResponse> getChapterDetail(String link) {
        return mReaderApi.getChapterDetail(link);
    }

    public Observable<List<BookUpdateInfo>> getBookUpdateInfo(String id) {
        return mReaderApi.getBookUpdateInfo(id);
    }

    public Observable<CatResponse> getCategoryInfo() {
        return mReaderApi.getCategoryInfo();
    }

    public Observable<CatDetailResponse> getCatDetail(String major, String gender, String type, int start, int limit) {
        return mReaderApi.getCatDetail(major, gender, type, start, limit);
    }

    public Observable<RankingResponse> getRanking(String type) {
        return mReaderApi.getRanking(type);
    }

    public Observable<RankingInfoResponse> getRankingInfo() {
        return mReaderApi.getRankingInfo();
    }
}
