package com.hi.dao;

import java.util.List;

import com.hi.bean.Base_Dict;

public interface Base_DictDao {

	List<Base_Dict> findByCode(Base_Dict base_Dict);

}
