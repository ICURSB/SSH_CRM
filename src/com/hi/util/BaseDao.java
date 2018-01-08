package com.hi.util;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	
	public boolean save(T t);
	
	public boolean delete(T t);
	
	public boolean update(T t);
	
	public T findById(Long id);
	
	public List<T> selectAll();
	
	public T selectOne(T t);
	
	
	public PageBean<T> findByPage(DetachedCriteria criteria,Integer pageCode,Integer pageSize);
	
	
}
