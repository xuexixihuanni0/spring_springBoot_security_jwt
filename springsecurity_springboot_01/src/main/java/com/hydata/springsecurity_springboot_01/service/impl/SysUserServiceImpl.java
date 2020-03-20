package com.hydata.springsecurity_springboot_01.service.impl;

import com.hydata.springsecurity_springboot_01.entity.SysPermission;
import com.hydata.springsecurity_springboot_01.entity.SysUser;
import com.hydata.springsecurity_springboot_01.dao.SysUserDao;
import com.hydata.springsecurity_springboot_01.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-03-13 14:03:04
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService, UserDetailsService {
    @Resource
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Integer id) {
        return this.sysUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser update(SysUser sysUser) {
        this.sysUserDao.update(sysUser);
        return this.queryById(sysUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserDao.deleteById(id) > 0;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.queryByUserName(userName);
        List<GrantedAuthority> list=new ArrayList();
        if(sysUser!=null){
            List<SysPermission> permTags = sysUserDao.queryPermTagsByUserName(userName);
            for (SysPermission permTag : permTags) {
                list.add(new SimpleGrantedAuthority(permTag.getPermtag()));
            }
            sysUser.setAuthorities(list);
        }

        return sysUser;
    }
}