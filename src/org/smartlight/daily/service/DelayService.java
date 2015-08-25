package org.smartlight.daily.service;

import java.util.List;

import org.smartlight.daily.model.WorkDelay;
import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;

public interface DelayService extends BusinessObjectServiceMgr {
	

	List selectAll();
	WorkDelay get(Integer id);
	void add(WorkDelay po);
	void update(WorkDelay po);
	void del(Integer id);
}
