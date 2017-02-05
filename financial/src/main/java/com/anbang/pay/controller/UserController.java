package com.anbang.pay.controller;

import com.anbang.pay.entity.Operator;
import com.anbang.pay.service.OperatorService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/1/1.
 */
@RestController
public class UserController {
    //资源注入
    @Autowired
    private OperatorService operatorService;
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
    @RequestMapping("/usr/2list")
    public ModelAndView listOperators(Operator operator,
                                      HttpServletRequest request, HttpServletResponse response){
        ModelAndView listView=new ModelAndView("/usr/list");
        List<Operator> listResult=operatorService.findOperators(operator);
        listView.addObject("userlist",listResult);
        return listView;
    }
}
