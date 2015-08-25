package org.smartlight.web.usermanager;

//import javax.inject.Inject;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.smartlight.framework.config.AppConfiguration;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.service.SysDeptService;
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
@RequestMapping("/um/dept")
public class SysDeptController extends BaseController{

	@Autowired
	protected SysDeptService sysDeptService;

	@RequestMapping
	public String index(Model model) {
		List ls=sysDeptService.selectAll();
		model.addAttribute("items", ls);
		return "/um/dept/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return "/um/dept/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		SysDept po=sysDeptService.getSysDept(id);
		model.addAttribute("item", po);
		return "/um/dept/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(SysDept dept) {
		sysDeptService.addSysDept(dept);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(SysDept dept) {
		sysDeptService.updSysDept(dept);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		sysDeptService.delSysDept(id);
		return ajaxDoneSuccess("成功删除");
	}

	
}