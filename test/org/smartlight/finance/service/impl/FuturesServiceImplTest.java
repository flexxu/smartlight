package org.smartlight.finance.service.impl;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import org.smartlight.finance.service.FuturesService;
import org.smartlight.framework.junit.BaseJunitCase;
import org.springframework.beans.factory.annotation.Autowired;

public class FuturesServiceImplTest extends BaseJunitCase{
	
	@Autowired
	private FuturesService futuresService;

	public FuturesService getFuturesService() {
		return futuresService;
	}

	public void setFuturesService(FuturesService futuresService) {
		this.futuresService = futuresService;
	}

	@Test
	public void testSelectLastestFutures() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFuturesData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFuturesHisData() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchFuturesHisData() {
		try {
			futuresService.fetchFuturesHisData("SR1609");
			assertTrue(true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testCalcMACD() {
		fail("Not yet implemented");
	}

}
