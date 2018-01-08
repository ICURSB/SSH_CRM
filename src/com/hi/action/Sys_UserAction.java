package com.hi.action;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hi.bean.Sys_User;
import com.hi.service.Sys_UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 登录用户管理
 * @author 王才
 *
 */
public class Sys_UserAction extends ActionSupport implements ModelDriven<Sys_User>{
	/**
	 * 初始版本号
	 */
	private static final long serialVersionUID = 1L;
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
	
	/**
	 * 登录处理
	 * @return
	 */
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
				
				session.setAttribute("existUser", sys_User);
				System.out.println("验证正确，用户名" + sys_User.getUser_name() + "，密码:" + sys_User.getUser_password());
				return "index";
			}
			session.setAttribute("userMsg", "用户名或密码错误！");
			return "login";
		}
		session.setAttribute("codeMsg", "验证码错误！");
		return "login";
	}
	
	/**
	 * 注册处理
	 * @return
	 */
	public String register(){
		if(viCode == null) return "register";//刷新页面时viCode为null，直接重新加载jsp文件
		//获取request 的 session  和验证码字符串 
		HttpSession session = ServletActionContext.getRequest().getSession();
		String vcode = (String) session.getAttribute("validateCode");
		
		
		System.out.println(viCode + "\t" +vcode);
		if(viCode.equalsIgnoreCase(vcode)){
			//转发业务层处理注册用户
			int seccess = sys_UserService.insert(this.sys_user);
			if(seccess == 1){
				session.setAttribute("successMsg", "<a href='"+ session.getServletContext().getContextPath() +
						"/user_login' style='text-decoration:none; color:blue; font-size:16px; '"
						+ ">立即登录<a>");
				return "regSuccess";
			}
			if (seccess==0) {
				session.setAttribute("userMsg", "注册失败，用户名密码不符合规则！");
				return "register";
			}
			session.setAttribute("userMsg", "注册失败！");
			return "register";
		}
		session.setAttribute("codeMsg", "验证码错误！");
		return "register";
	}
	
	
	/**
	 * ajax 检查用户code是否重复 ，重复返回0，否则返回1
	 * @return
	 */
	public String checkCode(){
		Sys_User sys_User = sys_UserService.selectByName(this.sys_user.getUser_code());
		ServletOutputStream stream = null;
		try {
			stream = ServletActionContext.getResponse().getOutputStream();
		
			if(sys_User != null){
				System.out.println("验证正确，用户名: " + sys_User.getUser_code() + "，密码:" + sys_User.getUser_password());
				stream.write("0".getBytes());
			}else{
				stream.write("1".getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		return null;
	}
	
	
	/**
	 * 修改用户密码的jsp重定向请求
	 */
	public String updatePassword(){
		return "updatePassword";
	}
	/**
	 * ajax 验证当前用户密码
	 */

	public String checkPassword(){
		//获取request 的 session  和验证码字符串 
		HttpSession session = ServletActionContext.getRequest().getSession();
		Sys_User existUser = (Sys_User) session.getAttribute("existUser");
		ServletOutputStream stream = null;
		try {
			stream = ServletActionContext.getResponse().getOutputStream();
		
			if(sys_UserService.checkPassword(sys_user,existUser)){
				
				System.out.println("验证密码正确，用户名:" + sys_user.getUser_code() + "，密码:" + sys_user.getUser_password());
				stream.write("1".getBytes());
			}else{
				stream.write("0".getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		return null;
	}
	
	
	
	
	private String u_password;
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	/**
	 * 实际修改用户密码的action
	 * @return
	 */
	public String password(){
		//获取request 的 session  和验证码字符串 
		HttpSession session = ServletActionContext.getRequest().getSession();
		Sys_User existUser = (Sys_User) session.getAttribute("existUser");
		
		
		if(sys_UserService.checkPassword(sys_user, existUser)){
			
			existUser.setUser_password(u_password);
			sys_UserService.update(existUser);
			
			session.setAttribute("successMsg", "<a href='"+ session.getServletContext().getContextPath() +
					"/user_index' style='text-decoration:none; color:blue; font-size:16px; '"
					+ ">回到主页面<a>");
			return "pswSuccess";
		}else{
			return "error";
		}
	}
	/**
	 * 用于跳转到主页面
	 * @return
	 */
	public String index(){
		//获取request 的 session
		HttpSession session = ServletActionContext.getRequest().getSession();
		Sys_User existUser = (Sys_User) session.getAttribute("existUser");
		if(existUser != null){
			return "index";
		}else{
			return "login";
		}
		
	}
	
	/**
	 * 退出登录
	 * @return 返回登录界面
	 */
	public String exit(){
		//获取request 的 session
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("existUser");
		return "login";
	}
	
	
}
