package com.hi.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hi.bean.Sys_User;
import com.hi.util.BaseDaoImpl;

@SuppressWarnings("unchecked")
public class Sys_UserDaoImpl extends BaseDaoImpl<Sys_User> implements Sys_UserDao{

	@Override
	public Sys_User select(Sys_User sys_user) {
		List<Sys_User> list = (List<Sys_User>) this.getHibernateTemplate().find("from Sys_User where user_code = ? and user_password = ? and user_state = ?",
					sys_user.getUser_code(),sys_user.getUser_password(),"1");
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public Sys_User selectByName(String user_code) {
		List<Sys_User> list = (List<Sys_User>) this.getHibernateTemplate().find("from Sys_User where user_code = ? ",
				user_code);
		if(list.size() > 0){
			return list.get(0);
		}
	
		return null;
	}

	@Override
	public int insert(Sys_User sys_user) {
		Serializable id = this.getHibernateTemplate().save(sys_user);
		try {
			if((long)id > 0 ){
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public boolean update(Sys_User sys_user) {
		try {
			this.getHibernateTemplate().update(sys_user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
