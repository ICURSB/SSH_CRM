package com.hi.util;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	
	public void save(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public T findById(Long id);
	
	public T selectOne(T t);
	
	public List<T> selectAll(DetachedCriteria criteria, Integer pageCode, Integer pageSize);
	
	public PageBean<T> findByPage(DetachedCriteria criteria,Integer pageCode,Integer pageSize);
}
