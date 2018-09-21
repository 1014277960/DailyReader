package com.wulinpeng.daiylreader.read.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.net.ReaderApiManager;
import com.wulinpeng.daiylreader.bean.ChapterDetailResponse;
import com.wulinpeng.daiylreader.bean.ChaptersResponse;
import com.wulinpeng.daiylreader.manager.CacheManager;
import com.wulinpeng.daiylreader.read.event.OnChapterLoadEvent;
import com.wulinpeng.daiylreader.read.event.RecycleBitmapEvent;
import com.wulinpeng.daiylreader.util.FileUtil;
import com.wulinpeng.daiylreader.util.RxUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import static com.wulinpeng.daiylreader.Application.getContext;

/**
 * @author wulinpeng
 * @datetime: 17/3/24 下午9:35
 * @description:
 */
public class NewBookFactory {
    /**
     * 翻页的状态,如果是新章节且内存中没有那么可能会有网络请求那就是异步，就不能直接draw了，等待读取完毕再调用接口通知draw
     */
    public static final int STATE_SUCCESS = 0;
    public static final int STATE_ASYN = 1;
    public static final int STATE_NULL = 2;

    private int height;
    private int width;

    private CacheManager cacheManager;

    public int fontSize = 45;
    public int titleSize = 64;
    public Paint normalPaint;
    public Paint titlePaint;
    public int normalMargin = 30;
    private String title;

    private int contentWidth;
    private int contentHeight;
    private int lineCount;
    private List<String> lines = new ArrayList<>();

    private File chapterFile = null;
    private MappedByteBuffer mappedBuffer = null;
    private int bufferLen = 0;
    private int bufferBegin = 0;
    private int bufferEnd = 0;
    private String charset = "UTF-8";

    private Bitmap backgroundBitmap;

    // 当前章节数
    private int currentChapterIndex;
    private ChaptersResponse.MixToc mChaptersInfo;

    private boolean openFirst = true;

    public NewBookFactory(ChaptersResponse.MixToc chaptersInfo){
        cacheManager = CacheManager.getInstance();

        EventBus.getDefault().register(this);

        this.mChaptersInfo = chaptersInfo;
        getWidthAndHeight();
        normalPaint = new Paint();
        normalPaint.setTextSize(fontSize);
        normalPaint.setColor(Color.BLACK);
        normalPaint.setTextAlign(Paint.Align.LEFT);

        titlePaint = new Paint();
        titlePaint.setTextSize(titleSize);
        titlePaint.setColor(Color.DKGRAY);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.DEFAULT_BOLD);

        contentWidth = width - 2 * normalMargin;
        contentHeight = height - 2 * normalMargin;
        lineCount = contentHeight / (fontSize + normalMargin);

        backgroundBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bg_read);
    }

    private void getWidthAndHeight() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    /**
     * 打开小说某一章
     * @param chapter
     * @return
     */
    public int openBook(int chapter) {
        bufferBegin = bufferEnd = 0;

        currentChapterIndex = chapter;
        chapterFile = cacheManager.getChapterFile(mChaptersInfo.getBook(), chapter);
        if (chapterFile == null) {
            // 从网络获取
            getChapterFromNet(chapter);
            return STATE_ASYN;
        } else {
            bufferLen = (int) chapterFile.length();
            try {
                mappedBuffer = new RandomAccessFile(chapterFile, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, bufferLen);
                // 刚打开先开一页
                charset = FileUtil.getCharset(chapterFile.getAbsolutePath());
                if (openFirst) {
                    lines = pageDown();
                } else {
                    while (true) {
                        bufferBegin = bufferEnd;
                        lines = pageDown();
                        if (bufferEnd >= bufferLen) {
                            break;
                        }
                    }
                    openFirst = true;
                }
                return STATE_SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
                return STATE_NULL;
            }
        }
    }

    private void getChapterFromNet(int chapter) {
        ReaderApiManager.getInstance().getChapterDetail(mChaptersInfo.getChapters().get(chapter).getLink())
                .compose(RxUtil.rxScheduler())
                .subscribe(chapterDetailResponse -> dealResponse(chapterDetailResponse));
    }

    /**
     * 网络获取后现存到本地，然后还是走openBook
     * @param response
     */
    private void dealResponse(ChapterDetailResponse response) {
        CacheManager.getInstance().saveChapter(mChaptersInfo.getBook(), currentChapterIndex, response.getChapter());
        openBook(currentChapterIndex);
        EventBus.getDefault().post(new OnChapterLoadEvent());
    }

    // 将当前页绘制到canvas上
    public void draw(Canvas canvas) {
        if(null != lines || lines.size() > 0){
            canvas.drawBitmap(backgroundBitmap, null, new RectF(0, 0, width, height), null);
            int y = normalMargin;
            for (String strLine : lines) {
                y += normalMargin + fontSize;
                canvas.drawText(strLine, normalMargin, y, normalPaint);
            }
        }
    }

    /**
     * 下一页的逻辑，如果该下一章就判断是不是最后一章，是的话返回NULL，否则openBook，不该下一章就pageDown
     * @return
     */
    public int nextPage(){
        Log.d("Debug", currentChapterIndex + " " + mChaptersInfo.getChapters().size());
        if(bufferEnd >= bufferLen) {
            //该下一章了
            if (currentChapterIndex + 1 > mChaptersInfo.getChapters().size() - 1) {
                // 没有下一章了
                return STATE_NULL;
            }
            currentChapterIndex++;
            return openBook(currentChapterIndex);
        } else {
            bufferBegin = bufferEnd;
            lines.clear();
            lines = pageDown();
            return STATE_SUCCESS;
        }
    }

    /**
     * 和nextPage不一样，向前很特殊，所以先pageUp，再pageDown，pageUp只是让begin和end都指向上一页的起始，然后pageDown就可以
     * @return
     */
    public int prePage(){
        if(bufferBegin <= 0) {
            // 上一章
            if(currentChapterIndex == 0){
                // 没有上一章了
                return STATE_NULL;
            }
            currentChapterIndex--;
            openFirst = false;
            return openBook(currentChapterIndex);
        } else {
            lines.clear();
            pageUp();
            lines = pageDown();
            return STATE_SUCCESS;
        }
    }


    private void pageUp() {
        if (bufferBegin < 0)
            bufferBegin = 0;
        Vector<String> lines = new Vector<String>();
        String strParagraph = "";
        while (lines.size() < lineCount && bufferBegin > 0) {
            Vector<String> paraLines = new Vector<String>();
            byte[] paraBuf = getPreParagraph(bufferBegin);
            bufferBegin -= paraBuf.length;
            try {
                strParagraph = new String(paraBuf, charset);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            strParagraph = strParagraph.replaceAll("\r\n", "");
            strParagraph = strParagraph.replaceAll("\n", "");

            if (strParagraph.length() == 0) {
                paraLines.add(strParagraph);
            }
            while (strParagraph.length() > 0) {
                int nSize = normalPaint.breakText(strParagraph, true, contentWidth,
                        null);
                paraLines.add(strParagraph.substring(0, nSize));
                strParagraph = strParagraph.substring(nSize);
            }
            lines.addAll(0, paraLines);
        }
        while (lines.size() > lineCount) {
            try {
                bufferBegin += lines.get(0).getBytes(charset).length;
                lines.remove(0);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        bufferEnd = bufferBegin;
        return;
    }

    /**
     * 从end开始加载一页数据，会移动end到加载后的地方，我们需要在调用前手动begin＝end
     * @return
     */
    private List<String> pageDown() {
        String strParagraph = "";
        List<String> lines = new ArrayList<>();
        while (lines.size() < lineCount && bufferEnd < bufferLen) {
            byte[] paraBuf = getNextParagraph(bufferEnd); // 读取一个段落
            bufferEnd += paraBuf.length;
            try {
                strParagraph = new String(paraBuf, charset);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String strReturn = "";
            if (strParagraph.indexOf("\r\n") != -1) {
                strReturn = "\r\n";
                strParagraph = strParagraph.replaceAll("\r\n", "");
            } else if (strParagraph.indexOf("\n") != -1) {
                strReturn = "\n";
                strParagraph = strParagraph.replaceAll("\n", "");
            }

            if (strParagraph.length() == 0) {
                lines.add(strParagraph);
            }
            while (strParagraph.length() > 0) {
                int nSize = normalPaint.breakText(strParagraph, true, contentWidth,
                        null);
                lines.add(strParagraph.substring(0, nSize));
                strParagraph = strParagraph.substring(nSize);
                if (lines.size() >= lineCount) {
                    break;
                }
            }
            if (strParagraph.length() != 0) {
                try {
                    bufferEnd -= (strParagraph + strReturn)
                            .getBytes(charset).length;
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }

    /**
     * 读取当前尾指针之后的一段段落
     * @param bufferEnd
     * @return
     */
    private byte[] getNextParagraph(int bufferEnd) {
        byte b0;
        int i = bufferEnd;
        while (i < bufferLen) {
            b0 = mappedBuffer.get(i++);
            if (b0 == 0x0a) {
                break;
            }
        }
        int nParaSize = i - bufferEnd;
        byte[] buf = new byte[nParaSize];
        for (i = 0; i < nParaSize; i++) {
            buf[i] = mappedBuffer.get(bufferEnd + i);
        }
        return buf;
    }

    /**
     * 读取当前头指针之前的一段段落
     * @param bufferBegin
     * @return
     */
    private byte[] getPreParagraph(int bufferBegin) {
        byte b0;
        int i = bufferBegin - 1;
        while (i > 0) {
            b0 = mappedBuffer.get(i);
            if (b0 == 0x0a && i != bufferBegin - 1) {
                i++;
                break;
            }
            i--;
        }
        int nParaSize = bufferBegin - i;
        byte[] buf = new byte[nParaSize];
        for (int j = 0; j < nParaSize; j++) {
            buf[j] = mappedBuffer.get(i + j);
        }
        return buf;
    }

    public String getTitle(){
        return title;
    }

    public Bitmap getBackgroundBitmap() {
        return backgroundBitmap;
    }

    @Subscribe
    public void recycleBitmap(RecycleBitmapEvent event) {
        backgroundBitmap.recycle();
        backgroundBitmap = null;
    }

}
