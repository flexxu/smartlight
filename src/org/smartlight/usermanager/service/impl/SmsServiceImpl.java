package org.smartlight.usermanager.service.impl;

import java.util.Date;
import java.util.List;

import org.smartlight.framework.jms.JmsTemplateMail;
import org.smartlight.framework.jms.MailMessage;
import org.smartlight.framework.jms.MessageHelper;
import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.smartlight.usermanager.model.SysSms;
import org.smartlight.usermanager.persistence.SysSmsMapper;
import org.smartlight.usermanager.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("smsService")
public class SmsServiceImpl extends AbstractBusinessObjectServiceMgr
		implements SmsService {
	
	@Autowired
	private SysSmsMapper sysSmsMapper;
	
	@Autowired
	private JmsTemplateMail jmstempMail;
	
	@Autowired
	private MessageHelper messageHelper;	
	
	@Autowired
	private MailMessage mailMessage;
	
	public List selectAll() {
		return sysSmsMapper.selectAll();
	}

	public SysSms get(Integer id) {
		return sysSmsMapper.selectByPrimaryKey(id);
	}

	public void add(SysSms po) {
		po.setSendTime(new Date());
		sysSmsMapper.insert(po);
	//	this.notifyMail(po.getSubject(), po.getContent());
	}

	public void update(SysSms po) {
		sysSmsMapper.updateByPrimaryKey(po);
		
	}

	public void del(Integer id) {
		sysSmsMapper.deleteByPrimaryKey(id);
		
	}
	
	public int notifyMail(String subject,String content){
		mailMessage.setSubject(subject);
		mailMessage.setContent(content);
		jmstempMail.sendNotificationMail(mailMessage);
	//	messageHelper.sendMail(mailMessage);
		return 0;
	}
	
}
