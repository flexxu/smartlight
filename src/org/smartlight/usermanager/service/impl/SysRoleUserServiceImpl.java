package org.smartlight.usermanager.service.impl;


import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysRoleUser;
import org.smartlight.usermanager.persistence.SysRoleUserMapper;
import org.smartlight.usermanager.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("sysRoleUserService")
public class SysRoleUserServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SysRoleUserService {
	


	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;
	

	public void addRoleUser(SysRoleUser po) {
		sysRoleUserMapper.insert(po);		
	}

	public int deleteByUser(Integer userId) {
		return sysRoleUserMapper.deleteByUser(userId);
	}

}
