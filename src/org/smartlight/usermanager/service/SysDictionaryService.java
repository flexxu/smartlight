package org.smartlight.usermanager.service;

import java.util.List;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysDictionary;

public interface SysDictionaryService extends BusinessObjectServiceMgr {
	SysDictionary getSysDictionary(int id);
	void addSysDictionary(SysDictionary po);
	void updSysDictionary(SysDictionary po);
	void delSysDictionary(int id);
	
	List<SysDictionary> selectByType(String dictType);
	
	SysDictionary selectByName(String name);
	
	List<SysDictionary> selectAll();	

}
