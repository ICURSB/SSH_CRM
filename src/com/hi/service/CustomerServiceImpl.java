package com.hi.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hi.bean.Customer;
import com.hi.dao.CustomerDao;
import com.hi.util.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService{
	private CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize) {
		return customerDao.findByPage(criteria,pageCode,pageSize);
	}
	
}
