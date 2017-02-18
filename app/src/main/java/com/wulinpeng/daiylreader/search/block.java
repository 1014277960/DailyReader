package com.wulinpeng.daiylreader.search;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.search.ui.FlowLayout;
import com.wulinpeng.daiylreader.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/11 下午11:56
 * @description:
 */
public class block {

    /*
            ReadActivity.startActivity(this, "51651e375a29ee6a5e0000af");
    flowLayout = (FlowLayout) findViewById(R.id.flow_layout);
        flowLayout.setPages(data);
        flowLayout.setListener(new FlowLayout.OnTextClickListener() {
        @Override
        public void onTextClick(String content) {
            Log.d("Debug", content);
        }
    });

        flowLayout.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            flowLayout.nextPage();
            return false;
        }
    });

        ReaderApiManager.getInstance().getHotWords()
                .compose(RxUtil.rxScheduler())
            .map(hotWordsResponse -> {
        String[] words = hotWordsResponse.getHotWords();
        int size = 7;
        List<List<String>> pages = new ArrayList<>();
        int currentIndex = 0;
        List<String> page = new ArrayList<>();
        for (int i = 0; i != words.length; i++) {
            if (i / size == currentIndex) {
                page.add(words[i]);
            } else {
                pages.add(page);
                page = new ArrayList<String>();
                currentIndex++;
                page.add(words[i]);
            }
        }
        if (page.size() > 0) {
            pages.add(page);
        }
        return pages;
    })
            .subscribe(pages -> flowLayout.setPages(pages));
            */
}
