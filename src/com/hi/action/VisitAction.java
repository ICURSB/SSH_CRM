package com.hi.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hi.bean.Visit;
import com.hi.service.VisitService;
import com.hi.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class VisitAction extends ActionSupport implements ModelDriven<Visit> {
	private Visit visit = new Visit();
	private VisitService visitService;

	@Override
	public Visit getModel() {
		return visit;
	}
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	/**
	 * 跳转至添加页面
	 * @return
	 */
	public String initAddUI(){
		return "initAddUI";
	}
	
	
	private int pageCode = 1;
	private int pageSize = 2;
	
	public void setPageCode(int pageCode) {
		System.out.println("set pageCode" + pageCode);
		this.pageCode = pageCode;
	}
	public void setPageSize(int pageSize) {
		System.out.println("set pageSize" + pageSize);
		this.pageSize = pageSize;
	}
	
	private String beginDate;
	private String endDate;
	
	public void setBeginDate(String beginDate) {
		System.out.println("set beginDate" + beginDate);
		this.beginDate = beginDate;
	}
	public void setEndDate(String endDate) {
		System.out.println("set endDate" + endDate);
		this.endDate = endDate;
	}
	/**
	 * 按页查找
	 * @return
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		System.out.println(pageCode + "\t" + pageSize + beginDate+endDate + visit.getVisit_interviewee());
		String interviewee = visit.getVisit_interviewee();
		if(interviewee != null && !interviewee.equals("")){
			criteria.add(Restrictions.like("visit_interviewee", "%"+ visit.getVisit_interviewee() +"%"));
			valueStack.set("interviewee", interviewee);
		}
		if(beginDate !=null && !beginDate.equals("")){
			criteria.add(Restrictions.ge("visit_time", beginDate));
			valueStack.set("beginDate", beginDate);
		}
		if(endDate != null && !endDate.equals("")) {
			criteria.add(Restrictions.le("visit_time", endDate));
			valueStack.set("endDate", endDate);
		}
		
		//按访问时间降序排序，以最接近当前时间的时间为第一项，方便查找
		criteria.addOrder(Order.desc("visit_time"));
		
		PageBean<Visit> pageBean = visitService.findByPage(criteria,pageCode,pageSize);
		valueStack.set("page", pageBean);
		
		return "visitList";
	}
	/**
	 * 保存拜访记录
	 * @return
	 */
	public String save(){
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		System.out.println(visit);
		if (visitService.save(visit)) {
			valueStack.set("msg", "保存成功！");
			return "saveOK";
		}else{
			valueStack.set("msg", "保存失败！");
			return "saveFailure";
		}
		
	}
}
