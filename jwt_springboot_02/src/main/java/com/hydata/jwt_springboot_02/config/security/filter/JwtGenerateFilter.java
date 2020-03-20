package com.hydata.jwt_springboot_02.config.security.filter;

import com.hydata.jwt_springboot_02.config.security.authenticationHandler.MyAuthenticationFailureHandler;
import com.hydata.jwt_springboot_02.config.security.authenticationHandler.MyAuthenticationSuccessHandler;
import com.hydata.jwt_springboot_02.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtGenerateFilter extends UsernamePasswordAuthenticationFilter {


    private boolean rememberme = false;

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    private RememberMeServices rememberMeServices = new NullRememberMeServices();

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
                    + authResult);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);

        rememberMeServices.loginSuccess(request, response, authResult);

        // Fire event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(
                    authResult, this.getClass()));
        }
//      从request中取出remember-me的值
        String parameter = request.getParameter(AbstractRememberMeServices.DEFAULT_PARAMETER);
        if (parameter != null && !parameter.isEmpty()) {
            if (parameter.equalsIgnoreCase("true") || parameter.equalsIgnoreCase("on")
                    || parameter.equalsIgnoreCase("yes") || parameter.equals("1")) {
                rememberme = true;
            }
        }
//        生成token
        String token = JwtTokenUtils.createToken(authResult.getName(), rememberme);
//        相应头设置token
        response.setHeader("token", token);

        successHandler.onAuthenticationSuccess(request, response, authResult);
    }



    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString(), failed);
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler " + failureHandler);
        }

        rememberMeServices.loginFail(request, response);

        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
