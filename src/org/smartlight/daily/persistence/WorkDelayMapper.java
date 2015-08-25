package org.smartlight.daily.persistence;

import java.util.List;

import org.smartlight.daily.model.WorkDelay;

public interface WorkDelayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkDelay record);

    int insertSelective(WorkDelay record);

    WorkDelay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkDelay record);

    int updateByPrimaryKey(WorkDelay record);
    
    List selectAll();
}