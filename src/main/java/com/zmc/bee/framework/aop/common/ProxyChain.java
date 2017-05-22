package com.zmc.bee.framework.aop.common;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongmc on 2017/5/22.
 * 代理链
 */
public class ProxyChain {
    private Class targetClass;
    private Object target;
    private Method targetMethod;
    private Object[] params;
    private MethodProxy methodProxy;
    private List<Proxy> proxyList = new ArrayList<Proxy>();


    private static int cursor = 0;

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public void setMethodProxy(MethodProxy methodProxy) {
        this.methodProxy = methodProxy;
    }

    public List<Proxy> getProxyList() {
        return proxyList;
    }

    public void setProxyList(List<Proxy> proxyList) {
        this.proxyList = proxyList;
    }

    /**
     * 构造器
     * @param targetClass
     * @param target
     * @param targetMethod
     * @param methodProxy
     * @param proxyList
     */
    public ProxyChain(Class targetClass, Object target, Method targetMethod, MethodProxy methodProxy, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.target = target;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.proxyList = proxyList;
    }

    public Object doProxyChain() throws Throwable{
        int proxyChainLen = proxyList.size();
        Object result ;
        if (cursor<proxyChainLen){
            Proxy proxy = proxyList.get(cursor++);
            result = proxy.doProxy(this);
        }else{
            result = methodProxy.invokeSuper(target, params);
        }
        return result;

    }
}
