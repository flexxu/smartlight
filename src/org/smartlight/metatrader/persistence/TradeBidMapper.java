package org.smartlight.metatrader.persistence;

import java.util.List;

import org.smartlight.metatrader.model.TradeBid;

public interface TradeBidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeBid record);

    int insertSelective(TradeBid record);

    TradeBid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeBid record);

    int updateByPrimaryKey(TradeBid record);
    
    List selectAll();
    
    List selectOpened(Integer opType);
}