package com.wulinpeng.daiylreader.util;

import com.wulinpeng.daiylreader.Application;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author wulinpeng
 * @datetime: 17/1/22 下午12:34
 * @description: 与业务无关的缓存工具类，提供基本缓存方法
 */
public class CacheHelper {

    private static volatile CacheHelper mInstance;

    private File mRootDir;

    private ObjectMapper mMapper;

    private CacheHelper(String rootPath) {
        mRootDir = new File(rootPath);
        if (!mRootDir.exists()) {
            mRootDir.mkdir();
        }

        mMapper = new ObjectMapper();
    }

    public static CacheHelper getInstance() {
        if (mInstance == null) {
            synchronized (CacheHelper.class) {
                if (mInstance == null) {
                    mInstance = new CacheHelper(FileUtil.getRootPath(Application.getContext()));
                }
            }
        }
        return mInstance;
    }

    public File getRootFile() {
        return mRootDir;
    }

    /**
     * 在缓存根目录下保存对象
     * @param fileName
     * @param o
     */
    public void saveObject(String fileName, Object o) {
        File file = new File(mRootDir, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveObject(file, o);
    }

    /**
     * 在缓存根目录下新建文件下并缓存文件
     * @param dirName
     * @param fileName
     * @param o
     */
    public void saveObject(String dirName, String fileName, Object o) {
        File file = new File(mRootDir, dirName);
        if (!file.exists()) {
            file.mkdir();
        }
        File f = new File(file, fileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveObject(f, o);
    }

    /**
     * 在任意文件下保存对象保存对象
     * @param file
     * @param o
     */
    public void saveObject(File file, Object o) {
        String content = "";
        try {
            content = mMapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveString(file, content);
    }

    /**
     * 在任意文件下保存字符串数据
     * @param file
     * @param content
     */
    public void saveString(File file, String content) {
        FileOutputStream fos = null;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 在缓存根目录下获得类型为T的对象
     * @param fileName
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getObject(String fileName, Class<T> tClass) {
        File file = new File(mRootDir, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return getObject(file, tClass);
    }

    /**
     * 在缓存根目录下的文件夹中得到T对象
     * @param dirName
     * @param fileName
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getObject(String dirName, String fileName, Class<T> tClass) {
        File file = new File(mRootDir, dirName);
        if (!file.exists()) {
            file.mkdir();
        }
        File f = new File(file, fileName);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return getObject(f, tClass);
    }

    /**
     * 从任意文件夹获得对象
     * @param file
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getObject(File file, Class<T> tClass) {
        try {
            return mMapper.readValue(file, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
