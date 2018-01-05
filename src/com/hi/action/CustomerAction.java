package com.hi.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.hi.bean.Customer;
import com.hi.service.CustomerService;
import com.hi.service.CustomerServiceImpl;
import com.hi.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 客户管理
 * @author 王才
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer customer = new Customer();
	
	private CustomerService customerService;
	@Override
	public Customer getModel() {
		return customer;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * 使用离线条件查询？
	 * 分页查询所用变量 pageSize每页数据条数，pageCode第几页
	 */
	private Integer pageSize = 2;
	private Integer pageCode = 1;

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageCode(Integer pageCode) {
		if (pageCode < 0 ) pageCode = 0;
		//下限在这设置，上限在dao设置，因为pageCode和pageSize可能同时改变，总页数也就变了
		this.pageCode = pageCode;
		//this.pageCode = (pageCode == null)?  (Integer)ServletActionContext.getRequest().getSession().getAttribute("pageCode") : pageCode;
		//ServletActionContext.getRequest().getSession().setAttribute("pageCode", this.pageCode);
	}

	/**
	 * 按页查询
	 * @return
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		System.out.println(customerService);
		System.out.println(criteria+" " + pageCode + " "+ pageSize);
		
		PageBean<Customer> page = customerService.findByPage(criteria,this.pageCode,this.pageSize);

		//获取值栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page",	page);//！！！用的set而不是setParameter
		
		return "customerList";
	}
	
	
}
