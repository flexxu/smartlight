package org.smartlight.business.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.smartlight.finance.service.BankGoldService;
import org.smartlight.finance.service.StockWatchService;
import org.smartlight.framework.sys.business.BusinessFactory;


public class StockWatchTask implements Runnable  {
	
	StockWatchService stockWatchService;
	
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void run() {
		System.out.println("Start BankGoldPriceTask..."+format1.format(new Date()));
		if(stockWatchService==null)
			stockWatchService= BusinessFactory.getInstance().getService("stockWatchService");

		try {
			stockWatchService.checkWatch();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
}
