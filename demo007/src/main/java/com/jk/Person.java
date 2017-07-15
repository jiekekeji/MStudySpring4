package com.jk;

public class Person {

	private int weight;
	private String color;
	
	public Person() {
		System.out.println("person构造函数");
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Person [weight=" + weight + ", color=" + color + "]";
	}
}
