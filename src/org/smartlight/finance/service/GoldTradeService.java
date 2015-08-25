package org.smartlight.finance.service;

import java.util.List;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.finance.model.GoldTrade;

public interface GoldTradeService extends BusinessObjectServiceMgr {
	

	List selectAllTrade();
	GoldTrade getTrade(Integer tradeId);
	void addTrade(GoldTrade po);
	void updTrade(GoldTrade po);
	void delTrade(Integer tradeId);
}
