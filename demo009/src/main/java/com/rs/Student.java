package com.rs;

import org.springframework.stereotype.Repository;

@Repository
public class Student {

	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
