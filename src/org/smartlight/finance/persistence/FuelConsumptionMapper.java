package org.smartlight.finance.persistence;

import java.util.List;

import org.smartlight.finance.model.FuelConsumption;

public interface FuelConsumptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuelConsumption record);

    int insertSelective(FuelConsumption record);

    FuelConsumption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FuelConsumption record);

    int updateByPrimaryKey(FuelConsumption record);

	List selectAll();
}