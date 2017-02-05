package com.anbang.pay.configs;

import com.anbang.pay.filters.DruidStatFilter;
import com.anbang.pay.servlet.DruidStatViewServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DRUID配置
 * 该方式配置DRUID的监控servlet和filter，需要在启动来中启用注解@ServletComponentScan
 * Created by ab053164wangyingling on 2017/1/14.
 */
@Configuration
public class DruidConfig {
    /**
     * 注册一个DruidStatServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatServelt(){
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new DruidStatViewServlet(),"/druid2/*");
        //添加初始化参数：initParams
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");//白名单：
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin2");
        servletRegistrationBean.addInitParameter("loginPassword","admin2");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个DruidStatFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new DruidStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return filterRegistrationBean;
    }
}
