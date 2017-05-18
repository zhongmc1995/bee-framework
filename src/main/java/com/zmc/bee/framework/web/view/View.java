package com.zmc.bee.framework.web.view;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongmc on 2017/5/16.
 * 视图
 */
public class View {
    private String path;
    private Map<String,Object> model;
    public View(String path){
        this.path = path;
        model = new HashMap<String,Object>();
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    /**
     * 将数据设置到视图模型中
     * @param name
     * @param value
     */
    public void addModel(String name,Object value){
        model.put(name,value);
    }
}
