package org.smartlight.usermanager.service.impl;


import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysDictionary;
import org.smartlight.usermanager.persistence.SysDictionaryMapper;
import org.smartlight.usermanager.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("sysDictionaryService")
public class SysDictionaryServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SysDictionaryService {
	
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;
	
	public SysDictionary getSysDictionary(int id) {
		return sysDictionaryMapper.selectByPrimaryKey(id);
	}

	public void addSysDictionary(SysDictionary po) {
		sysDictionaryMapper.insert(po);
	}

	public void updSysDictionary(SysDictionary po) {
		sysDictionaryMapper.updateByPrimaryKey(po);
	}

	public void delSysDictionary(int id) {
		sysDictionaryMapper.deleteByPrimaryKey(id);
		
	}

	public List<SysDictionary> selectByType(String dictType) {
		return sysDictionaryMapper.selectByType(dictType);
	}
	
	public SysDictionary selectByName(String name) {
		return sysDictionaryMapper.selectByName(name);
	}

	public List<SysDictionary> selectAll() {
		return sysDictionaryMapper.selectAll();
	}	
	
}
