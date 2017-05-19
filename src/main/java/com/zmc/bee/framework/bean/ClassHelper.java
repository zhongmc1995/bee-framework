package com.zmc.bee.framework.bean;

import com.zmc.bee.framework.configuration.ConfigHelper;
import com.zmc.bee.framework.util.ClassUtil;
import com.zmc.bee.framework.web.annotaion.Controller;
import com.zmc.bee.framework.web.annotaion.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhongmc on 2017/5/16.
 */
public final class ClassHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassHelper.class);
    private static Set<Class<?>> classSet;
    static {
        LOGGER.info("ClassHelper static");
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

    /**
     * 获取基础包下的指定父类或者接口的子类或者实现类
     * @param superClass 指定的父类或者接口
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> clsSet = new HashSet<Class<?>>();
        for (Class<?> c : classSet){
            if (c.isAssignableFrom(superClass) && !c.equals(superClass)){
                clsSet.add(c);
            }
        }
        return clsSet;
    }

    /**
     * 获取指定注解的类
     * @param annotaionClass
     * @return
     */
    public static Set<Class<?>> getClassSetByAnnotaion(Class<? extends Annotation> annotaionClass){
        Set<Class<?>> clsSet = new HashSet<Class<?>>();
        for (Class<?> c : classSet){
            if (c.isAnnotationPresent(annotaionClass)){
                clsSet.add(c);
            }
        }
        return clsSet;
    }

    public static void main(String[] args) {
        Set<Class<?>> controllerClassAsSet = getControllerClassAsSet();
        for (Class c : controllerClassAsSet){
            System.out.println(c.getName());
        }
    }
}
