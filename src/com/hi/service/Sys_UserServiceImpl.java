package com.hi.service;

import org.springframework.transaction.annotation.Transactional;

import com.hi.bean.Sys_User;
import com.hi.dao.Sys_UserDao;

@Transactional
public class Sys_UserServiceImpl implements Sys_UserService{
	//注入userdao
	private Sys_UserDao sys_UserDao;
	public void setSys_UserDao(Sys_UserDao sys_UserDao) {
		this.sys_UserDao = sys_UserDao;
	}


	@Override
	public Sys_User select(Sys_User sys_user) {
		return sys_UserDao.select(sys_user);
	}


	@Override
	public Sys_User selectByName(String user_code) {
		// TODO Auto-generated method stub
		return sys_UserDao.selectByName(user_code);
	}

}
