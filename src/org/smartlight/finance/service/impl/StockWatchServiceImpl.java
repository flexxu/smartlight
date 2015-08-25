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
import org.smartlight.finance.model.GoldTrade;
import org.smartlight.finance.model.Stock;
import org.smartlight.finance.model.StockWatch;
import org.smartlight.finance.persistence.GoldPriceMapper;
import org.smartlight.finance.persistence.GoldTradeMapper;
import org.smartlight.finance.persistence.StockWatchMapper;
import org.smartlight.finance.service.GoldTradeService;
import org.smartlight.finance.service.StockService;
import org.smartlight.finance.service.StockWatchService;
import org.smartlight.framework.jms.JmsTemplateMail;
import org.smartlight.framework.jms.MailMessage;
import org.smartlight.framework.jms.MessageHelper;
import org.smartlight.framework.sys.business.AbstractBusinessObjectServiceMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("stockWatchService")
public class StockWatchServiceImpl extends AbstractBusinessObjectServiceMgr
		implements StockWatchService {
	
	@Autowired
	private StockWatchMapper stockWatchMapper;
	
	@Autowired
	private StockService stockService;

	public List<StockWatch> selectValid() {
		return stockWatchMapper.selectValid();
	}

	public StockWatch getStockWatch(Integer watchId) {
		return stockWatchMapper.selectByPrimaryKey(watchId);
	}

	public void addStockWatch(StockWatch po) {
		stockWatchMapper.insert(po);
		
	}

	public void updStockWatch(StockWatch po) {
		stockWatchMapper.updateByPrimaryKey(po);
		
	}

	public void delStockWatch(Integer watchId) {
		stockWatchMapper.deleteByPrimaryKey(watchId);
		
	}
	
	public void checkWatch() throws ParseException{
		List<StockWatch> ls=this.selectValid();
		for(int i=0;i<ls.size();i++){
			StockWatch po=ls.get(i);
			Stock poStock=stockService.getStock(po.getStockCode());
			if(poStock.getStockClose()>po.getWatchtop()){
				stockService.notifyMail(po.getStockCode()+"发出买入信号："+po.getWatchtop(), po.getStockCode()+"发出买入信号："+po.getWatchtop());
			}else if(poStock.getStockClose()<po.getWatchbottom()){
				stockService.notifyMail(po.getStockCode()+"发出卖出信号："+po.getWatchbottom(), po.getStockCode()+"发出卖出信号："+po.getWatchbottom());
			}
		}
	}
	

	
}
