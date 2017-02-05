package com.anbang.pay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Error错误处理页面
 * Created by ab053164wangyingling on 2017/1/14.
 */
@Controller
public class ErrorHandleController implements ErrorController{
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public String getErrorPath() {
        logger.debug("##SYSTEM ERROR,URL SEND TO ERROR PAGE");
        return "/common/error";
    }
    @RequestMapping
    public String errorHandle(){
        return getErrorPath();
    }
}
