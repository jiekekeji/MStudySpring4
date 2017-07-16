Spring--泛型依赖注入:
===

1、有两个基类，BaseDao和BaseService；

BaseDao.java:
```
package com.jk;

public class BaseDao <T>{

	public void save(T entity){
		System.out.println("save.."+entity);
	}
}
```

BaseService.java

```
package com.jk;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

	//引用BaseDao
	@Autowired
	protected BaseDao<T> baseDao;
	
	public void add(T entity){
		baseDao.save(entity);
	}
}
```

2、BaseDao和BaseService的子类UserDao和UserService,将他们给IOC容器管理:

UserDao.java:

```
package com.jk;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User>{
	
}
```

UserService.java:

```
package com.jk;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User>{

}
```
3、配置扫描器bean.xml：

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

4、App.java中使用：

```
package com.jk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {

		// 1. 创建 Spring 的 IOC 容器: 从类路径加载xml配置文件
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

		// 2.从 IOC 容器中获取 bean 的实例:以id的方式,注解时，用类名第一个字母小写的方式获取
		UserService userService = (UserService) ctx.getBean("userService");
		
		User user=new User();
		user.setName("Jack");
		user.setAge(12);
		// 3.使用bean
		userService.add(user);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
```