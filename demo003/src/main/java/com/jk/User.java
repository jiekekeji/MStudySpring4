package com.jk;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {

	private long id;
	private String name;
	private List<Car> cars;
	private Set<Book> books;
	private Map<String, Book> map;
	private Map<String, String> phoneMap;

	public User() {
		super();
	}

	public User(long id, String name, List<Car> cars, Set<Book> books, Map<String, Book> map,
			Map<String, String> phoneMap) {
		super();
		this.id = id;
		this.name = name;
		this.cars = cars;
		this.books = books;
		this.map = map;
		this.phoneMap = phoneMap;
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

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Map<String, Book> getMap() {
		return map;
	}

	public void setMap(Map<String, Book> map) {
		this.map = map;
	}

	public Map<String, String> getPhoneMap() {
		return phoneMap;
	}

	public void setPhoneMap(Map<String, String> phoneMap) {
		this.phoneMap = phoneMap;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", cars=" + cars + ", books=" + books + ", map=" + map
				+ ", phoneMap=" + phoneMap + "]";
	}

}
