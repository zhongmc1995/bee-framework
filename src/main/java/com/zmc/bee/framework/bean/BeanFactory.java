package com.zmc.bee.framework.bean;

/**
 * Created by zhongmc on 2017/5/16.
 */
public interface BeanFactory {
    <T> T getBean(Class<T> cls);
}
