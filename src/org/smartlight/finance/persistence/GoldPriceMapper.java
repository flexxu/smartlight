package org.smartlight.finance.persistence;

import java.util.List;

import org.smartlight.finance.model.GoldPrice;

public interface GoldPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoldPrice record);

    int insertSelective(GoldPrice record);

    GoldPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoldPrice record);

    int updateByPrimaryKey(GoldPrice record);
    
    List<GoldPrice> selectRecent(Integer period);
}