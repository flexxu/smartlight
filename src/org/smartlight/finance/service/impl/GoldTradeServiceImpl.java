package org.smartlight.finance.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.smartlight.common.util.FileUtils;
import org.smartlight.common.util.HttpClient;
import org.smartlight.finance.model.GoldPrice;
import org.smartlight.finance.model.GoldTrade;
import org.smartlight.finance.persistence.GoldPriceMapper;
import org.smartlight.finance.persistence.GoldTradeMapper;
import org.smartlight.finance.service.GoldTradeService;
import org.smartlight.framework.jms.JmsTemplateMail;
import org.smartlight.framework.jms.MailMessage;
import org.smartlight.framework.jms.MessageHelper;
import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("goldTradeService")
public class GoldTradeServiceImpl extends AbstractBusinessObjectServiceMgr
		implements GoldTradeService {
	
	@Autowired
	private GoldTradeMapper goldTradeMapper;

	public List selectAllTrade() {
		return goldTradeMapper.selectAll();
	}

	public GoldTrade getTrade(Integer tradeId) {
		return goldTradeMapper.selectByPrimaryKey(tradeId);
	}

	public void addTrade(GoldTrade po) {
		goldTradeMapper.insert(po);
		
	}

	public void updTrade(GoldTrade po) {
		goldTradeMapper.updateByPrimaryKey(po);
		
	}

	public void delTrade(Integer tradeId) {
		goldTradeMapper.deleteByPrimaryKey(tradeId);
		
	}
	

	
}
