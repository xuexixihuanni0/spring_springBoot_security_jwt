package com.hydata.jwt_springboot_03.controller;

import com.hydata.jwt_springboot_03.common.CommonResult;
import com.hydata.jwt_springboot_03.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {



    @Autowired
    private SysUserServiceImpl sysUserService;


    @RequestMapping("index")
    public String index() {
        return "index";
    }



    @PostMapping("/login")
    public CommonResult login(@RequestBody Map map) {
        String token = sysUserService.login(map);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return CommonResult.success(tokenMap);
    }

    @RequestMapping("accessDeined")
    public String accessDeined() {
        return "accessDeined";
    }
}
