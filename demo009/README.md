Spring--通过注解配置bean:
===

组件扫描(component scanning):  Spring 能够从 classpath 下自动扫描, 侦测和实例化具有特定注解的组件. 

特定组件包括:
@Component: 基本注解, 标识了一个受 Spring 管理的组件
@Respository: 标识持久层组件
@Service: 标识服务层(业务层)组件
@Controller: 标识表现层组件

对于扫描到的组件, Spring 有默认的命名策略: 使用非限定类名, 第一个字母小写. 也可以在注解中通过 value 属性值标识组件的名称.

一、配置过程
--------

1、给类加上注解：

Cat.java
```
package com.jk;

import org.springframework.stereotype.Component;

@Component
public class Cat {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

```

Student.java:

```
package com.jk;

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

```

Teacher.java:
```
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
```

User.java:
```
package com.jk;

import org.springframework.stereotype.Controller;

@Controller
public class User {

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
```

2、在beanx.xml配置文件中配置扫描器：

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
		
   <!--扫描器配置  扫描com.jk下的所有类  -->
   <context:component-scan base-package="com.jk"></context:component-scan>
</beans>
```

3、从IOC容器中获取bean实例：以id的方式,注解时，用类名第一个字母小写的方式获取,

```
package com.jk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		// 1. 创建 Spring 的 IOC 容器: 从类路径加载xml配置文件
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

		// 2.从 IOC 容器中获取 bean 的实例:以id的方式,注解时，用类名第一个字母小写的方式获取
		Cat cat = (Cat) ctx.getBean("cat");
		Student student = (Student) ctx.getBean("student");
		Teacher teacher = (Teacher) ctx.getBean("teacher");
		User user = (User) ctx.getBean("user");
		
		// 3.使用bean
		System.out.println(cat);
		System.out.println(student);
		System.out.println(teacher);
		System.out.println(user);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
```

二、扫描器配置详解：

1、基本配置
```
<context:component-scan base-package="com.jk"></context:component-scan>
```

2、扫描多个包,用逗号隔开
```
<context:component-scan base-package="com.jk,com.rs"></context:component-scan>
```

3、扫描某个包并排除该包下的某些类：
```
   <!--扫描器配置  扫描com.jk下的所有类  -->
   <context:component-scan base-package="com.jk,com.rs">
       <!--排除com.jk.User这个类  -->
	  <context:exclude-filter type="regex" expression="com.jk.User"/> 
	  <!--排除com.jk包下的所有类  -->
	  <context:exclude-filter type="regex" expression="com.jk.*"/> 
   </context:component-scan>
```

4、<context:include-filter> 子节点表示要包含的目标类：
```
   <!--扫描器配置  扫描com.jk下的所有类  -->
   <context:component-scan base-package="com.jk,com.rs">
       <!--包含com.jk.User这个类  -->
	  <context:include-filter type="regex" expression="com.jk.User"/> 
	  <!--包含com.jk包下的所有类  -->
	  <context:include-filter type="regex" expression="com.jk.*"/> 
   </context:component-scan>
```

5、<context:include-filter/> 和 <context:exclude-filter/> 子节点的type和expression种类：

![image](https://github.com/jiekekeji/MStudySpring4/blob/master/demo009/prevew/demo009-1.gif)
