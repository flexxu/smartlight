package org.smartlight.usermanager.persistence;

import java.util.List;

import org.smartlight.usermanager.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);
    
    SysUser selectByName(String username);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    List selectAll();

	List selectByDeptId(Integer deptId);
}