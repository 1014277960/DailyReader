package wulinpeng.com.framework.base.ui.image.imageloader;

import android.content.Context;

/**
 * @author wulinpeng
 * @datetime: 17/4/9 下午8:07
 * @description:
 */
public class ImageHelper {

    private IImageLoaderStrategy imageLoaderStrategy;

    private ImageHelper () {
        imageLoaderStrategy = new GlideImageLoaderStrategy();
    }

    public void load(Context context, ImageLoadEntity imageLoadEntity) {
        if (imageLoaderStrategy != null) {
            imageLoaderStrategy.load(context, imageLoadEntity);
        }
    }

    public void setImageLoaderStrategy(IImageLoaderStrategy imageLoaderStrategy) {
        this.imageLoaderStrategy = imageLoaderStrategy;
    }

    public static ImageHelper getInstance() {
        return Singleton.HOLDER.getInstance();
    }

    /**
     * 枚举单例
     */
    private enum Singleton {
        HOLDER;
        private ImageHelper instance;
        Singleton() {
            instance = new ImageHelper();
        }

        public ImageHelper getInstance() {
            return instance;
        }
    }
}
