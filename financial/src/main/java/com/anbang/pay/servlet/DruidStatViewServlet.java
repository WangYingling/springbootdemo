package com.anbang.pay.servlet;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * DRUID 数据源的监控SERVLET
 * Created by Administrator on 2017/1/14.
 */
@Component("druidStatServlet")
@WebServlet(urlPatterns = "/druid/*",initParams = {
        @WebInitParam(name = "allow",value = "127.0.0.1")//IP白名单，未配置或为空表示允许所有IP访问
        ,@WebInitParam(name = "deny",value = "192.168.1.104")//IP黑名单，有重复的deny优先级高于allow
        ,@WebInitParam(name = "loginUsername",value = "admin")
        ,@WebInitParam(name = "loginPassword" ,value = "admin")
        ,@WebInitParam(name = "resetEnable",value = "false")//禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {
    private static long serializedID=1L;
}
