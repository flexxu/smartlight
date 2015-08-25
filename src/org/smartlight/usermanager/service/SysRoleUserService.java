package org.smartlight.usermanager.service;

import java.util.List;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysAccess;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysRole;
import org.smartlight.usermanager.model.SysRoleUser;

public interface SysRoleUserService extends BusinessObjectServiceMgr {
	void addRoleUser(SysRoleUser po);
	int deleteByUser(Integer userId);
}
