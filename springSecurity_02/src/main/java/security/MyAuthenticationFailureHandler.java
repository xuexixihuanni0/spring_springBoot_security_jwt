package security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map<String,Object> map=new HashMap<>(2);
        map.put("success",false);
        if(e.getMessage()!=null){
//            UserDetailsService查询不到该用户
          if(e.getMessage().contains("UserDetailsService returned null")){
              map.put("mes","用户名不存在");
//              密码错误
            }else if(e.getMessage().contains("Bad credentials")){
              map.put("mes","密码错误");
            }else{
              map.put("mes",e.getMessage());
          }
        }else{
            map.put("mes","登录失败");
        }
        String reslut = JSONObject.toJSONString(map);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(reslut);
    }
}
