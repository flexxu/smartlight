package org.smartlight.finance.service;

import java.util.List;

import org.smartlight.finance.model.FuelConsumption;
import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;

public interface FuelConsumptionService extends BusinessObjectServiceMgr {
	

	List selectAll();
	FuelConsumption get(Integer id);
	void add(FuelConsumption po);
	void update(FuelConsumption po);
	void del(Integer id);
}
