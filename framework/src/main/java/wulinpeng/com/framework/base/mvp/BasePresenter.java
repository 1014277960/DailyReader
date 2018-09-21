package wulinpeng.com.framework.base.mvp;

/**
 * @author wulinpeng
 * @datetime: 18/9/22 上午12:51
 * @description: todo 添加Model获取数据
 */
public class BasePresenter<T extends IBaseContract.IBaseView> {
    protected T mRootView;

    public BasePresenter(T mRootView) {
        this.mRootView = mRootView;
    }
}
