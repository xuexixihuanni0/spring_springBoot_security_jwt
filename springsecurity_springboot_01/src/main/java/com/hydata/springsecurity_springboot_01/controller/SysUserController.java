package com.hydata.springsecurity_springboot_01.controller;

import com.hydata.springsecurity_springboot_01.entity.SysUser;
import com.hydata.springsecurity_springboot_01.service.SysUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2020-03-13 14:03:04
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUser selectOne(Integer id) {
        return this.sysUserService.queryById(id);

    }

    @RequestMapping("add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("list")
    public String list(){
        return "/user/list";
    }

    @RequestMapping("update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("delete")
    public String delete(){
        return "/user/delete";
    }

}