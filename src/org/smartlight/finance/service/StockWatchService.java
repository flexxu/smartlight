package org.smartlight.finance.service;

import java.text.ParseException;
import java.util.List;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.finance.model.StockWatch;

public interface StockWatchService extends BusinessObjectServiceMgr {
	

	List<StockWatch> selectValid();
	StockWatch getStockWatch(Integer watchId);
	void addStockWatch(StockWatch po);
	void updStockWatch(StockWatch po);
	void delStockWatch(Integer watchId);
	void checkWatch() throws ParseException;
}
