package com.hydata.jwt_springboot_01.config.security.filter;

import com.hydata.jwt_springboot_01.config.security.MyAuthenticationException;
import com.hydata.jwt_springboot_01.config.security.authenticationHandler.MyAuthenticationFailureHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class ImageCodeVerifyFilter extends OncePerRequestFilter {

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.debug("URL:" + requestURI + "----------METHOD:" + method);
        if (requestURI.contains("login") && method.equalsIgnoreCase("POST")) {
            try {
                String imageCode = request.getParameter("imageCode");
                if (StringUtils.isEmpty(imageCode)) {
                    throw new MyAuthenticationException("请输入验证码");
                }
                String verifyCode = (String) request.getSession().getAttribute("verifyCode");
                if (!imageCode.equalsIgnoreCase(verifyCode)) {
                    throw new MyAuthenticationException("请输入正确的验证码");
                }
            } catch (AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }

        }
        chain.doFilter(request, response);
    }
}
