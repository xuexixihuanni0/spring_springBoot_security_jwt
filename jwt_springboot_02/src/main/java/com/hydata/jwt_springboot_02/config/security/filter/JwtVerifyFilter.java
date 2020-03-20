package com.hydata.jwt_springboot_02.config.security.filter;

import com.hydata.jwt_springboot_02.config.security.MyAuthenticationException;
import com.hydata.jwt_springboot_02.config.security.authenticationHandler.MyAuthenticationFailureHandler;
import com.hydata.jwt_springboot_02.utils.JwtTokenUtils;
import com.hydata.jwt_springboot_02.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class JwtVerifyFilter extends OncePerRequestFilter {
    @Autowired
    private SysUserServiceImpl sysUserService;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerToken = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        String requestURI = request.getRequestURI();
//        如果请求头中没有token
        if (headerToken == null) {
//            如果是放行的页面继续放行
            if (requestURI.equals("/index") || requestURI.equals("/") || requestURI.equals("/login") || requestURI.equals("/getVerifyCode")) {
                filterChain.doFilter(request, response);
                return;
            }
//            如果不是则重定向到登录页面
            else {
                response.sendRedirect("/login");
                return;
            }
        }
//        请求头部有token
        else {
//            token格式符合要求
            if (!headerToken.isEmpty() && headerToken.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
                String token = headerToken.substring(7);
                String username = null;
                boolean expiration = false;
                //从token中获取是否过期、用户名；若解析错误则交由认证失败处理器执行
                try {
                    expiration = JwtTokenUtils.isExpiration(token);
                    username = JwtTokenUtils.getUsername(token);
                } catch (MyAuthenticationException e) {
                    failureHandler.onAuthenticationFailure(request, response, e);
                    return;
                }
                if (!expiration) {
                    UserDetails user = sysUserService.loadUserByUsername(username);
                    if (user != null) {
                        UsernamePasswordAuthenticationToken abstractAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
//                        这里设置了用户信息就不会走账号密码验证
                        SecurityContextHolder.getContext().setAuthentication(abstractAuthenticationToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
