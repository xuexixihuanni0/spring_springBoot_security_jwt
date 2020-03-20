package com.hydata.springsecurity_springboot_01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hydata.springsecurity_springboot_01.dao")
public class SpringsecuritySpringboot01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritySpringboot01Application.class, args);
    }

}
