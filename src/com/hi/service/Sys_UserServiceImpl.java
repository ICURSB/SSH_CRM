package com.hi.service;

import org.springframework.transaction.annotation.Transactional;

import com.hi.bean.Sys_User;
import com.hi.dao.Sys_UserDao;
import com.hi.util.Utils;

@Transactional
public class Sys_UserServiceImpl implements Sys_UserService{
	//注入userdao
	private Sys_UserDao sys_UserDao;
	public void setSys_UserDao(Sys_UserDao sys_UserDao) {
		this.sys_UserDao = sys_UserDao;
	}


	@Override
	public Sys_User select(Sys_User sys_user) {
		sys_user.setUser_password(Utils.md5(sys_user.getUser_password()));
		return sys_UserDao.select(sys_user);
	}


	@Override
	public Sys_User selectByName(String user_code) {
		return sys_UserDao.selectByName(user_code);
	}

	
	@Override
	public int insert(Sys_User sys_user) {
		if(checkUserLegal(sys_user)){
			return 0;
		}
		System.out.println(sys_user);
		sys_user.setUser_password(Utils.md5(sys_user.getUser_password()));
		sys_user.setUser_state("1");
		return sys_UserDao.insert(sys_user);
	}

	/**
	 * 验证用户名密码，长度必须在6~18之间、
	 * 用户名必须以字母开头并不包含特殊符号，可以含下划线
	 * 验证昵称
	 */
	private boolean checkUserLegal(Sys_User sys_user) {
		int i = sys_user.getUser_password().length();
		if( sys_user.getUser_name().length()>0 && i>=6 && i<=18 &&
				sys_user.getUser_code().matches("^[a-zA-Z]w{5,17}$")){
			return true;
		}
		return false;
	}


	@Override
	public boolean update(Sys_User sys_user) {
		sys_user.setUser_password(Utils.md5(sys_user.getUser_password()));
		
		return sys_UserDao.update(sys_user);
	}


	@Override
	public boolean checkPassword(Sys_User inUser, Sys_User existUser) {
		if(existUser == null || inUser == null) return false;
		return existUser.getUser_code().equals(inUser.getUser_code()) &&
				Utils.md5(inUser.getUser_password()).equals(existUser.getUser_password());
	}

}
