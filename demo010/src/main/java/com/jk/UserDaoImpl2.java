package com.jk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl2 implements UserDao{

	@Autowired 
	private User user;
	
	public User getUser() {
		System.out.println("UserDaoImpl2...");
		return user;
	}
}
