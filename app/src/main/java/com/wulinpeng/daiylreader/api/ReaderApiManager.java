package com.wulinpeng.daiylreader.api;

import com.wulinpeng.daiylreader.base.Constant;
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

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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

    public Observable<RankingResponse> getRanking(String type) {
        return mReaderApi.getRanking(type);
    }
}
