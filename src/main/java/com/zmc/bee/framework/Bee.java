package com.zmc.bee.framework;

import com.zmc.bee.framework.bean.ClassHelper;
import com.zmc.bee.framework.bean.DefaultBeanFactory;
import com.zmc.bee.framework.bean.IocHelper;
import com.zmc.bee.framework.util.ClassUtil;
import com.zmc.bee.framework.web.handler.HandlerMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhongmc on 2017/5/16.
 */
public final class Bee {
    private final static Logger LOGGER = LoggerFactory.getLogger(Bee.class);
    public static void init(){
        Class<?>[] classList = {ClassHelper.class, DefaultBeanFactory.class, IocHelper.class, HandlerMatcher.class};
        for (Class<?> c : classList){
            LOGGER.info("init "+c.getName());
            ClassUtil.loadClass(c.getName(),true);
        }
    }

}
