package com.hi.dao;

import com.hi.bean.Sys_User;

public interface Sys_UserDao {

	Sys_User select(Sys_User sys_user);

	Sys_User selectByName(String user_code);
	
}
