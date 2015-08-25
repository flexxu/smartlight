package org.smartlight.usermanager.service;

import java.util.List;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysRole;

public interface SysRoleService extends BusinessObjectServiceMgr {
	SysRole getSysRole(int id);
	void addSysRole(SysRole po);
	void updSysRole(SysRole po);
	void delSysRole(int id);
	
	List<SysRole> list(String condition, String orderField,
			int startIndex, int count);
	
	List<SysRole> selectAll();
	List selectForGrant(Integer roleId);
	List selectUserGrant(Integer userId);
	void grant(Integer id, String nodeIds);
}
