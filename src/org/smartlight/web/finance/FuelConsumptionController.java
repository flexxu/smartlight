package org.smartlight.web.finance;

//import javax.inject.Inject;

import java.util.List;

import org.smartlight.finance.model.FuelConsumption;
import org.smartlight.finance.model.GoldTrade;
import org.smartlight.finance.service.FuelConsumptionService;
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
@RequestMapping("/finance/fuleconsumption")
public class FuelConsumptionController extends BaseController{


	@Autowired
	protected FuelConsumptionService fuelConsumptionService;	
	
	@RequestMapping("/list")
	public String index(Model model) {
		List ls=fuelConsumptionService.selectAll();
		model.addAttribute("items", ls);
		return "/finance/fuelconsumption/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return "/finance/fuelconsumption/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		FuelConsumption po=fuelConsumptionService.get(id);
		model.addAttribute("item", po);
		return "/finance/fuelconsumption/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(FuelConsumption po) {
		fuelConsumptionService.add(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(FuelConsumption po) {
		fuelConsumptionService.update(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		fuelConsumptionService.del(id);
		return ajaxDoneSuccess("成功删除");
	}

	
}