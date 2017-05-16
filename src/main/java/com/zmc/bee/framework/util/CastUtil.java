package com.zmc.bee.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhongmc on 2017/5/8.
 * 转换类型工具类
 */
public final class CastUtil {

    /**
     * 转换为String
     * @param obj
     * @return
     */
    public static String castString(Object obj){
        return castString(obj,"");
    }

    /**
     * 转化为String 带有默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj,String defaultValue){
        return null != obj ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转换为double
     * @param obj
     * @return
     */
    public static double castDouble(Object obj){
        return castDouble(obj,0.0d);
    }

    /**
     * 转换为double 带有默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj,double defaultValue){
        double doubleValue = defaultValue;
        if (null!=obj){
            String str = castString(obj);
            if (StringUtils.isNotEmpty(str)){

                try {
                    doubleValue = Double.valueOf(str);
                }catch (NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }


    /**
     * 转换为long
     * @param obj
     * @return
     */
    public static long castLong(Object obj){
        return castLong(obj,0L);
    }
    /**
     * 转化为long
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj,long defaultValue){
        long doubleValue = defaultValue;
        if (null!=obj){
            String str = castString(obj);
            if (StringUtils.isNotEmpty(str)){
                try {
                    doubleValue = Long.valueOf(str);
                }catch (NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转换为int
     * @param obj
     * @return
     */
    public static int castInt(Object obj){
        return castInt(obj,0);
    }
    /**
     * 转换为int
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj,int defaultValue){
        int doubleValue = defaultValue;
        if (null!=obj){
            String str = castString(obj);
            if (StringUtils.isNotEmpty(str)){
                try {
                    doubleValue = Integer.valueOf(str);
                }catch (NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }
    /**
     * 转化为boolean
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj){
        return castBoolean(obj, false);
    }

    /**
     * 转换为boolean 带有默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean doubleValue = defaultValue;
        if (null!=obj){
            String str = castString(obj);
            if (StringUtils.isNotEmpty(str)){
                try {
                    doubleValue = Boolean.valueOf(str);
                }catch (NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    public static void main(String[] args) {
        System.out.println(castBoolean("true"));
        System.out.println(castString(123213));
    }
}
