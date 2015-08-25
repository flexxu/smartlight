package org.smartlight.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.smartlight.usermanager.model.SysUser;
import org.smartlight.usermanager.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;  
  
    /** 
     * 授权信息 
     */  
    protected AuthorizationInfo doGetAuthorizationInfo(  
                PrincipalCollection principals) {  
        String username=(String)principals.fromRealm(getName()).iterator().next();  
        if( username != null ){  
            SysUser user = sysUserService.getUserByName( username );  
            if( user != null /* && user.getRoles() != null */){  
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
          //      for( SecurityRole each: user.getRoles() ){  
          //              info.addRole(each.getName());  
          //              info.addStringPermissions(each.getPermissionsAsString());  
          //      }  
                return info;  
            }  
        }  
        return null;  
    }  
  
    /** 
     * 认证信息 ,调用subject。login方法时触发
     */  
    protected AuthenticationInfo doGetAuthenticationInfo(  
                AuthenticationToken authcToken ) throws AuthenticationException {  
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        String userName = token.getUsername();  
        if( userName != null && !"".equals(userName) ){  
            SysUser user = sysUserService.getUserByName(token.getUsername());  
  
            if( user != null)  { 
            	if(user.getPassword().equals(new String(token.getPassword()))){
            		this.setSession("CURRENT_USER", user);
	                return new SimpleAuthenticationInfo(  
	                            user.getUserName(),user.getPassword(), getName());  
            	}
            }
        }  
        return null;  
    }  
    
    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
         //   System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }  

}
