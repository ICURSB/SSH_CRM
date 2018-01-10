package com.hi.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hi.bean.Visit;
import com.hi.util.PageBean;

public interface VisitService {

	PageBean<Visit> findByPage(DetachedCriteria criteria, Integer pageCode, Integer pageSize);

	boolean save(Visit visit);

}
