package com.zmc.bee.framework.bean;

import com.zmc.bee.framework.configuration.ConfigHelper;
import com.zmc.bee.framework.util.ClassUtil;
import com.zmc.bee.framework.web.annotaion.Controller;
import com.zmc.bee.framework.web.annotaion.Service;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhongmc on 2017/5/16.
 */
public final class ClassHelper {
    private static Set<Class<?>> classSet;
    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        classSet = ClassUtil.getClassAsSet(basePackage);
    }

    /**
     * 获取基础包下的所有类
     */
    public static Set<Class<?>> getClassAsSet(){
        return classSet;
    }

    /**
     * 获取基础包下所有的controller
     */
    public static Set<Class<?>> getControllerClassAsSet(){
        Set<Class<?>> controllerBeansSet = new HashSet<Class<?>>();
        for (Class<?> c : classSet){
            if (c.isAnnotationPresent(Controller.class)){
                controllerBeansSet.add(c);
            }
        }
        return controllerBeansSet;
    }

    /**
     * 获取基础包下所有的service
     */
    public static Set<Class<?>> getServiceClassAsSet(){
        Set<Class<?>> serviceBeansSet = new HashSet<Class<?>>();
        for (Class<?> c : classSet){
            if (c.isAnnotationPresent(Service.class)){
                serviceBeansSet.add(c);
            }
        }
        return serviceBeansSet;
    }

    /**
     * 获取后ioc容器管理的所有bean
     * @return
     */
    public static Set<Class<?>> getBeansAsSet(){
        Set<Class<?>> beansSet = new HashSet<Class<?>>();
        beansSet.addAll(getControllerClassAsSet());
        beansSet.addAll(getServiceClassAsSet());
        return beansSet;
    }

    public static void main(String[] args) {
        Set<Class<?>> controllerClassAsSet = getControllerClassAsSet();
        for (Class c : controllerClassAsSet){
            System.out.println(c.getName());
        }
    }
}
