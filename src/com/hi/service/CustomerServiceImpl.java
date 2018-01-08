package com.hi.service;

import java.util.List;

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

	@Override
	public boolean update(Customer customer) {
		return customerDao.update(customer);
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public boolean save(Customer customer) {
		return customerDao.save(customer);
	}

	@Override
	public boolean delete(Customer customer) {
		return customerDao.delete(customer);
	}

	@Override
	public List<Customer> selectAll() {
		return customerDao.selectAll();
	}
	
}
