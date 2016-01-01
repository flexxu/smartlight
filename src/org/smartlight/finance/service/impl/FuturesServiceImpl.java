package org.smartlight.finance.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.smartlight.common.util.FileUtils;
import org.smartlight.common.util.HttpClient;
import org.smartlight.finance.model.Futures;
import org.smartlight.finance.model.Stock;
import org.smartlight.finance.persistence.FuturesMapper;
import org.smartlight.finance.service.FuturesService;
import org.smartlight.framework.jms.JmsTemplateMail;
import org.smartlight.framework.jms.MailMessage;
import org.smartlight.framework.jms.MessageHelper;
import org.smartlight.framework.maths.StockIndicator;
import org.smartlight.framework.maths.impl.StockIndicatorImpl;
import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("futuresService")
public class FuturesServiceImpl extends AbstractBusinessObjectServiceMgr
		implements FuturesService {
	
	@Autowired
	private FuturesMapper futuresMapper;
	
	@Autowired
	private JmsTemplateMail jmstempMail;
	
	@Autowired
	private MessageHelper messageHelper;	
	
	@Autowired
	private MailMessage mailMessage;
	
	/*
	 * 保存到文件，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	public int saveToFile(Futures futures){
		FileUtils fu=new FileUtils();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fileName=futures.getCode()+".day";
		try {
			fu.appendString(fileName, futures.getOpen()+","+futures.getClose()+","+futures.getHigh()+","+futures.getLow()+","+futures.getVol()+","+format1.format(futures.getOpTime())+";");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		fu=null;		
		return 0;
	}
	
	/*
	 * 保存到文件，
	 * @return 验证成功，返回SysUser对象，验证失败，返回null
	 * */
	public int saveToDb(Futures futures){
		return futuresMapper.insert(futures);
	}
	
	public Futures selectLastestFutures(String futuresCode){
		List<Futures> ls=futuresMapper.selectLastest(futuresCode);
		if(ls.size()>0)
			return ls.get(0);
		else
			return null;
	}

	public int notifyMail(String subject,String content){
		mailMessage.setSubject(subject);
		mailMessage.setContent(content);
		jmstempMail.sendNotificationMail(mailMessage);
	//	messageHelper.sendMail(mailMessage);
		return 0;
	}
	
	/*
	 * 获取期货实时报价，
	 * @return 
	 * */
	public Futures getFuturesData(String futuresCode) throws ParseException{
		String content=HttpClient.getHtmlCode("http://hq.sinajs.cn/list="+futuresCode,"utf-8");

	    content=content.substring(content.indexOf("=")+2,content.length()-2);
	    String[] ar=content.split(",");
	    //"名字","不明数字","开盘价","最高价","最低价","昨日收盘价","买价","卖价","最新价","结算价","昨结算",
	    //"买  量","卖  量","持仓量","成交量","交易所简称","品种名简称","日期"
	    Futures st=new Futures();
		st.setCode(futuresCode);
		st.setName(ar[0]);
		st.setOpen(Double.parseDouble(ar[2]));
		st.setHigh(Double.parseDouble(ar[3]));
		st.setLow(Double.parseDouble(ar[4]));
		st.setLastClose(Double.parseDouble(ar[5]));
		st.setClose(Double.parseDouble(ar[8]));
		st.setVol(Integer.parseInt(ar[14]));
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		st.setOpTime(format1.parse(ar[17]));	

		return st;
	}
	
	/*
	 * 获取期货历史数据，
	 * @return 
	 * */
	public List getFuturesHisData(String futuresCode,Integer period) throws ParseException{
		return futuresMapper.selectAll(futuresCode);
	}
	/*
	 * 获取交易所最新期货数据，
	 * @return 
	 * */
	public void fetchFuturesHisData(String futuresCode) throws ParseException{
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Futures recent=this.selectLastestFutures(futuresCode);
		if(recent==null) recent=new Futures();
		String content=HttpClient.getHtmlCode("http://stock2.finance.sina.com.cn/futures/api/json.php/IndexService.getInnerFuturesMiniKLine5m?symbol="+futuresCode,"");
	    content=content.substring(2,content.length()-3);
	    String[] objs=content.split("\\],\\[");
	    for(int i=0;i<objs.length;i++){
	    	String[] ar=objs[i].split(",");
		    //"时间","开盘价","最高价","最低价","收盘价","成交量"
	    	Date dt=format1.parse(ar[0].substring(1,ar[0].length()-2));
			if(dt.after(recent.getOpTime())){
			    Futures st=new Futures();
				st.setOpTime(dt);
				st.setCode(futuresCode);
				st.setOpen(Double.parseDouble(ar[1].substring(1,ar[1].length()-2)));
				st.setHigh(Double.parseDouble(ar[2].substring(1,ar[2].length()-2)));
				st.setLow(Double.parseDouble(ar[3].substring(1,ar[3].length()-2)));
				st.setClose(Double.parseDouble(ar[4].substring(1,ar[4].length()-2)));
				st.setVol(Integer.parseInt(ar[5].substring(1,ar[5].length()-2)));
				this.saveToDb(st);
			}else{
				break;
			}
	    }
	}
	
	public HashMap<String,Double> calcMACD(String futuresCode,int period,int shortPeriod,int longPeriod,int midPeriod) throws ParseException{
		List<Double> ls=futuresMapper.selectCloseList(futuresCode);
		StockIndicator si=new StockIndicatorImpl();
		return si.getMACD(ls, shortPeriod, longPeriod, midPeriod);
	}
	
}
