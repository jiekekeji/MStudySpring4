Spring--依赖注入的配置方式：
===

一、属性注入：即通过 setter 方法注入Bean 的属性值
----------

1、基本类型注入：

```
<!--即通过 setter 方法注入Bean 的属性值 -->
<bean id="user" class="com.jk.User">
    <!--对应 User的setName方法 -->
    <property name="name" value="小丽"></property>
    <!--对应 User的setAge方法 -->
    <property name="age" value="18"></property>
</bean>
```

2、引用其他的Bean，如User有一辆测Car,User,java;

```
package com.jk;
public class User {
	private long id;
	private String name;
	private int age;

	// 引用了其他的bean
	private Car car;

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
```

在bean.xml中的配置：

```
<!--配置好car -->
<bean id="car" class="com.jk.Car">
    <property name="name" value="奔驰GLA200" />
</bean>

<!--即通过 setter 方法注入Bean 的属性值 -->
<bean id="user" class="com.jk.User">
    <!--对应 User的setName方法 -->
    <property name="name" value="小丽"></property>
    <!--对应 User的setAge方法 -->
    <property name="age" value="18"></property>
    <!--引用其他的bean,先声明好，然后通过id引用 -->
    <property name="car" ref="car"></property>
</bean>
```

3、共享bean,car是属于两个人所有的，bean.xml配置方式：

```
<!--配置好car -->
<bean id="car" class="com.jk.Car">
    <property name="name" value="奔驰GLA200" />
</bean>

<!--即通过 setter 方法注入Bean 的属性值 -->
<bean id="user-1" class="com.jk.User">
    <!--对应 User的setName方法 -->
    <property name="name" value="小丽"></property>
    <!--对应 User的setAge方法 -->
    <property name="age" value="18"></property>
    <!--引用其他的bean,先声明好，然后通过id引用 -->
    <property name="car" ref="car"></property>
</bean>

<!--即通过 setter 方法注入Bean 的属性值 -->
<bean id="user-2" class="com.jk.User">
    <!--对应 User的setName方法 -->
    <property name="name" value="小白"></property>
    <!--对应 User的setAge方法 -->
    <property name="age" value="22"></property>
    <!--引用其他的bean,先声明好，然后通过id引用 -->
    <property name="car" ref="car"></property>
</bean>
```

4、内部bean，独有的：如user-3,他自己独有一辆兰博基尼，bean.xml配置方式：
```
<bean id="user-3" class="com.jk.User">
    <!--对应 User的setName方法 -->
    <property name="name" value="小丽"></property>
    <!--对应 User的setAge方法 -->
    <property name="age" value="18"></property>
    <!--独有的 内部的 -->
    <property name="car">
        <bean class="com.jk.Car">
            <property name="name" value="兰博基尼" />
        </bean>
    </property>
</bean>
```

二、构造器注入：通过构造函数的参数方式注入值
----------
给User添加一个构造函数：

```
public User(long id, String name, int age, Car car) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
    this.car = car;
    System.out.println("构造函数的参数：" + "id=" + id + ":name=" + name + ":age=" + age + ":car=" + car);
}
```

1、按构造函数的索引匹配注入：

```
<!--配置好car -->
<bean id="car" class="com.jk.Car">
    <property name="name" value="奔驰GLA200" />
</bean>

<bean id="user-4" class="com.jk.User">
    <!--和构造函数的id对应 -->
    <constructor-arg value="65516156" index="0"></constructor-arg>
    <!--和构造函数的name对应 -->
    <constructor-arg value="露丝" index="1"></constructor-arg>
    <!--和构造函数的age对应 -->
    <constructor-arg value="19" index="2"></constructor-arg>
    <!--和构造函数的car对应,这里的car在之前已定义 -->
    <constructor-arg ref="car" index="3"></constructor-arg>
</bean>
```
2、按构造函数参数的名称属性匹配注入：

```
<bean id="user-5" class="com.jk.User">
    <!--和构造函数的id对应 -->
    <constructor-arg name="id" value="65516156"></constructor-arg>
    <!--和构造函数的name对应 -->
    <constructor-arg name="name" value="杰克"></constructor-arg>
    <!--和构造函数的age对应 -->
    <constructor-arg name="age" value="25"></constructor-arg>
    <!--和构造函数的car对应,这里的car在之前已定义 -->
    <constructor-arg name="car" ref="car"></constructor-arg>
</bean>
```


