package org.smartlight.usermanager.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.smartlight.usermanager.model.SysNode;

public interface SysNodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNode record);

    int insertSelective(SysNode record);

    SysNode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNode record);

    int updateByPrimaryKey(SysNode record);
    
    List selectByUserId(@Param("pId")Integer pId,@Param("userId")Integer userId);

    List selectAll();

	List<SysNode> selectByGroupId(Integer groupId);

	List<SysNode> selectForGrant(@Param("groupId")Integer groupId,@Param("roleId")Integer roleId);
}