package com.hi.service;

import java.util.List;

import com.hi.bean.Sys_User;

public interface Sys_UserService {
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
	 * @return 0 用户名不符合规则
			<p>-1 注册失败
	 * 		<p>1 注册成功
	 */
	int insert(Sys_User sys_user);
	/**
	 * 更新单个用户
	 * @param sys_user
	 * @return 成功返回true，失败返回false
	 */
	boolean update(Sys_User sys_user);
	/**
	 * 验证用户名和密码
	 * @param inUser
	 * @param existUser
	 * @return 验证OK返回true，否则返回false
	 */
	boolean checkPassword(Sys_User inUser, Sys_User existUser);
	List<Sys_User> findAll();

}
