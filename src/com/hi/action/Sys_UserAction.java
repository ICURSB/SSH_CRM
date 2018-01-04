package com.hi.action;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hi.bean.Sys_User;
import com.hi.service.Sys_UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Sys_UserAction extends ActionSupport implements ModelDriven<Sys_User>{
	private Sys_User sys_user = new Sys_User();//!!!要new一个！！！

	@Override
	public Sys_User getModel() {
		return sys_user;
	}
	//注入service
	private Sys_UserService sys_UserService;
	public void setSys_UserService(Sys_UserService sys_UserService) {
		this.sys_UserService = sys_UserService;
	}
	//属性驱动viCode
	private String viCode;
	public void setViCode(String viCode) {
		this.viCode = viCode;
	}
	
	
	public String login(){
		if(viCode == null) return "login";//刷新页面时viCode为null，直接重新加载jsp文件
		
		//获取request 的 session  和验证码字符串 
		HttpSession session = ServletActionContext.getRequest().getSession();
		String vcode = (String) session.getAttribute("validateCode");
		
		
		System.out.println(viCode + "\t" +vcode);
		if(viCode.equalsIgnoreCase(vcode)){
			//查找该用户是否存在
			Sys_User sys_User = sys_UserService.select(this.sys_user);
			if(sys_User != null){
				System.out.println("验证正确，用户名" + sys_User.getUser_name() + "，密码:" + sys_User.getUser_password());
				return "index";
			}
			session.setAttribute("userMsg", "用户名或密码错误！");
			return "login";
		}
		session.setAttribute("codeMsg", "验证码错误！");
		return "login";
	}
	
	
	public String register(){
		Sys_User sys_User = sys_UserService.selectByName(this.sys_user.getUser_code());
		if (sys_User != null) {
			return "login";
		}
		return "register";
	}
	public String checkCode(){
		Sys_User sys_User = sys_UserService.selectByName(this.sys_user.getUser_code());
		ServletOutputStream stream = null;
		try {
			stream = ServletActionContext.getResponse().getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(sys_User != null){
			System.out.println("验证正确，用户名" + sys_User.getUser_name() + "，密码:" + sys_User.getUser_password());
			try {
				stream.write("0".getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				stream.write("1".getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
