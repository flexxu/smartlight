package org.smartlight.usermanager.service;

import java.util.List;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysGroup;

public interface SysGroupService extends BusinessObjectServiceMgr {
	
	/*
	 * 获取菜单组，
	 * @param userId 用户id
	 * @return 菜单列表
	 * */
	List selectAccessGroup(Integer userId);	
	SysGroup getSysGroup(int id);
	void addSysGroup(SysGroup group);
	void updSysGroup(SysGroup group);
	void delSysGroup(int id);
	
	List<SysGroup> list(String condition, String orderField,
			int startIndex, int count);
	
	List<SysGroup> selectAll();
	List selectForGrant(Integer roleId);
	
}
