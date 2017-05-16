package com.zmc.bee.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhongmc on 2017/5/8.
 * 属性文件工具类
 */
public final class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载properties文件
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (inputStream==null){
                throw new FileNotFoundException(fileName+"is not found");
            }
            properties = new Properties();
            properties.load(inputStream);
        }catch (IOException e) {
            LOGGER.error("load properties file failure",e);
        }finally {
            if (null!=inputStream)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close inputstream failure",e);
                }
        }
        return properties;
    }

    /**
     * 获取key对应的 string value
     * @param properties
     * @param key
     * @return
     */
    public static String getString(Properties properties,String key){
        return getString(properties,key,"");
    }

    /**
     * 获取key对应的 string value
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties,String key,String defaultValue){
        if (properties.containsKey(key)){
            return properties.getProperty(key);
        }
        return defaultValue;
    }

    /**
     * 获取key对应的int value
     * @param properties
     * @param key
     * @return
     */
    public static int getInt(Properties properties,String key){
        return getInt(properties, key,0);
    }

    /**
     * 获取key对应的int value
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Properties properties,String key,int defaultValue){
        if (properties.containsKey(key)){
            return CastUtil.castInt(properties.get(key));
        }
        return defaultValue;
    }

    /**
     * 获取key对应的boolean value
     * @param properties
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties properties,String key){
        return getBoolean(properties,key,false);
    }

    /**
     * 获取key对应的boolean value
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Properties properties,String key,boolean defaultValue){
        if (properties.containsKey(key)){
            return CastUtil.castBoolean(properties.get(key));
        }
        return defaultValue;
    }
}
