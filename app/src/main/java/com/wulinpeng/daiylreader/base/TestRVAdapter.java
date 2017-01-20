package com.wulinpeng.daiylreader.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wulinpeng.daiylreader.R;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/1/19 下午8:38
 * @description:
 */
public class TestRVAdapter extends FooterRVAdapter {

    private Context context;

    private List<String> data;

    private LayoutInflater inflater;

    public TestRVAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.test_layout, parent, false);
        return new TestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TestViewHolder testViewHolder = (TestViewHolder) holder;
        testViewHolder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public TestViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
