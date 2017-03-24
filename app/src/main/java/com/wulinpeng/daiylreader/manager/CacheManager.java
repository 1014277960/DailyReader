package com.wulinpeng.daiylreader.manager;

import com.wulinpeng.daiylreader.entity.BookCollection;
import com.wulinpeng.daiylreader.entity.BookDetail;
import com.wulinpeng.daiylreader.entity.BookShort;
import com.wulinpeng.daiylreader.entity.ChapterDetailResponse;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;
import com.wulinpeng.daiylreader.util.CacheHelper;

import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/22 下午1:52
 * @description: 提供与业务相关的缓存方法
 */
public class CacheManager {

    private static volatile CacheManager mInstance;

    private CacheHelper mCacheHelper;

    public CacheManager() {
        mCacheHelper = CacheHelper.getInstance();
    }

    public static CacheManager getInstance() {
        if (mInstance == null) {
            synchronized (CacheManager.class) {
                if (mInstance == null) {
                    mInstance = new CacheManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * =============================================================
     * ==================     收藏书籍       ========================
     * =============================================================
     */

    public void saveCollection(BookCollection collection) {
        mCacheHelper.saveObject("collection", collection);
    }

    public BookCollection getCollection() {
        return mCacheHelper.getObject("collection", BookCollection.class);
    }

    /**
     * =============================================================
     * ==================    书本章节列表     ========================
     * =============================================================
     */

    /**
     * 返回某一本书籍的全部信息缓存路径
     * @param bookId
     * @return
     */
    private File getBookDir(String bookId) {
        File file = new File(mCacheHelper.getRootFile(), "book");
        if (!file.exists()) {
            file.mkdir();
        }
        File bookDir = new File(file, bookId);
        if (!bookDir.exists()) {
            bookDir.mkdir();
        }
        return bookDir;
    }

    public void saveChapters(ChaptersResponse.MixToc toc) {
        File book = new File(getBookDir(toc.getBook()), "chapters");
        try {
            book.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCacheHelper.saveObject(book, toc);
    }

    public ChaptersResponse.MixToc getChapters(String bookId) {
        File chapters = new File(getBookDir(bookId), "chapters");
        if (!chapters.exists()) {
            return null;
        }
        return mCacheHelper.getObject(chapters, ChaptersResponse.MixToc.class);
    }

    /**
     * =============================================================
     * ==================    书本章节内容     ========================
     * =============================================================
     */

    public void saveChapter(String bookId, int index, ChapterDetailResponse.Chapter chapter) {
        File chapterFile = new File(getBookDir(bookId), "chapter" + index);
        try {
            chapterFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCacheHelper.saveString(chapterFile, chapter.getBody());
    }

    public ChapterDetailResponse.Chapter getChapter(String bookId, int index) {
        File chapterFile = new File(getBookDir(bookId), "chapter" + index);
        if (!chapterFile.exists()) {
            return null;
        }
        return mCacheHelper.getObject(chapterFile, ChapterDetailResponse.Chapter.class);
    }

    public File getChapterFile(String bookId, int index) {
        File chapterFile = new File(getBookDir(bookId), "chapter" + index);
        if (!chapterFile.exists()) {
            return null;
        }
        return chapterFile;
    }

}
