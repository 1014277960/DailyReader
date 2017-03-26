package com.wulinpeng.daiylreader.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.CharsetDecoder;

/**
 * @author wulinpeng
 * @datetime: 17/1/22 下午12:37
 * @description:
 */
public class FileUtil {

    /**
     * 缓存根目录
     * @param context
     * @return
     */
    public static String getRootPath(Context context) {
        String cacheRootPath = "";
        if (isSdCardEnable()) {
            // /sdcard/Android/data/<application package>/cache
            cacheRootPath = context.getExternalCacheDir().getPath();
        } else {
            // /data/data/<application package>/cache
            cacheRootPath = context.getCacheDir().getPath();
        }
        return cacheRootPath;
    }

    public static boolean isSdCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String getCharset(String fileName) {
        return EncodingDetect.getJavaEncode(fileName);

    }
}
