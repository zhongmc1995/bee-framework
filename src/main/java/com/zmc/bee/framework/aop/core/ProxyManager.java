package com.zmc.bee.framework.aop.core;

import com.zmc.bee.framework.aop.common.Proxy;
import com.zmc.bee.framework.aop.common.ProxyChain;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by zhongmc on 2017/5/22.
 * 代理管理器
 */
public class ProxyManager {
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T)Enhancer.create(targetClass, new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass,o,method, methodProxy,proxyList).doProxyChain();
            }
        });
    }
}
