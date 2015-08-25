package org.smartlight.web.daily;

//import javax.inject.Inject;

import java.util.List;

import org.smartlight.daily.model.WorkDelay;
import org.smartlight.daily.service.DelayService;
import org.smartlight.finance.model.GoldTrade;
import org.smartlight.finance.service.GoldTradeService;
import org.smartlight.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dh/delay")
public class DelayController extends BaseController{


	@Autowired
	protected DelayService delayService;	
	
	@RequestMapping("/list")
	public String index(Model model) {
		List ls=delayService.selectAll();
		model.addAttribute("items", ls);
		return "/dh/delay/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return "/dh/delay/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		WorkDelay po=delayService.get(id);
		model.addAttribute("item", po);
		return "/dh/delay/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(WorkDelay po) {
		delayService.add(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(WorkDelay po) {
		delayService.update(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		delayService.del(id);
		return ajaxDoneSuccess("成功删除");
	}

	
}