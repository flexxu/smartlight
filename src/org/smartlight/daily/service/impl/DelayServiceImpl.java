package org.smartlight.daily.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.smartlight.common.util.FileUtils;
import org.smartlight.common.util.HttpClient;
import org.smartlight.daily.model.WorkDelay;
import org.smartlight.daily.persistence.WorkDelayMapper;
import org.smartlight.daily.service.DelayService;
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
@Service("delayService")
public class DelayServiceImpl extends AbstractBusinessObjectServiceMgr
		implements DelayService {
	
	@Autowired
	private WorkDelayMapper workDelayMapper;

	public List selectAll() {
		return workDelayMapper.selectAll();
	}

	public WorkDelay get(Integer id) {
		return workDelayMapper.selectByPrimaryKey(id);
	}

	public void add(WorkDelay po) {
		workDelayMapper.insert(po);
		
	}

	public void update(WorkDelay po) {
		workDelayMapper.updateByPrimaryKey(po);
		
	}

	public void del(Integer id) {
		workDelayMapper.deleteByPrimaryKey(id);
		
	}
	

	
}
