package com.hi.service;


import java.util.List;

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

	boolean update(Customer customer);

	Customer findById(Long cust_id);

	boolean save(Customer customer);

	boolean delete(Customer customer);

	List<Customer> selectAll();
	
}
