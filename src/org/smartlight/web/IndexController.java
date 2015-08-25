package org.smartlight.web;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.smartlight.common.util.VerifyCodeUtil;
import org.smartlight.usermanager.model.SysUser;
import org.smartlight.usermanager.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController{

	@Autowired
	protected SysUserService sysUserService;
	

	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		String webRoot=request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getServletPath()));
		mav.addObject("webRoot", webRoot);
		return mav;
	}
	
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request) {	
		ModelAndView mav = new ModelAndView("login");
		String webRoot=request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getServletPath()));
		mav.addObject("webRoot", webRoot);
		return mav;
	}
	
	@RequestMapping("loadMenu")
	public ModelAndView loadMenu(HttpServletRequest request) {
		SysUser su=this.getCurrentUser();
		List ls=sysUserService.loadMenu(su.getId());

		ModelAndView mav = new ModelAndView("menu");
		mav.addObject("items", ls);
		String webRoot=request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getServletPath()));
		mav.addObject("webRoot", webRoot);
		return mav;
		
	}
	
	@RequestMapping(value="dologin",method=RequestMethod.POST)
	public ModelAndView dologin(HttpSession httpSession,SysUser userVo) {
		String verifyCode=userVo.getVerifyCode();
		if(verifyCode==null || "".equals(verifyCode)) return ajaxDoneError("请输入验证码！");
		if(!verifyCode.equals(httpSession.getAttribute("verifyCode"))) return ajaxDoneError("验证码输入有误，请重新输入！");
		String userName=userVo.getUserName();
		String msg="";
		Integer ret=-2;
        UsernamePasswordToken token = new UsernamePasswordToken(userName,userVo.getPassword());
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser && !currentUser.isAuthenticated()) {
            try{
            
        	System.out.println("对用户[" + userName + "]进行登录验证..验证开始"); 
            currentUser.login(token);
            System.out.println("对用户[" + userName + "]进行登录验证..验证通过");
            ret=0;
            }catch(UnknownAccountException uae){  
                System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");  
               ret=-3;// "未知账户" 
               msg="未知账户";
            }catch(IncorrectCredentialsException ice){  
                System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");  
                ret=-1;// "密码不正确"  
                msg="错误的凭证";
            }catch(LockedAccountException lae){  
                System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");  
                ret=-4;// "账户已锁定"  
                msg="账户已锁定";
            }catch(ExcessiveAttemptsException eae){  
                System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");  
                ret=-5;// "用户名或密码错误次数过多"  
                msg="错误次数过多";
            }

          //验证是否登录成功  
            if(currentUser.isAuthenticated()){  
                System.out.println("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
            }else{  
                token.clear();  
            }
        }
        if(ret==0)
        	return ajaxDoneSuccess(msg);
        else 
        	return ajaxDoneError(msg);
	}
	
  
    @RequestMapping("logout")
    public ModelAndView dologout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {   
            subject.logout();
        }
        return ajaxDoneSuccess(this.getMessage("msg.logout.success"));
    }
    
    /** 
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中) 
     */  
    @RequestMapping("getVerifyCode")  
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        //设置页面不缓存  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);  
        //将验证码放到HttpSession里面  
        request.getSession().setAttribute("verifyCode", verifyCode);  
        System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");  
        //设置输出的内容的类型为JPEG图像  
        response.setContentType("image/jpeg");  
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);  
        //写给浏览器  
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());  
    }  

}