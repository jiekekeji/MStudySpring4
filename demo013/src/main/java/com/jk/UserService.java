package com.jk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void addUser(User user) {
		System.out.println("addUser");
	}

	public int deleteUser() {

		System.out.println("deleteUser");

		return 1;
	}

	public List<User> queryUser() {

		List<User> users = new ArrayList<User>();

		System.out.println("queryUser");

		return users;
	}

	public User updateUser(int id, User user) {

		System.out.println("updateUser");

		return user;
	}
}
