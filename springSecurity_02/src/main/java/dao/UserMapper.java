package dao;

import domain.SysPermissionEntity;
import domain.SysUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public SysUserEntity findByName(String userName);

    List<SysPermissionEntity> findPermissionByName(String userName);

    void updateUser(SysUserEntity user);
}
