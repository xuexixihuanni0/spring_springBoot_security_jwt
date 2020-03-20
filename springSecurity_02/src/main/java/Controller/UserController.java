package Controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/add")
    public String add() {
//        获取用的所有认证信息（该用户对象、证书、所有权限、ip、sessionId、是否认证）
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        ip、sessionId
        Object details = authentication.getDetails();
//        证书
        Object credentials = authentication.getCredentials();
//        用户对象
        Object principal = authentication.getPrincipal();
//        是否认证
        boolean authenticated = authentication.isAuthenticated();

        return "user/add";
    }

    @RequestMapping("/list")
    public String list() {
        return "user/list";
    }

    @RequestMapping("/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/delete")
    public String delete() {
        return "user/delete";
    }

    @RequestMapping("/MyLogin")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @RequestMapping("/imageCode")
    public String imageCode() {
        return "imageCode";
    }


}
