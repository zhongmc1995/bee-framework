package com.zmc.bee.framework.aop.common.Impl;

import com.zmc.bee.framework.aop.common.Proxy;
import com.zmc.bee.framework.aop.common.ProxyChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by zhongmc on 2017/5/22.
 * 切面抽象类
 */
public abstract class AspectProxy implements Proxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    /**
     * 重写doProxy方法
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Class targetClass = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getParams();
        begin();
        Object result = null;
        try {
            if (intercept(targetClass,targetMethod,params)){
                before(targetClass,targetMethod,params);
                result = proxyChain.doProxyChain();
                after(targetClass,targetMethod,params,result);
            }else {
                result = proxyChain.doProxyChain();
            }
        }catch (Exception e){
            LOGGER.error("proxy failure" ,e);
            error(targetClass,targetMethod,params,e);
        }finally {
            end();
        }

        return result;
    }

    /**
     * 代理开始
     */
    public void begin(){

    }

    /**
     * 拦截
     * @param cls
     * @param method
     * @param params
     * @return
     * @throws Throwable
     */
    public boolean intercept(Class<?> cls, Method method,Object[] params) throws Throwable{
        return true;
    }

    /**
     * 前置增强方法
     * @param cls
     * @param method
     * @param params
     * @throws Throwable
     */
    public void before(Class<?> cls, Method method,Object[] params) throws Throwable{

    }

    /**
     * 后置增强方法
     * @param cls
     * @param method
     * @param params
     * @param result
     * @throws Throwable
     */
    public void after(Class<?> cls, Method method,Object[] params,Object result) throws Throwable{

    }

    /**
     * 代理异常处理方法
     * @param cls
     * @param method
     * @param params
     * @param throwable
     */
    public void error(Class<?> cls, Method method,Object[] params,Throwable throwable){

    }

    /**
     * 代理结束
     */
    public void end(){

    }
}
