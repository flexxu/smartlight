package org.smartlight.finance.service;

import java.text.ParseException;
import java.util.List;

import org.smartlight.finance.model.Futures;
import org.smartlight.finance.model.GoldPrice;
import org.smartlight.finance.model.Stock;
import org.smartlight.framework.sys.business.BusinessObjectServiceMgr;
import org.smartlight.finance.model.GoldTrade;

public interface FuturesService extends BusinessObjectServiceMgr {
	
	/*
	 * 查询最新一笔数据，
	 * @return 查询到，返回查询对象，否则，返回null
	 * */
	public Futures selectLastestFutures(String futuresCode);
	
	/*
	 * 保存到数据库，
	 * @return 保存成功，返回1，否则，返回-1
	 * */
	int saveToDb(Futures futures);	
	/*
	 * 保存到文件，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	int saveToFile(Futures futures);	
	/*
	 * 发送提醒邮件，
	 * @Parameter subject 邮件标题
	 * @Parameter content 邮件内容，
	 * @return，
	 * */
	public int notifyMail(String subject,String content);
	
	/*
	 * 获取期货实时报价，
	 * @return 
	 * */
	public Futures getFuturesData(String futuresCode) throws ParseException;
	
	/*
	 * 获取期货历史数据，
	 * @return 
	 * */
	public List getFuturesHisData(String futuresCode,Integer period) throws ParseException;
	
	/*
	 * 获取交易所最新期货数据，
	 * @return 
	 * */
	public void fetchFuturesHisData(String futuresCode) throws ParseException;

}
