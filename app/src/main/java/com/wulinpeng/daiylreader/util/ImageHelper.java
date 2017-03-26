package com.wulinpeng.daiylreader.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author wulinpeng
 * @datetime: 17/3/26 下午4:10
 * @description:
 */
public class ImageHelper {

    public static void load(Context context, String url, int placeHolder, ImageView target) {
        Glide.with(context).load(url).placeholder(placeHolder).into(target);
    }

    public static void load(Context context, String url, ImageView target) {
        Glide.with(context).load(url).into(target);
    }
}
