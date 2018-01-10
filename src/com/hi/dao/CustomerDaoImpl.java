package com.hi.dao;

import java.util.List;

import com.hi.bean.Customer;
import com.hi.util.BaseDaoImpl;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	public List<Object[]> findByLevel() {
		/*
		 * SELECT dict_item_name,COUNT(*) FROM base_dict b INNER JOIN 
		 * cst_customer c WHERE b.`dict_id` = c.`cust_level` GROUP BY b.dict_id;
		 * "select b.dict_item_name,count(*)"
				+ " from Base_Dict b"
				+ " inner join Customer c"
				//+ " where b.dict_id = c.level"
				+ " group by c.level"
		 */
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().find(
				"select c.level.dict_item_name,count(*)"
				+ " from Customer c"
				+ " inner join c.level"
				+ " group by c.level");
		
		
		return list;
	}
	
	@Override
	public List<Object[]> findBySource() {
		/*
		 * SELECT dict_item_name,COUNT(*) FROM base_dict b INNER JOIN 
		 * cst_customer c WHERE b.`dict_id` = c.`cust_level` GROUP BY b.dict_id;
		 * "select b.dict_item_name,count(*)"
				+ " from Base_Dict b"
				+ " inner join Customer c"
				//+ " where b.dict_id = c.level"
				+ " group by c.level"
		 */
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().find(
				"select c.source.dict_item_name,count(*)"
				+ " from Customer c"
				+ " inner join c.source"
				+ " group by c.source");
		
		
		return list;
	}
}
