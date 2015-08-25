package org.smartlight.usermanager.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.persistence.BaseConditionVO;
import org.smartlight.usermanager.model.SysGroup;
import org.smartlight.usermanager.model.SysNode;
import org.smartlight.usermanager.model.SysUser;

public interface SysNodeService extends BusinessObjectServiceMgr {
	

	/*
	 * 获取菜单节点，
	 * @param userId 用户id
	 * @return 菜单列表
	 * */
	List selectAccessNode(Integer groupId,Integer userId);
	SysNode getSysNode(int id);
	void addSysNode(SysNode po);
	void updSysNode(SysNode po);
	void delSysNode(int id);
	
	List<SysNode> list(String condition, String orderField,
			int startIndex, int count);
	
	List<SysNode> selectAll();
	List<SysNode> selectByGroupId(Integer groupId);
	List<SysNode> selectForGrant(Integer groupId,Integer roleId);
}
