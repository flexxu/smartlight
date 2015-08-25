package org.smartlight.finance.service.impl;

import java.util.List;

import org.smartlight.finance.model.FuelConsumption;
import org.smartlight.finance.persistence.FuelConsumptionMapper;

import org.smartlight.finance.service.FuelConsumptionService;
import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("fuelConsumptionService")
public class FuelConsumptionServiceImpl extends AbstractBusinessObjectServiceMgr
		implements FuelConsumptionService {
	
	@Autowired
	private FuelConsumptionMapper fuelConsumptionMapper;

	public List selectAll() {
		return fuelConsumptionMapper.selectAll();
	}

	public FuelConsumption get(Integer id) {
		return fuelConsumptionMapper.selectByPrimaryKey(id);
	}

	public void add(FuelConsumption po) {
		fuelConsumptionMapper.insert(po);
		
	}

	public void update(FuelConsumption po) {
		fuelConsumptionMapper.updateByPrimaryKey(po);
		
	}

	public void del(Integer id) {
		fuelConsumptionMapper.deleteByPrimaryKey(id);
		
	}
	

	
}
