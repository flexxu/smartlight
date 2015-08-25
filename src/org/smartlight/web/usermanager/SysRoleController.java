package org.smartlight.web.usermanager;

//import javax.inject.Inject;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.smartlight.framework.config.AppConfiguration;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysRole;
import org.smartlight.usermanager.service.SysDeptService;
import org.smartlight.usermanager.service.SysGroupService;
import org.smartlight.usermanager.service.SysRoleService;
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
@RequestMapping("/um/role")
public class SysRoleController extends BaseController{

	@Autowired
	protected SysRoleService sysRoleService;


	@RequestMapping
	public String index(Model model) {
		List ls=sysRoleService.selectAll();
		model.addAttribute("items", ls);
		return "/um/role/list";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return "/um/role/add";
	}
	
	@RequestMapping("/grant/show/{id}")
	public String showGrant(@PathVariable Integer id,Model model) {
		SysRole po=sysRoleService.getSysRole(id);
		model.addAttribute("item", po);
		List ls=sysRoleService.selectForGrant(id);
		model.addAttribute("items", ls);
		return "/um/role/grant";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		SysRole po=sysRoleService.getSysRole(id);
		model.addAttribute("item", po);
		return "/um/role/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(SysRole po) {
		sysRoleService.addSysRole(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(SysRole po) {
		sysRoleService.updSysRole(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		sysRoleService.delSysRole(id);
		return ajaxDoneSuccess("成功删除");
	}
	
	@RequestMapping(value = "/grant/save", method = RequestMethod.POST)
	public ModelAndView saveGrant(SysRole po) {
		sysRoleService.grant(po.getId(),po.getNodeIds());
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}

	
}