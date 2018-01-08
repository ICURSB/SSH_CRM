package com.hi.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hi.bean.LinkMan;
import com.hi.dao.LinkManDao;
import com.hi.util.PageBean;

@Transactional
public class LinkManServiceImpl implements LinkManService{
	LinkManDao linkManDao;
	
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria criteria, int pageCode, int pageSize) {
		return linkManDao.findByPage(criteria, pageCode, pageSize);
	}

	@Override
	public boolean save(LinkMan linkMan) {
		return linkManDao.save(linkMan);
	}

	@Override
	public boolean update(LinkMan linkMan) {
		return linkManDao.update(linkMan);
	}
	
}
