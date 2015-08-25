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
import org.smartlight.finance.persistence.GoldPriceMapper;
import org.smartlight.finance.service.BankGoldService;
import org.smartlight.framework.jms.JmsTemplateMail;
import org.smartlight.framework.jms.MailMessage;
import org.smartlight.framework.jms.MessageHelper;
import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("bankGoldService")
public class BankGoldServiceImpl extends AbstractBusinessObjectServiceMgr
		implements BankGoldService {
	
	@Autowired
	private GoldPriceMapper goldPriceMapper;
	
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
	public GoldPrice getGoldPrice(String bankType, String goldType){
		String content=HttpClient.getHtmlCode("http://www.icbc.com.cn/ICBCDynamicSite/Charts/GoldTendencyPicture.aspx","");

	    content=content.substring(content.indexOf("人民币账户黄金"),content.indexOf("人民币账户白银"));

	    String patternStr=">(\\s*\\d+\\.*\\d*\\s*)</td>"; 
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(content);
		List<Double> priceList=new ArrayList<Double>();
		while (matcher.find()){
			priceList.add(Double.parseDouble(matcher.group(1).trim()));
			System.out.println(matcher.group(1).trim());
		}
		GoldPrice gd=new GoldPrice();
		gd.setName("人民币账户黄金");
		gd.setOpTime(new Date());
		gd.setOpen(priceList.get(2));
		gd.setSpread(priceList.get(1)-priceList.get(2));
		gd.setClose(gd.getOpen());
		gd.setHigh(gd.getOpen());
		gd.setLow(gd.getOpen());
		gd.setTradeDept("工商银行");
		

		return gd;
	}
	/*
	 * 保存到文件，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	public int saveToFile(GoldPrice goldPrice){
		FileUtils fu=new FileUtils();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fileName=format.format(new Date())+".day";
		try {
			fu.appendString(fileName, goldPrice.getClose()+","+goldPrice.getSpread()+","+format1.format(goldPrice.getOpTime())+";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		fu=null;		
		return 0;
	}
	
	/*
	 * 保存到数据库，
	 *
	 * */
	public void saveToDB(GoldPrice goldPrice){
		goldPriceMapper.insert(goldPrice);		
	}
	
	public int refreshPrice(String bankType, String goldType){
		GoldPrice gd=this.getGoldPrice(bankType, goldType);
		this.saveToDB(gd);
		return this.saveToFile(gd);
	}
	
	public int calcPeriodData(int PeriodDays){
		return 0;
	}
	
	public List<GoldPrice> getRecentPrices(int Periods){
		return goldPriceMapper.selectRecent(Periods);
	}
	
	public int notifyMail(String subject,String content){
		mailMessage.setSubject(subject);
		mailMessage.setContent(content);
		jmstempMail.sendNotificationMail(mailMessage);
	//	messageHelper.sendMail(mailMessage);
		return 0;
	}
	
}
