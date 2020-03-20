package com.hydata.jwt_springboot_03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hydata.jwt_springboot_03.dao")
public class JwtSpringboot03Application {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringboot03Application.class, args);
    }

}
