package com.wulinpeng.daiylreader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.wulinpeng.daiylreader.api.ReaderApiManager;
import com.wulinpeng.daiylreader.entity.ChaptersResponse;
import com.wulinpeng.daiylreader.read.ui.ReadView;
import com.wulinpeng.daiylreader.read.view.ReadActivity;
import com.wulinpeng.daiylreader.search.ui.FlowLayout;
import com.wulinpeng.daiylreader.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FlowLayout flowLayout;

    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ReadActivity.startActivity(this, "51651e375a29ee6a5e0000af");
        flowLayout = (FlowLayout) findViewById(R.id.flow_layout);
        for (int i = 0; i != 10; i++) {
            data.add("test   " + i);
        }
        flowLayout.setWords(data);
        flowLayout.setListener(new FlowLayout.OnTextClickListener() {
            @Override
            public void onTextClick(String content) {
                Log.d("Debug", content);
            }
        });
    }

}
