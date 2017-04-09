package com.wulinpeng.daiylreader.manager.imageloader;

import android.content.Context;

import com.bumptech.glide.Glide;

/**
 * @author wulinpeng
 * @datetime: 17/4/9 下午8:15
 * @description:
 */
public class GlideImageLoaderStrategy implements IImageLoaderStrategy {
    @Override
    public void load(Context context, ImageLoader imageLoader) {
        Glide.with(context).load(imageLoader.getUrl()).placeholder(imageLoader.getPlaceHolder()).into(imageLoader.getTarget());
    }
}
