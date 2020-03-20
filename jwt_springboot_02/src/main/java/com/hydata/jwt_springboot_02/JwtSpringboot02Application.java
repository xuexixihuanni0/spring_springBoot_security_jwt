package com.hydata.jwt_springboot_02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hydata.jwt_springboot_02.dao")
public class JwtSpringboot02Application {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringboot02Application.class, args);
    }

}
