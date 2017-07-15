Spring--使用 @Autowired 自动装配 Bean:
===

1、比如需要在UserDao中使用到User，首先给这两个类加上注解：

User.java:

```
package com.jk;
import org.springframework.stereotype.Component;
@Component
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

UserDao.java:里面有个getUser的方法，通过@Autowired 获取User

```
package com.jk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private User user;

	public User getUser() {
		return user;
	}
}
```
配置文件 bean.xml:

```
<?xml version="1.0" encoding="UTF-8"?>
<beans  xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context-3.0.xsd">
		
   <!--扫描器配置  扫描com.jk下的所有类  -->
   <context:component-scan base-package="com.jk">
   </context:component-scan>
   
</beans>
```
App.java中使用：
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
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		
		// 3.使用bean
		System.out.println(userDao.getUser());

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
```

2、给bean命名：UserDaoImpl1 和 UserDaoImpl2 都实现了 UserDao接口：

UserDao.java:
```
package com.jk;

public interface UserDao {

	public User getUser();
}

```

UserDaoImpl1 和 UserDaoImpl2 都实现了 UserDao接口,然后加上注解  通过@Repository("userDaoImpl1") 方式取名字：

UserDaoImpl1.java
```
package com.jk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDaoImpl1")
public class UserDaoImpl1 implements UserDao{

	@Autowired 
	private User user;
	
	public User getUser() {
		System.out.println("UserDaoImpl1...");
		return user;
	}
}
``

UserDaoImpl2.java
```
package com.jk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDaoImpl2")
public class UserDaoImpl2 implements UserDao{

	@Autowired 
	private User user;
	
	public User getUser() {
		System.out.println("UserDaoImpl2...");
		return user;
	}
}
``

在UserService.java中使用到UserDaoImpl2 和  UserDaoImpl1：

```
package com.jk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	//属性名称需和 @Repository("userDaoImpl1") 一致
	@Autowired
	public UserDao userDaoImpl1;
	
	//属性名称需和 @Repository("userDaoImpl2") 一致
	@Autowired
	public UserDao userDaoImpl2;
	
	public User getUser(){
		System.out.println(userDaoImpl2.getUser());
		return userDaoImpl1.getUser();
	}
}
```