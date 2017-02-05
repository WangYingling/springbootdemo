package com.anbang.pay.filters;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * DRUID数据源监控FILTER
 * Created by ab053164wangyingling on 2017/1/14.
 */
@Component("druidStatFilter")
@WebFilter(
       filterName = "druidStatFilter"
        ,urlPatterns = "/*"
        ,initParams = {
        @WebInitParam(name = "exclusions",value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
}
)
public class DruidStatFilter extends WebStatFilter{
}
