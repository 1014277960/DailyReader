package wulinpeng.com.framework.base.mvp;

/**
 * @author wulinpeng
 * @datetime: 18/9/22 上午12:51
 * @description:
 */
public class BasePresenter<T extends IBaseContract.IBaseView> {
    protected T mRootView;

    public BasePresenter(T mRootView) {
        this.mRootView = mRootView;
    }
}
