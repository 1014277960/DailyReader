package com.wulinpeng.daiylreader.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.api.ApiConstant;
import com.wulinpeng.daiylreader.base.FooterRVAdapter;
import com.wulinpeng.daiylreader.bookdetail.view.BookDetailActivity;
import com.wulinpeng.daiylreader.entity.BookShort;
import com.wulinpeng.daiylreader.util.ImageHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wulinpeng
 * @datetime: 17/2/19 下午3:05
 * @description:
 */
public class BookShortAdapter extends FooterRVAdapter {

    private Context context;

    private List<BookShort> data;

    private LayoutInflater inflater;

    public static final int TYPE_NORMAL = 0;

    public static final int TYPE_FOOTER = 1;

    public BookShortAdapter(Context context, List<BookShort> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_FOOTER) {
            view = inflater.inflate(R.layout.footer_layout, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_book_short, parent, false);
        }
        return new CatDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            return;
        }
        CatDetailViewHolder viewHolder = (CatDetailViewHolder) holder;
        BookShort bookShort = data.get(position);
        ImageHelper.load(context, ApiConstant.IMG_BASE_URL + bookShort.getCover(), R.drawable.book_cover_default, viewHolder.imageView);
        viewHolder.titleView.setText(bookShort.getTitle());
        viewHolder.authorView.setText(bookShort.getAuthor());
        viewHolder.shotIntroView.setText(bookShort.getShortIntro());
        viewHolder.readMsgView.setText(bookShort.getLatelyFollower() + "人在追｜" + bookShort.getRetentionRatio() + "%读者存留率");
    }

    @Override
    public int getItemViewType(int position) {
        if (loading && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return data.size() + (loading ? 1 : 0);
    }

    class CatDetailViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.img)
        ImageView imageView;
        @Nullable
        @BindView(R.id.title)
        TextView titleView;
        @Nullable
        @BindView(R.id.author)
        TextView authorView;
        @Nullable
        @BindView(R.id.short_intro)
        TextView shotIntroView;
        @Nullable
        @BindView(R.id.read_msg)
        TextView readMsgView;

        public CatDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                BookDetailActivity.startActivity(context, data.get(getLayoutPosition()).get_id());
            });
        }
    }

}
