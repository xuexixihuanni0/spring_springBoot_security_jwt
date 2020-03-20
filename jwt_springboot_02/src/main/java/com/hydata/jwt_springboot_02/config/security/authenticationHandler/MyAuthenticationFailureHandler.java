package com.hydata.jwt_springboot_02.config.security.authenticationHandler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map<String,Object> map=new HashMap<>(2);
        map.put("success",false);
        if(e.getMessage()!=null){
            if(e.getMessage().contains("UserDetailsService returned null")){
                map.put("mes","用户名不存在");
            }else if(e.getMessage().contains("Bad credentials")){
                map.put("mes","账号或密码输入错误");
            }else{
                map.put("mes",e.getMessage());
            }
        }
        String result = JSONObject.toJSONString(map);
        httpServletResponse.setContentType("text/json;charset=UTF-8");
        httpServletResponse.getWriter().write(result);
    }
}
