package org.smartlight.usermanager.service.impl;


import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysAccess;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysGroup;
import org.smartlight.usermanager.model.SysRole;
import org.smartlight.usermanager.persistence.SysDeptMapper;
import org.smartlight.usermanager.persistence.SysRoleMapper;
import org.smartlight.usermanager.service.SysAccessService;
import org.smartlight.usermanager.service.SysDeptService;
import org.smartlight.usermanager.service.SysGroupService;
import org.smartlight.usermanager.service.SysNodeService;
import org.smartlight.usermanager.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("sysRoleService")
public class SysRoleServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	protected SysGroupService sysGroupService;
	
	@Autowired
	protected SysNodeService sysNodeService;
	
	@Autowired
	protected SysAccessService sysAccessService;
	
	public SysRole getSysRole(int id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	public void addSysRole(SysRole po) {
		sysRoleMapper.insert(po);
	}

	public void updSysRole(SysRole po) {
		sysRoleMapper.updateByPrimaryKey(po);
	}

	public void delSysRole(int id) {
		sysRoleMapper.deleteByPrimaryKey(id);
		
	}

	public List<SysRole> list(String condition, String orderField,
			int startIndex, int count) {
		return null;
	}
	
	public List<SysRole> selectAll(){
		return sysRoleMapper.selectAll();
	}
	
	public List selectForGrant(Integer roleId){
		List ls=sysGroupService.selectForGrant(roleId);
		for(int i=0;i<ls.size();i++){
			SysGroup po=(SysGroup)ls.get(i);
			po.setNodes(sysNodeService.selectForGrant(po.getId(),roleId));
		}
		return ls;		
	}
	
	public List selectUserGrant(Integer userId){
		return sysRoleMapper.selectByUser(userId);		
	}

	public void grant(Integer id, String nodeIds){
		sysAccessService.deleteByRole(id);
		String[] sa=nodeIds.split(",");
		for(int i=0;i<sa.length;i++){
			SysAccess po=new SysAccess();
			po.setLevel(1);
			po.setNodeId(Integer.parseInt(sa[i]));
			po.setPid(-1);
			po.setRoleId(id);
			sysAccessService.addSysAccess(po);			
		}
	}
	
}
