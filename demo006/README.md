Spring--Bean之间的关系 继承和依赖：
===

一、继承：
----------

1、有一个类Person.java,

```
package com.jk;

public class Person {

	private int weight;
	private String color;
	
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

```
2、person2继承person1，他们又相同的颜色,parent="person1" 指定父类颜色继承过去：

```
	<!-- person1 -->
	<bean id="person1" class="com.jk.Person">
		<property name="weight" value="#{100}" />
		<property name="color" value="#{'黑色'}" />
	</bean>
	
	<!-- person2 -->
	<bean id="person2" class="com.jk.Person" parent="person1">
		<property name="weight" value="#{200}" />
	</bean>
```

3、输出结果

```
Person [weight=100, color=黑色]
Person [weight=200, color=黑色
```

4、通过abstract="true"指定bean为抽象bean，设置为抽象bean的bean不能被实例化，

```
	<!-- person1 -->
	<bean id="person1" class="com.jk.Person" abstract="true">
		<property name="weight" value="#{100}" />
		<property name="color" value="#{'黑色'}" />
	</bean>
	
	<!-- person2 -->
	<bean id="person2" class="com.jk.Person" parent="person1">
		<property name="weight" value="#{200}" />
	</bean>
```

person1，将无法被实例化：

```
//被配置为abstract="true"之后调用会报错
Person person1 = (Person) ctx.getBean("person1");
```


二、依赖：通过 depends-on  配置：

```
 	<bean id="car" class="com.jk.Car">
		<property name="name" value="#{'丰田'}" />
		<property name="price" value="#{2000}" />
	</bean>

	<bean id="user" class="com.jk.User" depends-on="car">
		<property name="name" value="#{'Jack'}" />
		<property name="address" value="#{'海南省'}" />
		<property name="car" value="#{car}" />
	</bean>
```