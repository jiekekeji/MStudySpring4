package com.jk;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

	//引用BaseDao
	@Autowired
	protected BaseDao<T> baseDao;
	
	public void add(T entity){
		baseDao.save(entity);
	}
	
}
