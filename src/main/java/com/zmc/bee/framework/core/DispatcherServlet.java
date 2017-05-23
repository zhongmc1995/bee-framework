package com.zmc.bee.framework.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmc.bee.framework.Bee;
import com.zmc.bee.framework.bean.DefaultBeanFactory;
import com.zmc.bee.framework.bean.IocHelper;
import com.zmc.bee.framework.configuration.ConfigHelper;
import com.zmc.bee.framework.util.ReflctionUtil;
import com.zmc.bee.framework.web.data.Data;
import com.zmc.bee.framework.web.handler.HandlerMatcher;
import com.zmc.bee.framework.web.handler.RequestHandler;
import com.zmc.bee.framework.web.request.Param;
import com.zmc.bee.framework.web.view.View;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongmc on 2017/5/16.
 * mvc核心控制器
 */
@WebServlet(value = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    private final static Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        LOGGER.info("DispatcherServlet init start");
        Bee.init();
        ServletContext servletContext = config.getServletContext();
        ServletRegistration servletRegistration = servletContext.getServletRegistration("jsp");
        servletRegistration.addMapping(ConfigHelper.getAppJspPath()+"*");
        ServletRegistration assetRegistration = servletContext.getServletRegistration("default");
        assetRegistration.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        RequestHandler requestHandler = HandlerMatcher.getRequestHandler(requestMethod, requestPath);
        if (null != requestHandler){
            Class<?> controllerClass = requestHandler.getController();
            Object controllerBean = DefaultBeanFactory.getBean(controllerClass);
            LOGGER.info("the request controller is "+controllerBean);
            Enumeration<String> parameterNames = req.getParameterNames();
            Map<String,Object> pataMap = new HashMap<String, Object>();
            while (parameterNames.hasMoreElements()){
                String param = parameterNames.nextElement();
                String value = req.getParameter(param);
                pataMap.put(param,value);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String line ;
            StringBuffer body = new StringBuffer();
            while ((line=reader.readLine())!=null){
                body.append(line);
            }
            if (reader!=null){
                reader.close();
            }
            String[] params = body.toString().split("&");
            if (params!=null && params.length!=0){
                for (String param : params){
                    String[] array = param.split("=");
                    if (array!=null && array.length==2){
                        pataMap.put(array[0],array[1]);
                    }
                }
            }
            Param param = new Param(pataMap);
            Method method = requestHandler.getRequestMethod();
            LOGGER.info("param is "+ param.toString()+" , method is "+method.getName());
            Object result = ReflctionUtil.invokeMethod(controllerBean, method, param);
            if (result instanceof View){
                View view = (View)result;
                String path = view.getPath();
                if (StringUtils.isNotEmpty(path)){
                    if (path.startsWith("/")){
                        resp.sendRedirect(req.getContextPath()+path);
                    }else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String,Object> e : model.entrySet()){
                            req.setAttribute(e.getKey(),e.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);
                    }
                }
            }else if (result instanceof Data){
                //返回json数据
                Data data = (Data)result;
                Object model = data.getModel();
                if (null != model){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter out = resp.getWriter();
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json = objectMapper.writeValueAsString(model);
                    out.write(json);
                    out.flush();
                    out.close();
                }
            }
        }
    }
}
