package com.hydata.jwt_springboot_03.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (SysPermission)实体类
 *
 * @author makejava
 * @since 2020-03-13 15:45:59
 */
@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 895752777704943285L;
    
    private Integer id;
    
    private String permname;
    
    private String permtag;

}