package com.hydata.springsecurity_springboot_01.config.security;

import com.hydata.springsecurity_springboot_01.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserServiceImpl sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyOnecPerFilter myOnecPerFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .authorizeRequests()
                .antMatchers("/login", "/index", "/getVerifyCode").permitAll()
//               注意：hasRole，hasAnyRole方法会自动加上前缀"ROLE_"；而hasAnyAuthority不会
//               允许角色是ROLE_ADD或者ROLE_ADMIN并且是完整登录（非记住我登录）
                .antMatchers("/sysUser/add").access("hasAuthority('ROLE_ADD') || hasRole('LIST') && isFullyAuthenticated()")
                .antMatchers("/sysUser/list").hasAnyAuthority("ROLE_LIST")
                .antMatchers("/sysUser/update").hasAnyAuthority("ROLE_UPDATE")
                .antMatchers("/sysUser/delete").hasAnyAuthority("ROLE_DELETE")
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().loginPage("/login")
//               认证成功后跳转的页面，与successHandler不共存
//               .defaultSuccessUrl("/index")
                .successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler)
                .and()
                .csrf().disable();
        http.addFilterBefore(myOnecPerFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }


}
