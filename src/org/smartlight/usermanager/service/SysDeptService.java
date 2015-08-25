package org.smartlight.usermanager.service;

import java.util.List;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysDept;

public interface SysDeptService extends BusinessObjectServiceMgr {
	SysDept getSysDept(int id);
	void addSysDept(SysDept dept);
	void updSysDept(SysDept dept);
	void delSysDept(int id);
	
	List<SysDept> list(String condition, String orderField,
			int startIndex, int count);
	
	List<SysDept> selectAll();

}
