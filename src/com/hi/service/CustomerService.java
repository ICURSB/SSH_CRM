package com.hi.service;


import org.hibernate.criterion.DetachedCriteria;

import com.hi.bean.Customer;
import com.hi.util.PageBean;

public interface CustomerService {
	/**
	 * 分页查找
	 * @param criteria
	 * @param pageCode
	 * @param pageSize
	 * @return List<Customer>
	 */
	PageBean<Customer> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize);
	
}
