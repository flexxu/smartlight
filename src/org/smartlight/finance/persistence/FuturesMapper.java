package org.smartlight.finance.persistence;

import java.util.List;

import org.smartlight.finance.model.Futures;

public interface FuturesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Futures record);

    int insertSelective(Futures record);

    Futures selectByPrimaryKey(Integer id);
    
    List<Futures> selectLastest(String code);

    List<Futures> selectAll(String code);

    List<Double> selectCloseList(String code);

    int updateByPrimaryKeySelective(Futures record);

    int updateByPrimaryKey(Futures record);
}