package org.smartlight.usermanager.persistence;

import org.smartlight.usermanager.model.SysRoleUser;

public interface SysRoleUserMapper {
    int deleteByUser(Integer userId);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);
}