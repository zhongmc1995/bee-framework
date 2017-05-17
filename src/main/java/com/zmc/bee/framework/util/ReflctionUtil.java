package com.zmc.bee.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhongmc on 2017/5/16.
 * 反射工具类
 */
public final class ReflctionUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReflctionUtil.class);

    /**
     * 通过class得到类的实例对象
     * @param clz
     * @return
     */
    public static Object newInstance(Class<?> clz){
        Object instance = null;
        try {
            instance = clz.newInstance();
        } catch (Exception e) {
            LOGGER.error("reflect class failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param obj
     * @param method
     * @param params
     * @return
     */
    public static Object invokeMethod(Object obj, Method method,Object... params){
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, params);
        } catch (Exception e) {
            LOGGER.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field,Object value){
        field.setAccessible(true);
        try {
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }

}
