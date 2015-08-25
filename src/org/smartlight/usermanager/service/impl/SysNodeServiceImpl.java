package org.smartlight.usermanager.service.impl;


import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysNode;
import org.smartlight.usermanager.persistence.SysNodeMapper;
import org.smartlight.usermanager.service.SysNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("sysNodeService")
public class SysNodeServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SysNodeService {
	


	@Autowired
	private SysNodeMapper sysNodeMapper;
	
	public List selectAccessNode(Integer pId,Integer userId) {
		// TODO Auto-generated method stub
		return sysNodeMapper.selectByUserId(pId, userId);
	}

	public SysNode getSysNode(int id) {
		return sysNodeMapper.selectByPrimaryKey(id);
	}

	public void addSysNode(SysNode po) {
		sysNodeMapper.insert(po);
		
	}

	public void updSysNode(SysNode po) {
		sysNodeMapper.updateByPrimaryKey(po);
		
	}

	public void delSysNode(int id) {
		sysNodeMapper.deleteByPrimaryKey(id);
		
	}

	public List<SysNode> list(String condition, String orderField,
			int startIndex, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SysNode> selectAll() {
		return sysNodeMapper.selectAll();
	}

	public List<SysNode> selectByGroupId(Integer groupId) {
		return sysNodeMapper.selectByGroupId(groupId);
	}	
	
	public List<SysNode> selectForGrant(Integer groupId,Integer roleId) {
		return sysNodeMapper.selectForGrant(groupId,roleId);
	}	
	

}
