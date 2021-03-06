package com.wulinpeng.daiylreader.self.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wulinpeng.daiylreader.R;
import com.wulinpeng.daiylreader.bean.BookDetail;
import com.wulinpeng.daiylreader.bean.BookUpdateInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import wulinpeng.com.framework.base.ui.image.imageloader.ImageHelper;
import wulinpeng.com.framework.base.ui.image.imageloader.ImageLoadEntity;

import com.wulinpeng.daiylreader.read.view.ReadActivity;
import com.wulinpeng.daiylreader.search.view.SearchActivity;
import com.wulinpeng.daiylreader.util.TimeUtil;
import com.wulinpeng.daiylreader.util.UrlUtil;

import java.util.List;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午8:49
 * @description:
 */
public class SelfAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_ADD = 1;

    private Context context;

    private List<BookDetail> books;

    private List<BookUpdateInfo> updateInfos;

    private LayoutInflater inflater;

    public SelfAdapter(Context context, List<BookDetail> books, List<BookUpdateInfo> updateInfos) {
        this.context = context;
        this.books = books;
        this.updateInfos = updateInfos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ADD) {
            View view = inflater.inflate(R.layout.item_self_add, parent, false);
            return new AddViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_update_info, parent, false);
            return new UpdateInfoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ADD) {
            return;
        }
        UpdateInfoViewHolder viewHolder = (UpdateInfoViewHolder) holder;
        BookDetail bookDetail = books.get(position);
        BookUpdateInfo updateInfo = updateInfos.get(position);
        ImageLoadEntity imageLoadEntity = new ImageLoadEntity(UrlUtil.getCoverUrl(bookDetail.getCover()), R.drawable.book_cover_default, viewHolder.cover);
        ImageHelper.INSTANCE.load(context, imageLoadEntity);
        viewHolder.title.setText(bookDetail.getTitle());
        viewHolder.updateInfo.setText(TimeUtil.getTimeInterval(updateInfo.getUpdated()) + "更新:" + updateInfo.getLastChapter());
    }

    @Override
    public int getItemCount() {
        return books.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_ADD;
        }
        return TYPE_NORMAL;
    }

    class UpdateInfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        public ImageView cover;
        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.update_info)
        public TextView updateInfo;

        public UpdateInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                ReadActivity.startActivity(context, books.get(getLayoutPosition()).get_id());
            });
        }
    }

    class AddViewHolder extends RecyclerView.ViewHolder {

        public AddViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                SearchActivity.startActivity(context);
            });
        }
    }
}
