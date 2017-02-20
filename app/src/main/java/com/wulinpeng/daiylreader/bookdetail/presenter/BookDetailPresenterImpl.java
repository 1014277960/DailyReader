package com.wulinpeng.daiylreader.bookdetail.presenter;

import android.content.Context;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.bookdetail.contract.IBookDetailPresenter;
import com.wulinpeng.daiylreader.bookdetail.contract.IBookDetailView;
import com.wulinpeng.daiylreader.bookdetail.event.CollectionChangeEvent;
import com.wulinpeng.daiylreader.entity.BookCollection;
import com.wulinpeng.daiylreader.entity.BookDetail;
import com.wulinpeng.daiylreader.entity.BookShort;
import com.wulinpeng.daiylreader.manager.CacheManager;
import com.wulinpeng.daiylreader.util.RxUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午7:59
 * @description:
 */
public class BookDetailPresenterImpl implements IBookDetailPresenter {

    private Context context;

    private IBookDetailView rootView;

    private String bookId;

    private BookDetail bookDetail;

    public BookDetailPresenterImpl(Context context, IBookDetailView rootView, String bookId) {
        this.context = context;
        this.rootView = rootView;
        this.bookId = bookId;
    }

    @Override
    public void getBookDetail() {
        ReaderApiManager.getInstance().getBookDetail(bookId)
                .compose(RxUtil.rxScheduler())
                .subscribe(bookDetail -> {
                    rootView.onBookDetailFinish(bookDetail);
                    BookDetailPresenterImpl.this.bookDetail = bookDetail;
                }, throwable -> rootView.onError(throwable.getMessage()));
    }

    /**
     * 书架已经有了返回true
     * @return
     */
    @Override
    public boolean checkSelf() {
        BookCollection collection = CacheManager.getInstance().getCollection();
        if (collection == null || collection.getBooks() == null || collection.getBooks().size() == 0) {
            return false;
        }
        for (BookDetail bookDetail: collection.getBooks()) {
            if (bookDetail.get_id().equals(bookId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeFromSelf() {
        if (!checkSelf()) {
            return;
        }
        BookCollection collection = CacheManager.getInstance().getCollection();
        BookDetail book = null;
        for (BookDetail bookDetail: collection.getBooks()) {
            if (bookDetail.get_id().equals(bookId)) {
                book = bookDetail;
                break;
            }
        }
        collection.getBooks().remove(book);
        CacheManager.getInstance().saveCollection(collection);
        EventBus.getDefault().post(new CollectionChangeEvent());
    }

    @Override
    public void addToSelf() {
        if (checkSelf()) {
            return;
        }
        BookCollection collection = CacheManager.getInstance().getCollection();
        if (collection == null || collection.getBooks() == null || collection.getBooks().size() == 0) {
            collection = new BookCollection();
            collection.setBooks(new ArrayList<>());
        }
        collection.getBooks().add(bookDetail);
        CacheManager.getInstance().saveCollection(collection);
        EventBus.getDefault().post(new CollectionChangeEvent());
    }
}
