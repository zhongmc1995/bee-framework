package com.zmc.bee.framework.aop.core;

import com.zmc.bee.framework.aop.annotation.Aspect;
import com.zmc.bee.framework.aop.common.Impl.AspectProxy;
import com.zmc.bee.framework.aop.common.Proxy;
import com.zmc.bee.framework.bean.ClassHelper;
import com.zmc.bee.framework.bean.DefaultBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by zhongmc on 2017/5/22.
 */
public final class AopHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);
    static {
        try {
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(createProxyMap());
            for (Map.Entry<Class<?>,List<Proxy>> entry : targetMap.entrySet()){
                Class<?> targetClass = entry.getKey();
                List<Proxy> proxyList = entry.getValue();

                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                DefaultBeanFactory.setBean(targetClass,proxy);
            }
        }catch (Exception e){
            LOGGER.error("load aophelper failure",e);
        }

    }


    /**
     * 扫描被aspect注解value值所注解的所有类
     * such as：@Aspect("Controller.class")
     * @param aspect
     * @return
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception{
        Class<? extends Annotation> value = aspect.value();
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        if (value!=null && !value.equals(aspect)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotaion(aspect.value()));
        }
        return targetClassSet;
    }

    /**
     * 建立切面类和要代理的类的映射
     *
     * @return
     * @throws Exception
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> c : proxyClassSet){
            if (c.isAnnotationPresent(Aspect.class)){
                Aspect aspect = c.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(c,targetClassSet);
            }
        }
        return proxyMap;
    }

    /**
     * 建立代理目标类和切面类的映射
     * @param proxyMap
     * @return
     * @throws Exception
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>,Set<Class<?>>> entry : proxyMap.entrySet()){
            Class<?> proxyClass = entry.getKey();
            Set<Class<?>> targetClassSet = entry.getValue();
            for (Class<?> c : targetClassSet){
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(c)){
                    targetMap.get(c).add(proxy);
                }else {
                    List<Proxy> proxies = new ArrayList<Proxy>();
                    proxies.add(proxy);
                    targetMap.put(c,proxies);
                }
            }
        }
        return targetMap;
    }
}
