package org.smartlight.usermanager.persistence;

import java.util.List;

import org.smartlight.usermanager.model.SysSms;

public interface SysSmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysSms record);

    int insertSelective(SysSms record);

    SysSms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysSms record);

    int updateByPrimaryKey(SysSms record);
    
    List selectAll();
}