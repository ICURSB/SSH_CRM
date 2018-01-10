package com.hi.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hi.bean.LinkMan;
import com.hi.service.LinkManService;
import com.hi.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan = new LinkMan();
	private LinkManService linkManService;
	
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	@Override
	public LinkMan getModel() {
		return linkMan;
	};
	
	public String initAddUI(){
		
		return "initAddUI";
	}
	public String initUpdate(){
		//修改之前保存当前查询的页码，改完还原回去，通过get请求的url带入pageCode
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("pageCode",pageCode);
		session.setAttribute("pageSize",pageSize);
		
		System.out.println(pageCode + "\t" + pageSize);
		
		linkMan = linkManService.findById(linkMan.getLkm_id());
		return "initUpdate";
	}
	
	
	private int pageCode = 1;
	private int pageSize = 2;
	
	
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 按页查询   pageCode,pageSize
	 * @return
	 */
	public String findByPage(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		System.out.println(linkMan);
		if(linkMan.getLkm_name() != null && !linkMan.getLkm_name().equals("")){
			criteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		System.out.println(linkMan.getCustomer());
		Long id = null;
		if(linkMan.getCustomer() != null && (id = linkMan.getCustomer().getCust_id()) != null){
			criteria.add(Restrictions.eq("customer.cust_id", id));
		}
		
		PageBean<LinkMan> pageBean = linkManService.findByPage(criteria,pageCode,pageSize);
		
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("page", pageBean);
		
		return "linkmanList";
	}
	/**
	 * 保存新联系人
	 * @return
	 */
	public String save(){
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		if(linkManService.save(linkMan)){
			valueStack.set("msg", "保存成功");//保存成功返回继续添加界面提示保存成功
			return "saveOK";
		}else{
			return "saveFailure";
		}
	}
	
	/**
	 * 修改联系人
	 * @return
	 */
	public String update(){
		//修改之前保存当前查询的页码，改完还原回去，通过get请求的url带入pageCode
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			pageCode = (int) session.getAttribute("pageCode");
			pageSize = (int) session.getAttribute("pageSize");
			
			System.out.println(pageCode + "\t" + pageSize);
			
			session.removeAttribute("pageCode");
			session.removeAttribute("pageSize");
			
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("pageCode", pageCode);
			valueStack.set("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (linkManService.update(linkMan)) {
			return "updateOK";
		} else {
			return "updateFailure";
		}
		
	}
	
	/**
	 * 根据ID删除联系人
	 * @return
	 */
	public String delete(){
		if(linkMan.getLkm_id() > 0){
			linkManService.delete(linkMan);
		}
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		valueStack.set("pageCode", pageCode);
		valueStack.set("pageSize", pageSize);
		return "deleteOK";
	}
}

