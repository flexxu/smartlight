package org.smartlight.web.usermanager;

//import javax.inject.Inject;

import java.util.List;

import org.smartlight.usermanager.model.SysSms;
import org.smartlight.usermanager.service.SmsService;
import org.smartlight.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/um/sms")
public class SmsController extends BaseController{


	@Autowired
	protected SmsService smsService;	
	
	@RequestMapping
	public String index(Model model) {
		List ls=smsService.selectAll();
		model.addAttribute("items", ls);
		return "/um/sms/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return "/um/sms/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		SysSms po=smsService.get(id);
		model.addAttribute("item", po);
		return "/um/sms/edit";
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable Integer id,Model model) {
		SysSms po=smsService.get(id);
		model.addAttribute("item", po);
		return "/um/sms/view";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(SysSms po) {
		smsService.add(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(SysSms po) {
		smsService.update(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		smsService.del(id);
		return ajaxDoneSuccess("成功删除");
	}

	
}