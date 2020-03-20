package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        首先判断这个请求是否是登录请求
        if(request.getRequestURI().contains("login")){
            String imageCode = (String) request.getParameter("imageCode");
            String key = (String) request.getSession().getAttribute("key");
            try {
                if(imageCode==null||imageCode.trim().length()==0){
                    throw new ImageCodeException("验证码不能为空");
                }
                if(!imageCode.equalsIgnoreCase(key)){
                    throw new ImageCodeException("请输入正确的验证码");
                }
            } catch (ImageCodeException e) {
                myAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }

        }
        filterChain.doFilter(request,response);
    }
}
