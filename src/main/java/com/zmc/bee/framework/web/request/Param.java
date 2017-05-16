package com.zmc.bee.framework.web.request;

import java.util.Map;

/**
 * Created by zhongmc on 2017/5/16.
 * 请求参数的封装
 */
public class Param {
    private Map<String ,Object> params;
    public Param(Map<String,Object> params){
        this.params = params;
    }

    public Map<String,Object> getParams(){
        return params;
    }
}
