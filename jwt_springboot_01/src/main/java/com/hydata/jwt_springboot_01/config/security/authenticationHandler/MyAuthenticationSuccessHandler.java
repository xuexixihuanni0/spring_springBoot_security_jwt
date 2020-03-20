package com.hydata.jwt_springboot_01.config.security.authenticationHandler;

import com.alibaba.fastjson.JSONObject;
import com.hydata.jwt_springboot_01.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private boolean rememberme = false;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        chain.doFilter(request,response);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> map=new HashMap<>(2);
        map.put("success",true);
        map.put("mes","登陆成功");
        String username = httpServletRequest.getParameter("username");
        String parameter = httpServletRequest.getParameter(AbstractRememberMeServices.DEFAULT_PARAMETER);
        if (parameter != null && !parameter.isEmpty()) {
            if (parameter.equalsIgnoreCase("true") || parameter.equalsIgnoreCase("on")
                    || parameter.equalsIgnoreCase("yes") || parameter.equals("1")) {
                rememberme = true;
            }
        }
        String token = JwtTokenUtils.createToken(username, rememberme);
        map.put("token",token);
        String result = JSONObject.toJSONString(map);
        httpServletResponse.setContentType("text/json;charset=UTF-8");
        httpServletResponse.getWriter().write(result);
    }


}
