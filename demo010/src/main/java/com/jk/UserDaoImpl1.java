package com.jk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDaoImpl1")
public class UserDaoImpl1 implements UserDao{

	@Autowired 
	private User user;
	
	public User getUser() {
		System.out.println("UserDaoImpl1...");
		return user;
	}

}
