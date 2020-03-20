package com.hydata.jwt_springboot_03.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2020-03-13 14:03:01
 */
@Data
public class SysUser implements Serializable , UserDetails {
    private static final long serialVersionUID = 573810770728994662L;
    
    private Integer id;
    
    private String username;
    
    private String realname;
    
    private String password;
    
    private Object createdate;
    
    private Object lastlogintime;
    
    private boolean enabled;
    
    private boolean accountnonexpired;
    
    private boolean accountnonlocked;
    
    private boolean credentialsnonexpired;

    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountnonexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountnonlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountnonexpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}