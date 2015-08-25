package org.smartlight.metatrader.service.impl;

import java.util.Date;
import java.util.List;

import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.metatrader.model.TradeBid;
import org.smartlight.metatrader.persistence.TradeBidMapper;
import org.smartlight.metatrader.service.TradeBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("tradeBidService")
public class TradeBidServiceImpl extends AbstractBusinessObjectServiceMgr
		implements TradeBidService {
	
	@Autowired
	private TradeBidMapper tradeBidMapper;

	public List selectAll() {
		return tradeBidMapper.selectAll();
	}

	public TradeBid get(Integer id) {
		return tradeBidMapper.selectByPrimaryKey(id);
	}

	public void add(TradeBid po) {
		tradeBidMapper.insert(po);
		
	}

	public void update(TradeBid po) {
		tradeBidMapper.updateByPrimaryKey(po);
		
	}

	public void del(Integer id) {
		tradeBidMapper.deleteByPrimaryKey(id);
		
	}

	public Integer Oper(String opCode, String opPrice) {
		Integer opType=1,opType1=0;
		if(opCode.equals("Sell")){
			opType=0;
			opType1=1;
		}
		List ls=tradeBidMapper.selectOpened(opType);
		if(ls.size()>0)
			return 0;
		else{
			ls=tradeBidMapper.selectOpened(opType1);
			if(ls.size()>0){
				TradeBid p=(TradeBid)ls.get(0);
				p.setOpFlag(1);
				tradeBidMapper.updateByPrimaryKeySelective(p);
			}
			TradeBid po=new TradeBid();
			po.setOpAmount(1);
			po.setOpCode("gold");
			po.setOpDate(new Date());
			po.setOpPrice(Double.parseDouble(opPrice));
			po.setOpType(opType);
			po.setOpFlag(0);
			tradeBidMapper.insert(po);
			return 1;
		}
	}
	

	
}
