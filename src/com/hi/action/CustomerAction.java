package com.hi.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.hi.bean.Customer;
import com.hi.service.CustomerService;
import com.hi.util.PageBean;
import com.hi.util.Utils;
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
	public static final String FILEPATH = "F:/FileTest/";
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
	 * ajax查询所有客户的名称和id
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Customer> list = customerService.selectAll();
		String string = Utils.toJsonString(list);
		Utils.write(ServletActionContext.getResponse(), string);
		return null;
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
	/**
	 * 转到添加客户界面
	 * @return
	 */
	public String initAddUI(){
		System.out.println(customer);
		return "initAddUI";
	}
	
	
	/**
	 * 修改客户页面，查询出来显示
	 * @return
	 */
	public String initUpdate(){
		Long cust_id = customer.getCust_id();
		customer = customerService.findById(cust_id);
		System.out.println(customer);
		return "initUpdate";
	}
	/**
	 * 修改&添加客户
	 * @return
	 */
	public File upload;
	public String uploadFileName;
	public String uploadContentType;
	
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * 添加新客户  或者  保存客户修改
	 * @return
	 */
	public String update(){
		System.out.println(customer);
		System.out.println(upload);
		ValueStack vs = ActionContext.getContext().getValueStack();
		
		if(upload != null){
			File file = null;
			if(customer.getFilePath()!=null){
				file = new File(customer.getFilePath());
				if(file.exists()){
					file.delete();
				}
			}
			
			String name = Utils.getUUIDName(uploadFileName);
			try {
				FileUtils.copyFile(upload, new File(FILEPATH  + name));
			} catch (IOException e) {
				e.printStackTrace();
				vs.set("msg", "文件上传失败！");
				return "initAddUI";
			}
			
			customer.setFilePath(FILEPATH + name);

		}
		if (customer.getCust_id() == null) {
			if(!customerService.save(customer)){
				vs.set("msg", "操作失败！");
				return "initAddUI";
			}
		}else{
			if(!customerService.update(customer)){
			vs.set("msg", "操作失败！");
			return "initAddUI";
		}
		}
		
		vs.set("msg", "操作成功！");
		return "initAddUI";
	}
	/**
	 * 删除客户资料，删除之前先删除文件
	 * @return
	 */
	public String delete(){
		Long cust_id = customer.getCust_id();
		//查
		customer = customerService.findById(cust_id);
		
		String filePath = customer.getFilePath();
		if(!filePath.trim().equals("")){
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}
		//删
		customerService.delete(customer);
		
		return "deleteOK";
		
	}
}
