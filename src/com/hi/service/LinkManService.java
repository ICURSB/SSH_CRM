package com.hi.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hi.bean.LinkMan;
import com.hi.util.PageBean;

public interface LinkManService {

	LinkMan findById(Long lkm_id);

	PageBean<LinkMan> findByPage(DetachedCriteria criteria, int pageCode, int pageSize);

	boolean save(LinkMan linkMan);

	boolean update(LinkMan linkMan);
	
}
