package com.zmc.bee.framework.aop.common;

/**
 * Created by zhongmc on 2017/5/22.
 * 代理接口
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
