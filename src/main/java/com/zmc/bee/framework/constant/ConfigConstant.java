package com.zmc.bee.framework.constant;

/**
 * Created by zhongmc on 2017/5/16.
 * 定义全局配置常量
 */
public interface ConfigConstant {
    //默认 public final static
    /**
     * 配置文件名
     */
    String CONFIG_FILE = "bee.properties";
    /**
     * 数据驱动名
     */
    String JDBC_DRIVER = "bee.framework.jdbc.driver";
    /**
     * 数据库地址
     */
    String JDBC_URL = "bee.framework.jdbc.url";
    /**
     * 数据库用户名
     */
    String JDBC_USERNAME = "bee.framework.jdbc.username";
    /**
     * 数据库密码
     */
    String JDBC_PASSWORD = "bee.framework.jdbc.password";
    /**
     * 项目基础包
     */
    String APP_BASE_PACKAGE = "bee.framework.app.base_package";
    /**
     * JSP文件路径
     */
    String APP_JSP_PATH = "bee.framework.app.jsp_path";
    /**
     * 静态资源路径
     */
    String APP_ASSET_PATH = "bee.framework.app.asset_path";
}
