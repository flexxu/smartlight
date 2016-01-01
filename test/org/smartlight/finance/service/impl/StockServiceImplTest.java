package org.smartlight.finance.service.impl;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.smartlight.finance.model.Futures;
import org.smartlight.finance.service.StockService;
import org.smartlight.framework.junit.BaseJunitCase;
import org.springframework.beans.factory.annotation.Autowired;

public class StockServiceImplTest{

	private StockService stockService;
	@Before
	public void setUp(){
		stockService=new StockServiceImpl();
	}
	
	@After
	public void tearDown(){
		stockService=null;
	}
	

	

}
