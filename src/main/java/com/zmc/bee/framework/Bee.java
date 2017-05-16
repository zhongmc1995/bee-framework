package com.zmc.bee.framework;

import com.zmc.bee.framework.bean.ClassHelper;
import com.zmc.bee.framework.bean.DefaultBeanFactory;
import com.zmc.bee.framework.bean.IocHelper;
import com.zmc.bee.framework.util.ClassUtil;
import com.zmc.bee.framework.web.handler.HandlerMatcher;

/**
 * Created by zhongmc on 2017/5/16.
 */
public final class Bee {
    public static void init(){
        Class<?>[] classList = {ClassHelper.class, DefaultBeanFactory.class, IocHelper.class, HandlerMatcher.class};
        for (Class<?> c : classList){
            ClassUtil.loadClass(c.getName());
        }
    }

}
