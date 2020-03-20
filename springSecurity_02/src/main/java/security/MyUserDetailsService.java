package security;

import dao.UserMapper;
import domain.SysPermissionEntity;
import domain.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUserEntity sysUserEntity = userMapper.findByName(userName);

        List<GrantedAuthority> list = new ArrayList();
        if (sysUserEntity != null) {
            List<SysPermissionEntity> permissionList = userMapper.findPermissionByName(userName);
            for (SysPermissionEntity sysPermissionEntity : permissionList) {
                String permTag = sysPermissionEntity.getPermTag();
                list.add(new SimpleGrantedAuthority(permTag));
            }
            sysUserEntity.setAuthorities(list);
        }

        return sysUserEntity;
    }
}
