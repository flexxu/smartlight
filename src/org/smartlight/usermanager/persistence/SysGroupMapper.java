package org.smartlight.usermanager.persistence;

import java.util.List;

import org.smartlight.usermanager.model.SysGroup;

public interface SysGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysGroup record);

    int insertSelective(SysGroup record);

    SysGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysGroup record);

    int updateByPrimaryKey(SysGroup record);
    
    List selectByUserId(Integer userId);
    
    List selectAll();
    
    List selectForGrant(Integer roleId);
}