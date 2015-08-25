package org.smartlight.usermanager.persistence;

import java.util.List;

import org.smartlight.usermanager.model.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List selectAll();

	List selectByUser(Integer userId);
}