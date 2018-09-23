package com.wulinpeng.daiylreader.util;

import wulinpeng.com.framework.base.net.ApiConstant;

/**
 * @author wulinpeng
 * @datetime: 18/9/23 下午4:35
 * @description:
 */
public class UrlUtil {

    public static String getCoverUrl(String coverPath) {
        if (coverPath.startsWith("/")) {
            // 容错，删除path前的'/'
            coverPath = coverPath.substring(1);
        }
        return ApiConstant.IMG_BASE_URL + coverPath;
    }
}
