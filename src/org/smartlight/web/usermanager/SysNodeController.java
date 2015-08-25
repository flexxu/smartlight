package org.smartlight.web.usermanager;

//import javax.inject.Inject;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.smartlight.framework.config.AppConfiguration;
import org.smartlight.usermanager.model.SysDept;
import org.smartlight.usermanager.model.SysNode;
import org.smartlight.usermanager.service.SysDeptService;
import org.smartlight.usermanager.service.SysNodeService;
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
@RequestMapping("/um/node")
public class SysNodeController extends BaseController{

	@Autowired
	protected SysNodeService sysNodeService;

	@RequestMapping
	public String index(Model model) {
		List ls=sysNodeService.selectAll();
		model.addAttribute("items", ls);
		return "/um/node/list";
	}
	
	@RequestMapping("/list/{groupId}")
	public String list(@PathVariable Integer groupId,Model model) {
		List ls=sysNodeService.selectByGroupId(groupId);
		model.addAttribute("items", ls);
		model.addAttribute("groupId", groupId);
		return "/um/node/list";
	}
	
	@RequestMapping("/add/{groupId}")
	public String add(@PathVariable Integer groupId,Model model) {
		model.addAttribute("groupId", groupId);
		model.addAttribute("pid", -1);
		return "/um/node/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		SysNode po=sysNodeService.getSysNode(id);
		model.addAttribute("item", po);
		return "/um/node/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(SysNode node) {
		node.setStatus(1);
		sysNodeService.addSysNode(node);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(SysNode node) {
		sysNodeService.updSysNode(node);
		return ajaxDoneSuccess(getMessage("msg.operation.success"));
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		sysNodeService.delSysNode(id);
		return ajaxDoneSuccess("成功删除");
	}

	
}