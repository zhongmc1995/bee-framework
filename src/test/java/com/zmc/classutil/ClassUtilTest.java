package com.zmc.classutil;

import com.zmc.bee.framework.util.ClassUtil;

/**
 * Created by zhongmc on 2017/5/17.
 */
public class ClassUtilTest {
    public static void main(String[] args) {
        ClassUtil.loadClass(TestBean.class.getName());
    }
}
