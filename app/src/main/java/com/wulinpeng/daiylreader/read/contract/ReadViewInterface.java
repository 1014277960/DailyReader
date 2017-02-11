package com.wulinpeng.daiylreader.read.contract;

import com.wulinpeng.daiylreader.entity.ChaptersResponse;

/**
 * @author wulinpeng
 * @datetime: 17/2/11 下午7:59
 * @description:
 */
public interface ReadViewInterface {
    public void onChaptersInfoSuccess(ChaptersResponse.MixToc mixToc);
}
