package com.zmc.bee.framework.bean;

import com.zmc.bee.framework.util.ReflctionUtil;
import com.zmc.bee.framework.web.annotaion.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by zhongmc on 2017/5/16.
 * 依赖注入bean
 */
public final class IocHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);
    static {
        Map<Class<?>, Object> beanMaps = DefaultBeanFactory.getContainer();
        LOGGER.info("IocHelper init static block start"+beanMaps.size());
        if (null!=beanMaps && beanMaps.size()!=0){
            for (Map.Entry<Class<?>, Object> e : beanMaps.entrySet()){
                Class<?> beanClass = e.getKey();
                Object classInstance = e.getValue();
                Field[] fields = beanClass.getDeclaredFields();
                if (fields!=null && fields.length!=0){
                    for (Field f : fields){
                        if (f.isAnnotationPresent(Inject.class)){
                            Class<?> fieldClass = f.getType();
                            Object fieldInstance = beanMaps.get(fieldClass);
                            LOGGER.info("============"+fieldInstance);
                            if (null != fieldInstance){
                                ReflctionUtil.setField(classInstance,f,fieldInstance);
                            }
                        }
                    }
                }

            }
        }
    }
}
