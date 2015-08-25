package org.smartlight.usermanager.persistence;

import java.util.List;

import org.smartlight.usermanager.model.SysDictionary;

public interface SysDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictionary record);

    int insertSelective(SysDictionary record);

    SysDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDictionary record);

    int updateByPrimaryKey(SysDictionary record);

	List<SysDictionary> selectByType(String dictType);

	SysDictionary selectByName(String name);

	List<SysDictionary> selectAll();
}