package com.hi.service;

import com.hi.bean.Sys_User;

public interface Sys_UserService {

	Sys_User select(Sys_User sys_user);

	Sys_User selectByName(String user_code);

}
