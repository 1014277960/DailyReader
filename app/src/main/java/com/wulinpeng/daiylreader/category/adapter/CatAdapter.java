package com.wulinpeng.daiylreader.category.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.categorydetail.ui.CategoryDetailActivity;
import com.wulinpeng.daiylreader.entity.CatResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 上午12:07
 * @description:
 */
public class CatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<CatResponse.Cat> data;

    private LayoutInflater inflater;

    private String gender;

    public CatAdapter(Context context, List<CatResponse.Cat> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cat_item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CatViewHolder viewHolder = (CatViewHolder) holder;
        viewHolder.catName.setText(data.get(position).getName());
        viewHolder.bookCount.setText(data.get(position).getBookCount() + "本");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cat_name)
        TextView catName;
        @BindView(R.id.book_count)
        TextView bookCount;

        public CatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CategoryDetailActivity.startActivity(context, data.get(getLayoutPosition()).getName(), gender);
                }
            });
        }
    }
}
