package com.wulinpeng.daiylreader.search.presenter

import android.content.Context
import com.wulinpeng.daiylreader.bean.SearchResponse

import com.wulinpeng.daiylreader.net.ReaderApiManager
import com.wulinpeng.daiylreader.search.contract.ISearchResultPresenter
import com.wulinpeng.daiylreader.search.contract.ISearchResultView
import com.wulinpeng.daiylreader.util.RxUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import wulinpeng.com.framework.base.mvp.BasePresenter
import java.lang.Exception

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午2:20
 * @description:
 */
class SearchResultPresenterImpl(private val context: Context, rootView: ISearchResultView, private val content: String) : BasePresenter<ISearchResultView>(rootView), ISearchResultPresenter {

    private val pageCount = 20

    private var currentCount = 0

    private var isEnd: Boolean = false

    override fun firstLoad() {
        mRootView.showLoading(true)
        currentCount = 0
        GlobalScope.launch(Dispatchers.Main) {
            try {
                var response = ReaderApiManager.searchBooks(content, currentCount, pageCount).await()
                mRootView.onFirstLoadFinish(response.books)
                checkCount(response.books.size)
                mRootView.showLoading(false)
            } catch (t: Throwable) {
                mRootView.onFirstLoadError(t.message)
                mRootView.showLoading(false)
            }
        }
    }

    override fun loadMore() {
        if (isEnd) {
            mRootView.onLoadMoreEnd()
            return
        }

        GlobalScope.launch(Dispatchers.Main) {
            try {
                var response = ReaderApiManager.searchBooks(content, currentCount, pageCount).await()
                mRootView.onLoadMoreFinish(response.books)
                checkCount(response.books.size)
                mRootView.showLoading(false)
            } catch (t: Throwable) {
                mRootView.onFirstLoadError(t.message)
                mRootView.showLoading(false)
            }
        }
    }

    /**
     * 检查数据是否全部加载完毕，并更新计数
     * @param size
     */
    private fun checkCount(size: Int) {
        if (size < pageCount) {
            isEnd = true
        }
        currentCount += size
    }
}
