Spring--装配集合：
===

一、先定义几个Bean实体：
----------

1、Book.java：

```
package com.jk;

public class Book {

	private String name;
	private long price;

	public Book() {
		super();
	}

	public Book(String name, long price) {
		super();
		this.name = name;
		this.price = price;
	}

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

	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + "]";
	}
}
```

2、Car.java;

```
package com.jk;

public class Car {

	private String name;
	private long price;

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

	@Override
	public String toString() {
		return "Car [name=" + name + ", price=" + price + "]";
	}

}
```

3、User.java;
```
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

```

4、在bean.xml中的配置：
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">


    <!--即通过 setter 方法注入Bean 的属性值 -->
    <bean id="honda" class="com.jk.Car">
        <property name="name" value="思域"/>
        <property name="price" value="120520"/>
    </bean>

    <bean id="ford" class="com.jk.Car">
        <property name="name" value="蒙迪欧"/>
        <property name="price" value="180520"/>
    </bean>

    <!--book实体-->
    <bean id="java" class="com.jk.Book">
        <property name="name" value="从入门到丢弃"/>
        <property name="price" value="125"/>
    </bean>

    <bean id="vuejs" class="com.jk.Book">
        <property name="name" value="由深入到入门"/>
        <property name="price" value="140"/>
    </bean>

    <!--user_1 这个人有两辆车 -->
    <bean id="user_1" class="com.jk.User">
        <property name="id" value="25623541"/>
        <property name="name" value="Jack"/>

        <!--装配list的方式 -->
        <property name="cars">
            <list>
                <ref bean="honda"/>
                <ref bean="ford"/>
            </list>
        </property>

        <!--装配set的方式和list一样 -->
        <property name="books">
            <list>
                <ref bean="java"/>
                <ref bean="vuejs"/>
            </list>
        </property>

        <!--装配map的方式 -->
        <property name="map">
            <map>
                <entry key="key1" value-ref="java"/>
                <entry key="key2" value-ref="vuejs"/>
            </map>
        </property>

        <!--map的key和vaule都是String类型的时候可以这么配置 -->
        <property name="phoneMap">
            <props>
                <prop key="phone_1">18898652541</prop>
                <prop key="phone_2">16525362542</prop>
            </props>
        </property>
    </bean>
</beans>
```

