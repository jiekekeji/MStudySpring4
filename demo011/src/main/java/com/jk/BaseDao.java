package com.jk;

public class BaseDao <T>{

	public void save(T entity){
		System.out.println("save.."+entity);
	}
}
