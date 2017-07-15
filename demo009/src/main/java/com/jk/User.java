package com.jk;

import org.springframework.stereotype.Controller;

@Controller
public class User {

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
