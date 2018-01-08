package com.hi.dao;

import java.util.List;

import com.hi.bean.Base_Dict;
import com.hi.util.BaseDaoImpl;

public class Base_DictDaoImpl extends BaseDaoImpl<Base_Dict> implements Base_DictDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Base_Dict> findByCode(Base_Dict base_Dict) {
		return (List<Base_Dict>) this.getHibernateTemplate().find("from Base_Dict where dict_type_code = ? ", base_Dict.getDict_type_code());
	}
	
}
