package com.zmc.bee.framework.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by zhongmc on 2017/5/22.
 * 切面的注解类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
