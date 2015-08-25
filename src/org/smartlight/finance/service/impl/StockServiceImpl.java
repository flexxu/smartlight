package org.smartlight.finance.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.smartlight.common.util.FileUtils;
import org.smartlight.common.util.HttpClient;
import org.smartlight.finance.model.GoldPrice;
import org.smartlight.finance.model.Stock;
import org.smartlight.finance.persistence.GoldPriceMapper;
import org.smartlight.finance.persistence.StockMapper;
import org.smartlight.finance.service.BankGoldService;
import org.smartlight.finance.service.StockService;
import org.smartlight.framework.jms.JmsTemplateMail;
import org.smartlight.framework.jms.MailMessage;
import org.smartlight.framework.jms.MessageHelper;
import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("stockService")
public class StockServiceImpl extends AbstractBusinessObjectServiceMgr
		implements StockService {
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private JmsTemplateMail jmstempMail;
	
	@Autowired
	private MessageHelper messageHelper;	
	
	@Autowired
	private MailMessage mailMessage;
	
	/*
	 * 获取银行报价，
	 * @return 
	 * */
	public Stock getStock(String stockCode) throws ParseException{
		String content=HttpClient.getHtmlCode("http://hq.sinajs.cn/?list="+stockCode,"");

	    content=content.substring(content.indexOf("=")+1,content.length()-1);

	    String[] ar=content.split(",");
	    //"股票名字","今日开盘价    ","昨日收盘价","当前价格","今日最高价","今日最低价","竟买价","竞卖价","成交的股票数","成交金额(元)",
	    //"买一","买一","买二","买二","买三","买三","买四","买四","买五","买五","卖一","卖一","卖二","卖二","卖三","卖三",
	    //"卖四","卖四","卖五","卖五","日期","时间"
		Stock st=new Stock();
		st.setStockCode(stockCode);
		st.setStockName(ar[0]);
		st.setStockOpen(Float.parseFloat(ar[1]));
		st.setStockClose(Float.parseFloat(ar[3]));
		st.setStockHigh(Float.parseFloat(ar[4]));
		st.setStockLow(Float.parseFloat(ar[5]));
		st.setStockVol(Integer.parseInt(ar[8]));
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		st.setOpTime(format1.parse(ar[30]+" "+ar[31]));	

		return st;
	}
	/*
	 * 保存到文件，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	public int saveToFile(Stock stock){
		FileUtils fu=new FileUtils();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fileName=stock.getStockCode()+".day";
		try {
			fu.appendString(fileName, stock.getStockOpen()+","+stock.getStockClose()+","+stock.getStockHigh()+","+stock.getStockLow()+","+stock.getStockVol()+","+format1.format(stock.getOpTime())+";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		fu=null;		
		return 0;
	}
	
	public int notifyMail(String subject,String content){
		mailMessage.setSubject(subject);
		mailMessage.setContent(content);
		jmstempMail.sendNotificationMail(mailMessage);
	//	messageHelper.sendMail(mailMessage);
		return 0;
	}
	
}
