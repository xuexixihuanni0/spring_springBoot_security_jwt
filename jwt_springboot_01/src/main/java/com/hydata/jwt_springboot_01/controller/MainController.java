package com.hydata.jwt_springboot_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {



    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("/")
    @ResponseBody
    public String index1(){
        return "index";
    }


    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping("accessDeined")
    public String accessDeined(){
        return "accessDeined";
    }
}
