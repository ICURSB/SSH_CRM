package com.hi.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hi.bean.Sys_User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 自定义拦截器，当session中不存在已登录用户时，调用任意方法都返回到登录界面进行登录
 * @author 王才
 *
 */
public class UserInterceptor extends MethodFilterInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Sys_User existUser = (Sys_User) session.getAttribute("existUser");
		
		if(existUser == null){
			return "login";
		}
		
		return arg0.invoke();
	}

}
