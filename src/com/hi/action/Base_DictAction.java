package com.hi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.hi.bean.Base_Dict;
import com.hi.service.Base_DictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Base_DictAction extends ActionSupport implements ModelDriven<Base_Dict> {

	private static final long serialVersionUID = 1L;
	private Base_Dict base_Dict = new Base_Dict();
	private Base_DictService base_DictService;

	@Override
	public Base_Dict getModel() {
		return base_Dict;
	}
	public void setBase_DictService(Base_DictService base_DictService) {
		this.base_DictService = base_DictService;
	}
	
	public String findByCode(){
		System.out.println(base_Dict);
		//到业务层去查模型驱动得到的字符串
		List<Base_Dict> list = base_DictService.findByCode(base_Dict);
		//转换成json
		String string = JSON.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//设置response的字符集，输出给浏览器
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");;
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.print(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
