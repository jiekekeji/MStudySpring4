package com.jk;

import org.springframework.stereotype.Service;

@Service
public class Teacher {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
