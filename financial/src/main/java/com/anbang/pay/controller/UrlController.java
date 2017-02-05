package com.anbang.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/1/1.
 */
@Controller
public class UrlController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
