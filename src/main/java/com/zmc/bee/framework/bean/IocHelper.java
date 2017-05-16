package com.zmc.bee.framework.bean;

import com.zmc.bee.framework.util.ReflctionUtil;
import com.zmc.bee.framework.web.annotaion.Inject;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by zhongmc on 2017/5/16.
 * 依赖注入bean
 */
public final class IocHelper {
    static {
        Map<Class<?>, Object> beanMaps = DefaultBeanFactory.getContainer();
        if (null!=beanMaps && beanMaps.size()!=0){
            for (Map.Entry e : beanMaps.entrySet()){
                Class<?> beanClass = (Class<?>) e.getKey();
                Object classInstance = e.getValue();
                Field[] fields = beanClass.getDeclaredFields();
                if (fields!=null && fields.length!=0){
                    for (Field f : fields){
                        if (f.isAnnotationPresent(Inject.class)){
                            Class<?> fieldClass = f.getType();
                            Object fieldInstance = beanMaps.get(fieldClass);
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
