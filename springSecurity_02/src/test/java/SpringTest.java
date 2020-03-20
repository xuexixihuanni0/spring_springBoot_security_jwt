import dao.UserMapper;
import domain.SysPermissionEntity;
import domain.SysUserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/ApplicationContext.xml")
public class SpringTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByName(){
        SysUserEntity user = userMapper.findByName("user1");
        System.out.println(user);
    }

    @Test
    public void findPerByName(){
        List<SysPermissionEntity> permissions = userMapper.findPermissionByName("user1");
        for (SysPermissionEntity permission : permissions) {
            System.out.println(permission.toString());
        }
    }

    @Test
    public void updateUser(){
        SysUserEntity user=new SysUserEntity();
        user.setUsername("user2");
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        user.setPassword(encode);
        userMapper.updateUser(user);

    }
}
