package org.smartlight.finance.persistence;

import java.util.List;

import org.smartlight.finance.model.StockWatch;

public interface StockWatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockWatch record);

    int insertSelective(StockWatch record);

    StockWatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockWatch record);

    int updateByPrimaryKey(StockWatch record);
    
    List<StockWatch> selectValid();
}