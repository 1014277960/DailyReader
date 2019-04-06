package wulinpeng.com.framework.base.ui.image.imageloader

import android.content.Context

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 7:46 PM
 * @description:
 */
object ImageHelper {

    private var strategy: IImageLoaderStrategy = GlideImageLoaderStrategy()

    fun load(context: Context, imageEntity: ImageLoadEntity) {
        strategy.load(context, imageEntity)
    }
}