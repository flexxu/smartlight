package org.smartlight.web.usermanager;

//import javax.inject.Inject;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.smartlight.framework.config.AppConfiguration;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysDictionary;
import org.smartlight.usermanager.service.SysDeptService;
import org.smartlight.usermanager.service.SysDictionaryService;
import org.smartlight.usermanager.service.SysUserService;
import org.smartlight.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/um/dict")
public class SysDictionaryController extends BaseController{

	@Autowired
	protected SysDictionaryService sysDictionaryService;

	@RequestMapping
	public String index(Model model) {
		List ls=sysDictionaryService.selectAll();
		model.addAttribute("items", ls);
		return "/um/sysdictionary/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return "/um/sysdictionary/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		SysDictionary po=sysDictionaryService.getSysDictionary(id);
		model.addAttribute("item", po);
		return "/um/sysdictionary/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(SysDictionary po) {
		sysDictionaryService.addSysDictionary(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(SysDictionary po) {
		sysDictionaryService.updSysDictionary(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/sysdictionary/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		sysDictionaryService.delSysDictionary(id);
		return ajaxDoneSuccess("成功删除");
	}

	
}