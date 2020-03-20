package com.hydata.jwt_springboot_01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hydata.jwt_springboot_01.dao")
public class JwtSpringboot01Application {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringboot01Application.class, args);
    }

}
