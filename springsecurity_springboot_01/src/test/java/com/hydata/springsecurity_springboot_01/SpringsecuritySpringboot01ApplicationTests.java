package com.hydata.springsecurity_springboot_01;

import com.hydata.springsecurity_springboot_01.dao.SysUserDao;
import com.hydata.springsecurity_springboot_01.entity.SysPermission;
import com.hydata.springsecurity_springboot_01.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringsecuritySpringboot01ApplicationTests {

    @Autowired
    private SysUserDao sysUserDao;

    @Test
    void queryById() {
        SysUser sysUser = sysUserDao.queryById(1);
        System.out.println(sysUser.toString());
    }


    @Test
    void queryPermTagsByUserName() {
        List<SysPermission> user1 = sysUserDao.queryPermTagsByUserName("user1");
        for (SysPermission sysPermission : user1) {
            System.out.println(sysPermission.toString());
        }
    }
}
