package com.zmc.bee.framework.web.handler;

import java.lang.reflect.Method;

/**
 * Created by zhongmc on 2017/5/16.
 * 请求处理
 */
public class RequestHandler {
    public RequestHandler(Class<?> controller, Method requestMethod) {
        this.controller = controller;
        this.requestMethod = requestMethod;
    }

    /**
     * 请求处理的controller
     */
    private Class<?> controller;
    /**
     * 请求处理的方法
     */
    private Method requestMethod;

    public Class<?> getController() {
        return controller;
    }

    public void setController(Class<?> controller) {
        this.controller = controller;
    }

    public Method getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(Method requestMethod) {
        this.requestMethod = requestMethod;
    }
}
