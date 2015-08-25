package org.smartlight.usermanager.service.impl;


import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysAccess;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysGroup;
import org.smartlight.usermanager.model.SysRole;
import org.smartlight.usermanager.model.SysRoleUser;
import org.smartlight.usermanager.model.SysUser;
import org.smartlight.usermanager.persistence.SysUserMapper;
import org.smartlight.usermanager.service.SysDeptService;
import org.smartlight.usermanager.service.SysGroupService;
import org.smartlight.usermanager.service.SysNodeService;
import org.smartlight.usermanager.service.SysRoleService;
import org.smartlight.usermanager.service.SysRoleUserService;
import org.smartlight.usermanager.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("sysUserService")
public class SysUserServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysNodeService sysNodeService;
	
	@Autowired
	private SysGroupService sysGroupService;

	@Autowired
	private SysDeptService sysDeptService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysRoleUserService sysRoleUserService;
	
	public SysUser getSysUser(int id) {
		SysUser user=sysUserMapper.selectByPrimaryKey(id);
		return user;
	}

	public void addSysUser(SysUser sysUser) {
		SysDept po=sysDeptService.getSysDept(sysUser.getDepartmentId());
		sysUser.setDepartmentName(po.getDepartmentName());
		sysUserMapper.insert(sysUser);
	}

	public void updSysUser(SysUser sysUser) {
		sysUserMapper.updateByPrimaryKeySelective(sysUser);		
	}

	public void delSysUser(int id) {
		sysUserMapper.deleteByPrimaryKey(id);
		
	}

	public List<SysUser> list(String condition, String orderField,
			int startIndex, int count) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SysUser getUserByLogin(String userName, String passWord){
		SysUser user=sysUserMapper.selectByName(userName);
		if(user!=null && user.getPassword().equals(passWord))
			return user;
		
		return null;
	}
	
	public SysUser getUserByName(String userName){
		return sysUserMapper.selectByName(userName);
	}

	public List loadMenu(Integer userId) {
		// TODO Auto-generated method stub
		List<SysGroup> ls= sysGroupService.selectAccessGroup(userId);
		for(int i=0;i<ls.size();i++){
			SysGroup po=ls.get(i);
			List lsNode=sysNodeService.selectAccessNode(po.getId(), userId);
			po.setNodes(lsNode);
		}
		return ls;
	}	
	
	public List<SysRole> selectAll(){
		return sysUserMapper.selectAll();
	}
	
	/*
	 * 获取指定部门的用户列表，
	 * @param deptId 部门id
	 * @return 用户列表
	 * */	
	public List selectByDeptId(Integer deptId){
		return sysUserMapper.selectByDeptId(deptId);
	}

	public List selectForGrant(Integer userId) {
		return sysRoleService.selectUserGrant(userId);
	}
	
	public void grant(Integer userId, String roleIds){
		sysRoleUserService.deleteByUser(userId);
		String[] sa=roleIds.split(",");
		for(int i=0;i<sa.length;i++){
			SysRoleUser po=new SysRoleUser();
			po.setRoleId(Integer.parseInt(sa[i]));
			po.setUserId(userId);
			sysRoleUserService.addRoleUser(po);			
		}
	}
	
}
