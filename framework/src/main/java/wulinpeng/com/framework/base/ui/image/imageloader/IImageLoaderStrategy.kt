package wulinpeng.com.framework.base.ui.image.imageloader

import android.content.Context

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 8:37 PM
 * @description:
 */
interface IImageLoaderStrategy {
    fun load(context: Context, imageLoadEntity: ImageLoadEntity)
}