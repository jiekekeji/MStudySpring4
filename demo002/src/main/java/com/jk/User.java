package com.jk;

public class User {

	private long id;
	private String name;
	private int age;

	// 引用了其他的bean
	private Car car;

	public User(long id, String name, int age, Car car) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.car = car;
		System.out.println("构造函数的参数：" + "id=" + id + ":name=" + name + ":age=" + age + ":car=" + car);
	}

	public User() {
		System.out.println("空参数构造函数");
	}

	public void say() {
		System.out.println("I am " + name);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", car=" + car + "]";
	}

}
