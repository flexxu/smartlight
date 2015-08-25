package org.smartlight.business.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.smartlight.finance.service.BankGoldService;
import org.smartlight.framework.sys.business.BusinessFactory;


public class GoldPriceTask implements Runnable  {
	
	BankGoldService bgService;
	
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void run() {
		System.out.println("Start BankGoldPriceTask..."+format1.format(new Date()));
		if(bgService==null)
			bgService= BusinessFactory.getInstance().getService("bankGoldService");

		bgService.refreshPrice("1","1");

	}	
}
