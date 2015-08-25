package org.smartlight.finance.service;

import java.util.List;

import org.smartlight.finance.model.GoldPrice;
import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.finance.model.GoldTrade;

public interface BankGoldService extends BusinessObjectServiceMgr {
	
	/*
	 * 获取银行最新报价，
	 * @return 
	 * */
	GoldPrice getGoldPrice(String bankType, String goldType);
	/*
	 * 保存到文件，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	int saveToFile(GoldPrice goldPrice);	
	/*
	 * 获取最新报价，并保存到文件，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */	
	int refreshPrice(String bankType, String goldType);	
	/*
	 * 获取最近10次报价，
	 * @paramter Periods 时间周期，单位分钟，最小为1，
	 * @return 报价列表，
	 * */
	public List<GoldPrice> getRecentPrices(int Periods);
	/*
	 * 发送提醒邮件，
	 * @Parameter subject 邮件标题
	 * @Parameter content 邮件内容，
	 * @return，
	 * */
	public int notifyMail(String subject,String content);
	
}
