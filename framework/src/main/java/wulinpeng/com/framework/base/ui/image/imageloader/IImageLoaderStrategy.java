package wulinpeng.com.framework.base.ui.image.imageloader;

import android.content.Context;

/**
 * @author wulinpeng
 * @datetime: 17/4/9 下午8:14
 * @description:
 */
public interface IImageLoaderStrategy {

    public void load(Context context, ImageLoadEntity imageLoadEntity);
}
