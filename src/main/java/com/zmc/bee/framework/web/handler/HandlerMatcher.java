package com.zmc.bee.framework.web.handler;

import com.zmc.bee.framework.bean.ClassHelper;
import com.zmc.bee.framework.bean.DefaultBeanFactory;
import com.zmc.bee.framework.web.annotaion.Router;
import com.zmc.bee.framework.web.request.Request;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhongmc on 2017/5/16.
 * 请求和处理方法映射器
 */
public class HandlerMatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerMatcher.class);
    private static final Map<Request,RequestHandler> handlerMapping = new HashMap<Request, RequestHandler>();
    static {
        LOGGER.info("HandlerMatcher static");
        Set<Class<?>> controllers = ClassHelper.getControllerClassAsSet();
        if (CollectionUtils.isNotEmpty(controllers)){
            for (Class<?> c : controllers){
                Method[] methods = c.getDeclaredMethods();
                if (methods!=null && methods.length!=0){
                    for (Method m : methods){
                        if (m.isAnnotationPresent(Router.class)){
                            Router router = m.getAnnotation(Router.class);
                            String value = router.value();
                            if (value.matches("\\w+:/\\w*")){
                                String[] arr = value.split(":");
                                if (arr!=null && arr.length==2){
                                    String requestMethod = arr[0];
                                    String requestPath = arr[1];
                                    Request request = new Request(requestPath,requestMethod);
                                    RequestHandler handler = new RequestHandler(c,m);
                                    handlerMapping.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public static RequestHandler getRequestHandler(String requestMethod,String requestPaht){
        Request r = new Request(requestPaht,requestMethod);
        return handlerMapping.get(r);
    }
}
