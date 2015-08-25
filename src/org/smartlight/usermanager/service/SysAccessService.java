package org.smartlight.usermanager.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.persistence.BaseConditionVO;
import org.smartlight.usermanager.model.SysAccess;
import org.smartlight.usermanager.model.SysGroup;
import org.smartlight.usermanager.model.SysUser;

public interface SysAccessService extends BusinessObjectServiceMgr {
	

	/*
	 * 获取菜单节点，
	 * @param userId 用户id
	 * @return 菜单列表
	 * */
	List selectAccessNode(Integer groupId,Integer userId);	
	void addSysAccess(SysAccess po);
	int deleteByRole(Integer roleId);
}
