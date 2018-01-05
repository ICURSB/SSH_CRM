package com.hi.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hi.bean.Customer;
import com.hi.util.BaseDaoImpl;
import com.hi.util.PageBean;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

}
