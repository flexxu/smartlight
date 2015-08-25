package org.smartlight.finance.service;

import java.text.ParseException;
import java.util.List;

import org.smartlight.finance.model.GoldPrice;
import org.smartlight.finance.model.Stock;
import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.finance.model.GoldTrade;

public interface StockService extends BusinessObjectServiceMgr {
	
	/*
	 * 获取银行最新报价，
	 * @return 
	 * */
	Stock getStock(String stockCode) throws ParseException;
	/*
	 * 保存到文件，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	int saveToFile(Stock stock);	
	/*
	 * 发送提醒邮件，
	 * @Parameter subject 邮件标题
	 * @Parameter content 邮件内容，
	 * @return，
	 * */
	public int notifyMail(String subject,String content);
	
}
