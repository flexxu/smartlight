package org.smartlight.web.finance;

//import javax.inject.Inject;

import java.util.List;

import org.smartlight.finance.model.GoldPrice;
import org.smartlight.finance.service.BankGoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("finance.indexController")
@RequestMapping("/finance")
public class IndexController {

	@Autowired
	protected BankGoldService bankGoldService;	
	
	@RequestMapping("/getRecent")
	@ResponseBody
	public List<GoldPrice> getRecent() {
		List<GoldPrice> ls=bankGoldService.getRecentPrices(1);
		return ls;
	}
	
	@RequestMapping("/notifyMail")
	public String notifyMail(@RequestParam("subject") String subject,@RequestParam("content") String content) {
		bankGoldService.notifyMail(subject, content);
		return "1";
	}
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("webRoot", "http://localhost/smartlight");
		return mav;
	}

	
}