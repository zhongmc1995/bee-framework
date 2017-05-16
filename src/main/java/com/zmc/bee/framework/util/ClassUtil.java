package com.zmc.bee.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.protocol.jar.JarURLConnection;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by zhongmc on 2017/5/16.
 * 类操作工具类
 */
public final class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取基础包下的类集合
     * @param clazPackage
     * @return
     */
    public static Set<Class<?>> getClassAsSet(String clazPackage){
        Set<Class<?>> classSet = new LinkedHashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(clazPackage.replace(".","/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if (null != url){
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)){
                        //load common file such as class file
                        String packagePath = url.getPath().replaceAll("%20","");
                        addClass(classSet,packagePath,clazPackage);
                    }else if ("jar".equals(protocol)){
                        //load net jar file
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (null != jarURLConnection){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (null != jarFile){
                                Enumeration<JarEntry> entries = jarFile.entries();
                                while (entries.hasMoreElements()){
                                    JarEntry jarEntry = entries.nextElement();
                                    String jarName = jarEntry.getName();
                                    if (jarName.endsWith(".class")){
                                        String className = jarName.substring(0,jarName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("scanner basepackage class failure",e);
            throw new RuntimeException(e);
        }

        return classSet;
    }

    /**
     * 加载类
     * @param className
     * @return
     */
    public static Class<?> loadClass(String className){
        Class<?> cls = null;
        try {
            cls = getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("load "+className+" class failure",e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 通过类全名加入到classSet
     * @param classSet
     * @param className
     */
    private static void doAddClass(Set<Class<?>> classSet,String className){
        classSet.add(loadClass(className));
    }


    private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
            }
        });
        for (File file : files){
            String fileName = file.getName();
            if (file.isFile()){
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if (StringUtils.isNotEmpty(className)){
                    className = packageName+"."+className;
                    doAddClass(classSet,className);
                }
            }else{
                String subPackagePath = packagePath;
                if (StringUtils.isNotEmpty(fileName)){
                    subPackagePath = subPackagePath+"/"+fileName;
                }
                String subPackageName = packageName;
                if (StringUtils.isNotEmpty(fileName)){
                    subPackageName = subPackageName+"."+fileName;
                }

                addClass(classSet,subPackagePath,subPackageName);
            }
        }
    }

    /*public static void main(String[] args) {
        String s = "com/zmc/dfs/zdf".replaceAll("%20", "");
        System.out.println(s);

        Set<Class<?>> classAsSet = getClassAsSet("com.zmc");
        for (Class c : classAsSet){
            System.out.println(c.getName());
        }
    }*/
}
