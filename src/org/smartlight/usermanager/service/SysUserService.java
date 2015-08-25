package org.smartlight.usermanager.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.persistence.BaseConditionVO;
import org.smartlight.usermanager.model.SysRole;
import org.smartlight.usermanager.model.SysUser;

public interface SysUserService extends BusinessObjectServiceMgr {
	SysUser getSysUser(int id);
	void addSysUser(SysUser sysUser);
	void updSysUser(SysUser sysUser);
	void delSysUser(int id);
	
	List<SysUser> list(String condition, String orderField,
			int startIndex, int count);
	
	/*
	 * 验证用户，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	SysUser getUserByLogin(String userName, String passWord);
	/*
	 * 认证用户，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	SysUser getUserByName(String userName);
	
	List<SysRole> selectAll();

	/*
	 * 初始化菜单，
	 * @param userId 用户id
	 * @return 菜单列表
	 * */
	List loadMenu(Integer userId);
	
	/*
	 * 获取指定部门的用户列表，
	 * @param deptId 部门id
	 * @return 用户列表
	 * */	
	List selectByDeptId(Integer deptId);
	/*
	 * 获取角色列表，
	 * @param id 用户id
	 * @return 角色列表
	 * */
	List selectForGrant(Integer userId);
	
	void grant(Integer userId, String roleIds);	
}
