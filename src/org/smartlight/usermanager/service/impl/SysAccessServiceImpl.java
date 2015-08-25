package org.smartlight.usermanager.service.impl;


import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysAccess;
import org.smartlight.usermanager.persistence.SysAccessMapper;
import org.smartlight.usermanager.persistence.SysGroupMapper;
import org.smartlight.usermanager.service.SysAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("sysAccessService")
public class SysAccessServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SysAccessService {
	


	@Autowired
	private SysAccessMapper sysAccessMapper;
	
	public List selectAccessNode(Integer pId,Integer userId) {
		// TODO Auto-generated method stub
		return sysAccessMapper.selectByUserId(pId, userId);
	}

	public void addSysAccess(SysAccess po) {
		sysAccessMapper.insert(po);		
	}

	public int deleteByRole(Integer roleId) {
		return sysAccessMapper.deleteByRole(roleId);
	}	
}
