package wulinpeng.com.framework.base.mvp

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 8:47 PM
 * @description:
 */
open class BasePresenter<T: IBaseContract.IBaseView>(@JvmField var mRootView: T) {
}