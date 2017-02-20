package com.wulinpeng.daiylreader.bookdetail.contract;

/**
 * @author wulinpeng
 * @datetime: 17/2/20 下午7:57
 * @description:
 */
public interface IBookDetailPresenter {

    public void getBookDetail();

    public boolean checkSelf();

    public void removeFromSelf();

    public void addToSelf();
}
