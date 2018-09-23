package wulinpeng.com.framework.base.ui.image.imageloader;

import android.widget.ImageView;

/**
 * @author wulinpeng
 * @datetime: 17/4/9 下午8:07
 * @description:
 */
public class ImageLoadEntity {

    private String url;
    private int placeHolder;
    private ImageView target;

    public ImageLoadEntity(Builder builder) {
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.target = builder.target;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public ImageView getTarget() {
        return target;
    }

    public void setTarget(ImageView target) {
        this.target = target;
    }

    public static class Builder {
        private String url;
        private int placeHolder;
        private ImageView target;

        public Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder target(ImageView imageView) {
            this.target = imageView;
            return this;
        }

        public ImageLoadEntity build() {
            return new ImageLoadEntity(this);
        }
    }
}
