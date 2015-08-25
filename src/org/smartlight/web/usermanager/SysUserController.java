package org.smartlight.web.usermanager;

//import javax.inject.Inject;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.smartlight.framework.config.AppConfiguration;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysRole;
import org.smartlight.usermanager.model.SysUser;
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
@RequestMapping("/um/user")
public class SysUserController extends BaseController{

	@Autowired
	protected SysUserService sysUserService;

	@RequestMapping
	public String index(Model model) {
		List ls=sysUserService.selectAll();
		model.addAttribute("items", ls);
		return "/um/user/list";
	}
	
	@RequestMapping("/list/{deptId}")
	public String list(@PathVariable Integer deptId,Model model) {
		List ls=sysUserService.selectByDeptId(deptId);
		model.addAttribute("items", ls);
		model.addAttribute("deptId", deptId);
		return "/um/user/list";
	}
	
	@RequestMapping("/grant/show/{id}")
	public String showGrant(@PathVariable Integer id,Model model) {
		SysUser po=sysUserService.getSysUser(id);
		model.addAttribute("item", po);
		List ls=sysUserService.selectForGrant(id);
		model.addAttribute("items", ls);
		return "/um/user/grant";
	}
	
	@RequestMapping("/add/{deptId}")
	public String add(@PathVariable Integer deptId,Model model) {
		model.addAttribute("deptId", deptId);
		return "/um/user/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		SysUser po=sysUserService.getSysUser(id);
		model.addAttribute("item", po);
		return "/um/user/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(SysUser po) {
		sysUserService.addSysUser(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(SysUser po) {
		sysUserService.updSysUser(po);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		sysUserService.delSysUser(id);
		return ajaxDoneSuccess("成功删除");
	}
	
	@RequestMapping(value = "/grant/save", method = RequestMethod.POST)
	public ModelAndView saveGrant(SysUser po) {
		sysUserService.grant(po.getId(),po.getRoleIds());
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
}