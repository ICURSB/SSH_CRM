package com.hi.service;

import java.util.List;

import com.hi.bean.Base_Dict;

public interface Base_DictService {

	List<Base_Dict> findByCode(Base_Dict base_Dict);

}
