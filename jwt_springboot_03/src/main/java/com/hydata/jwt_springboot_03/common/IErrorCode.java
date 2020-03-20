package com.hydata.jwt_springboot_03.common;

/**
 * 封装API的错误码
 * Created by ChangHeXiang on 2019/7/24.
 */
public interface IErrorCode {

    long getCode();
    String getMessage();

}
