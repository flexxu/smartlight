package org.smartlight.metatrader.service;

import java.util.List;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.metatrader.model.TradeBid;

public interface TradeBidService extends BusinessObjectServiceMgr {
	

	List selectAll();
	TradeBid get(Integer id);
	void add(TradeBid po);
	void update(TradeBid po);
	void del(Integer id);
	Integer Oper(String opCode,String opPrice);
}
