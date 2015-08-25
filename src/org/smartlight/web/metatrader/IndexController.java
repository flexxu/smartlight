package org.smartlight.web.metatrader;

//import javax.inject.Inject;

import java.util.List;

import org.smartlight.metatrader.service.TradeBidService;
import org.smartlight.usermanager.model.SysSms;
import org.smartlight.usermanager.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("metatrader.indexController")
@RequestMapping("/mt")
public class IndexController {

	@Autowired
	protected SmsService smsService;	
	@Autowired
	protected TradeBidService tradeBidService;	
	
	@RequestMapping("/operate")
	public String Operate(@RequestParam("opCode") String opCode,@RequestParam("params") String params) {
		System.out.println("收到请求");
		System.out.println(opCode);
		System.out.println(params);
		Integer ret=tradeBidService.Oper(opCode, params);
		if(ret==1){//如果信号不重复，发送消息提醒
			SysSms po=new SysSms();
			po.setSubject("收到请求:"+opCode);
			po.setContent("请求参数："+params);
			smsService.add(po);
		}
		System.out.println("请求结束");
		return "test";
	}	
	
	@RequestMapping("/list")
	public String list(Model model) {
		List ls=tradeBidService.selectAll();
		model.addAttribute("items", ls);
		return "/finance/gold/mt/list";
	}
}