package com.jk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	//属性名称需和 @Repository("userDaoImpl1") 一致
	@Autowired
	public UserDao userDaoImpl1;
	
	//属性名称需和 @Repository("userDaoImpl2") 一致
	@Autowired
	public UserDao userDaoImpl2;
	
	public User getUser(){
		System.out.println(userDaoImpl2.getUser());
		return userDaoImpl1.getUser();
	}
}
