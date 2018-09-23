package wulinpeng.com.framework.base.ui.image.imageloader;

import android.content.Context;

import com.bumptech.glide.Glide;

/**
 * @author wulinpeng
 * @datetime: 17/4/9 下午8:15
 * @description:
 */
public class GlideImageLoaderStrategy implements IImageLoaderStrategy {
    @Override
    public void load(Context context, ImageLoadEntity imageLoadEntity) {
        Glide.with(context).load(imageLoadEntity.getUrl()).placeholder(imageLoadEntity.getPlaceHolder()).into(imageLoadEntity.getTarget());
    }
}
