package org.smartlight.web.finance;

//import javax.inject.Inject;

import java.util.List;

import org.smartlight.finance.model.GoldPrice;
import org.smartlight.finance.model.GoldTrade;
import org.smartlight.finance.service.BankGoldService;
import org.smartlight.finance.service.GoldTradeService;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/finance/gold/trade")
public class GoldTradeController extends BaseController{


	@Autowired
	protected GoldTradeService goldTradeService;	
	
	@RequestMapping("/list")
	public String index(Model model) {
		List ls=goldTradeService.selectAllTrade();
		model.addAttribute("items", ls);
		return "/finance/gold/trade/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return "/finance/gold/trade/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		GoldTrade po=goldTradeService.getTrade(id);
		model.addAttribute("item", po);
		return "/finance/gold/trade/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(GoldTrade po) {
		goldTradeService.addTrade(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(GoldTrade po) {
		goldTradeService.updTrade(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		goldTradeService.delTrade(id);
		return ajaxDoneSuccess("成功删除");
	}

	
}