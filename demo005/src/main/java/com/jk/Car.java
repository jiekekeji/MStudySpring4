package com.jk;

public class Car {

	public static final String childName = "小车";

	public static String getChildName() {
		return childName;
	}

	private String name;
	private long price;
	private Brand brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", price=" + price + ", brand=" + brand + "]";
	}
}
