package com.zmc.bee.framework.configuration;

import com.zmc.bee.framework.constant.ConfigConstant;
import com.zmc.bee.framework.util.PropsUtil;

import java.util.Properties;

/**
 * Created by zhongmc on 2017/5/16.
 * 配置项工具类
 */
public final class ConfigHelper {
    private static final Properties CONFIG_PROP = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取数据库驱动名
     *
     * @return
     */
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROP, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取数据库连接地址
     *
     * @return
     */
    public static String getJdbcUrl() {
        return PropsUtil.getString(CONFIG_PROP, ConfigConstant.JDBC_URL);
    }

    /**
     * 获取数据库连接用户名
     *
     * @return
     */
    public static String getJdbcUsername() {
        return PropsUtil.getString(CONFIG_PROP, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取数据库连接密码
     *
     * @return
     */
    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROP, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取项目的根包
     *
     * @return
     */
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROP, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取jsp的路径
     *
     * @return
     */
    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROP, ConfigConstant.APP_JSP_PATH, "/WEB-INF/views/");
    }

    /**
     * 获取静态资源的路径
     *
     * @return
     */
    public static String getAppAssetPath() {
        return PropsUtil.getString(CONFIG_PROP, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
}
