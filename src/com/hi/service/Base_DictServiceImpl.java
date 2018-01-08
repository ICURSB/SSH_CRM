package com.hi.service;

import java.util.ArrayList;
import java.util.List;

import com.hi.bean.Base_Dict;
import com.hi.dao.Base_DictDao;

public class Base_DictServiceImpl implements Base_DictService{
	Base_DictDao base_DictDao;

	public void setBase_DictDao(Base_DictDao base_DictDao) {
		this.base_DictDao = base_DictDao;
	}

	@Override
	public List<Base_Dict> findByCode(Base_Dict base_Dict) {
		if (base_Dict.getDict_type_code() == null || base_Dict.getDict_type_code().equals("")) return new ArrayList<Base_Dict>();
		return base_DictDao.findByCode(base_Dict);
	}
	
	
}
