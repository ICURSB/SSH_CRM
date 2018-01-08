package com.hi.test;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hi.bean.Customer;
import com.hi.dao.CustomerDao;
import com.hi.util.PageBean;

public class Tests {
	
	@Test
	@Transactional
	public void run1(){
		/*ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
		   
		CustomerDao customerDao  = (CustomerDao) context.getBean("customerDao");  
		PageBean<Customer> page = customerDao.findByPage(DetachedCriteria.forClass(Customer.class) , 1, 5);
		
		System.out.println("select");*/
		
		Customer customer = new Customer();
		customer.setCust_mobile("16513165");
		
		String string = JSON.toJSONString(customer);
		System.out.println(string);
	}
}
