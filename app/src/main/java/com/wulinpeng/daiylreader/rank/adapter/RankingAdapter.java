package com.wulinpeng.daiylreader.rank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wulinpeng.daiylreader.R;
import wulinpeng.com.framework.base.net.ApiConstant;
import com.wulinpeng.daiylreader.bean.RankingInfoResponse;
import com.wulinpeng.daiylreader.manager.imageloader.ImageLoader;
import com.wulinpeng.daiylreader.rank.view.RankDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午3:54
 * @description:
 */
public class RankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<RankingInfoResponse.Rank> data;

    private LayoutInflater inflater;

    public RankingAdapter(Context context, List<RankingInfoResponse.Rank> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rank, parent, false);
        return new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RankingViewHolder viewHolder = (RankingViewHolder) holder;
        RankingInfoResponse.Rank rank = data.get(position);
//        ImageHelper.load(context, ApiConstant.IMG_BASE_URL + rank.getCover(), viewHolder.iocn);
        ImageLoader imageLoader = new ImageLoader.Builder().url(ApiConstant.IMG_BASE_URL + rank.getCover())
                .placeHolder(R.drawable.book_cover_default)
                .target(viewHolder.iocn).build();
        com.wulinpeng.daiylreader.manager.imageloader.ImageHelper.getInstance().load(context, imageLoader);
        viewHolder.textView.setText(rank.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RankingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        public ImageView iocn;
        @BindView(R.id.text)
        public TextView textView;

        public RankingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                RankingInfoResponse.Rank rank = data.get(getLayoutPosition());
                RankDetailActivity.startActivity(context, rank.get_id(), rank.getTitle());
            });
        }
    }
}
