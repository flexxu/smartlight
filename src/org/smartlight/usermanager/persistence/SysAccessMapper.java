package org.smartlight.usermanager.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.smartlight.usermanager.model.SysAccess;

public interface SysAccessMapper {
    int insert(SysAccess record);

    int insertSelective(SysAccess record);
    
    List selectByUserId(@Param("pId")Integer pId,@Param("userId")Integer userId);
    
    int deleteByRole(Integer id);
}