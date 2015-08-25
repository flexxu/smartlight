package org.smartlight.usermanager.service;

import java.util.List;

import org.smartlight.daily.model.WorkDelay;
import org.smartlight.finance.model.GoldPrice;
import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.finance.model.GoldTrade;
import org.smartlight.usermanager.model.SysSms;

public interface SmsService extends BusinessObjectServiceMgr {
	

	List selectAll();
	SysSms get(Integer id);
	void add(SysSms po);
	void update(SysSms po);
	void del(Integer id);
	
	/*
	 * 发送提醒邮件，
	 * @Parameter subject 邮件标题
	 * @Parameter content 邮件内容，
	 * @return，
	 * */
	public int notifyMail(String subject,String content);
	
}
