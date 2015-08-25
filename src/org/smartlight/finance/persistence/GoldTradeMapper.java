package org.smartlight.finance.persistence;

import java.util.List;

import org.smartlight.finance.model.GoldTrade;

public interface GoldTradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoldTrade record);

    int insertSelective(GoldTrade record);

    GoldTrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoldTrade record);

    int updateByPrimaryKey(GoldTrade record);

	List selectAll();
}