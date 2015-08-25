package org.smartlight.usermanager.service.impl;


import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysGroup;
import org.smartlight.usermanager.persistence.SysGroupMapper;
import org.smartlight.usermanager.service.SysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("sysGroupService")
public class SysGroupServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SysGroupService {
	@Autowired
	private SysGroupMapper sysGroupMapper;
	

	public List selectAccessGroup(Integer userId) {
		return sysGroupMapper.selectByUserId(userId);
	}


	public SysGroup getSysGroup(int id) {
		return sysGroupMapper.selectByPrimaryKey(id);
	}


	public void addSysGroup(SysGroup group) {
		sysGroupMapper.insert(group);
		
	}


	public void updSysGroup(SysGroup group) {
		sysGroupMapper.updateByPrimaryKey(group);
		
	}


	public void delSysGroup(int id) {
		sysGroupMapper.deleteByPrimaryKey(id);		
	}


	public List<SysGroup> list(String condition, String orderField,
			int startIndex, int count) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<SysGroup> selectAll() {
		return sysGroupMapper.selectAll();
	}	
	
	public List selectForGrant(Integer roleId){
		return sysGroupMapper.selectForGrant(roleId);
	}
	
}
