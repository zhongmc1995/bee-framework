package com.zmc.bee.framework.bean;

import com.zmc.bee.framework.util.ReflctionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhongmc on 2017/5/16.
 */
public class DefaultBeanFactory /*implements BeanFactory*/ {

    private final static Map<Class<?>,Object> CONTAINER = new HashMap<Class<?>,Object>();

    //初始化bean容器
    static {
        Set<Class<?>> beansSet = ClassHelper.getBeansAsSet();
        for (Class<?> c : beansSet){
            CONTAINER.put(c, ReflctionUtil.newInstance(c));
        }
    }

    /**
     * 获取ioc容器中指定的bean
     * @param cls
     * @param <>
     * @return
     */
    /*public  <T> T getBean1(Class<T> cls) {
        if (!CONTAINER.containsKey(cls))
            throw new RuntimeException("can not get bean by class"+cls.getName());
        return (T) CONTAINER.get(cls);
    }*/

    public static Object getBean(Class<?> cls){
        if(!CONTAINER.containsKey(cls))
        throw new RuntimeException("can not get bean by class"+cls.getName());
        return CONTAINER.get(cls);
    }

    /**
     * 获取bean map
     * @return
     */
    public static Map<Class<?>,Object> getContainer(){
        return CONTAINER;
    }
}