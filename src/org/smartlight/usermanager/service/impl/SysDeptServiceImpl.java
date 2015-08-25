package org.smartlight.usermanager.service.impl;


import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.persistence.SysDeptMapper;
import org.smartlight.usermanager.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("sysDeptService")
public class SysDeptServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SysDeptService {
	
	@Autowired
	private SysDeptMapper sysDeptMapper;
	
	public SysDept getSysDept(int id) {
		return sysDeptMapper.selectByPrimaryKey(id);
	}

	public void addSysDept(SysDept dept) {
		sysDeptMapper.insert(dept);
	}

	public void updSysDept(SysDept dept) {
		sysDeptMapper.updateByPrimaryKey(dept);
	}

	public void delSysDept(int id) {
		sysDeptMapper.deleteByPrimaryKey(id);
		
	}

	public List<SysDept> list(String condition, String orderField,
			int startIndex, int count) {
		return null;
	}
	
	public List selectAll(){
		return sysDeptMapper.selectAll();
	}


	
	
}
