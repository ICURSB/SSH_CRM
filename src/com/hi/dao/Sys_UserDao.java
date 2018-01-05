package com.hi.dao;

import com.hi.bean.Sys_User;

public interface Sys_UserDao {
	/**
	 * 用用户名和密码查找单个用户
	 * @param sys_user
	 * @return
	 */
	Sys_User select(Sys_User sys_user);
	/**
	 * 根据用户名查找用户
	 * @param user_code
	 * @return
	 */
	Sys_User selectByName(String user_code);
	/**
	 * insert用户，用于注册
	 * @param sys_user
	 * @return -1 注册失败
	 * 		<p>1 注册成功
	 */
	int insert(Sys_User sys_user);
	/**
	 * 更新单个用户
	 * @param sys_user
	 * @return 成功返回true，失败返回false
	 */
	boolean update(Sys_User sys_user);
	
}
