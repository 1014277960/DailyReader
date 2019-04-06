package wulinpeng.com.framework.base.ui.image.imageloader

import android.content.Context
import com.bumptech.glide.Glide

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 8:36 PM
 * @description:
 */
class GlideImageLoaderStrategy: IImageLoaderStrategy {
    override fun load(context: Context, imageLoadEntity: ImageLoadEntity) {
        Glide.with(context).load(imageLoadEntity.url).placeholder(imageLoadEntity.placeHolder).into(imageLoadEntity.target)
    }
}