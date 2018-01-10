package com.hi.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hi.bean.Visit;
import com.hi.dao.VisitDao;
import com.hi.util.PageBean;
@Transactional
public class VisitServiceImpl implements VisitService{
	VisitDao visitDao;
	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	@Override
	public PageBean<Visit> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize) {
		return visitDao.findByPage(criteria, pageCode, pageSize);
	}

	@Override
	public boolean save(Visit visit) {
		return visitDao.save(visit);
	}

}
