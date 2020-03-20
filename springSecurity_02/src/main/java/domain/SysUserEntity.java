package domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SysUserEntity implements UserDetails {


    private Integer id;


    private String username;


    private String realname;


    private String password;


    private Date createDate;


    private Date lastLoginTime;


    private boolean enabled;


    private boolean accountNonExpired;


    private boolean accountNonLocked;


    private boolean credentialsNonExpired;

    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();


}