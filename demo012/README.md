Spring--AspectJ 注解声明切面:
===

要在 Spring 中声明 AspectJ 切面, 只需要在 IOC 容器中将切面声明为 Bean 实例. 当在 Spring IOC 容器中初始化 AspectJ 切面之后, Spring IOC 容器就会为那些与 AspectJ 切面相匹配的 Bean 创建代理.

在 AspectJ 注解中, 切面只是一个带有 @Aspect 注解的 Java 类. 

通知是标注有某种注解的简单的 Java 方法.

AspectJ 支持 5 种类型的通知注解: 

    @Before: 前置通知, 在方法执行之前执行
    @After: 后置通知, 在方法执行之后执行 
    @AfterRunning: 返回通知, 在方法返回结果之后执行
    @AfterThrowing: 异常通知, 在方法抛出异常之后
    @Around: 环绕通知, 围绕着方法执行


1、被切入的类 UserService.java,添加@Service注解，给IOC容器管理：

```
package com.jk;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void addUser(User user) {
		System.out.println("addUser");
	}

	public int deleteUser() {

		System.out.println("deleteUser");

		return 1;
	}

	public List<User> queryUser() {

		List<User> users = new ArrayList<User>();

		System.out.println("queryUser");

		return users;
	}

	public User updateUser(int id, User user) {

		System.out.println("updateUser");

		return user;
	}
}
```

2、切入类AspectBean.java,添加@Aspect @Component注解：

execution 表达式：

```
	/**
	 * execution 表达式： 
	 * 第一个返回值 * :函数的返回值任意类型 
	 * 第二个 目标函数 com.jk.*.* : con.jk包下的任意类的任意方法
	 * 第三个函数参数 (..) :任意类型 和数量的参数
	 */
```

```
@Before("execution(* com.jk.*.*(..))")
	public void logBefore() {
		System.out.println("logBefore");
	}

	@After("execution(* com.jk.*.*(..))")
	public void logAfter() {
		System.out.println("logAfter");
	}

	@AfterReturning("execution(* com.jk.*.*(..))")
	public void logAfterRunning() {
		System.out.println("AfterReturning");
	}
	
	@AfterThrowing("execution(* com.jk.*.*(..))")
	public void logAfterThrowing() {
		System.out.println("AfterReturning");
	}
	
	@Around("execution(* com.jk.*.*(..))")
	public void logAround() {
		System.out.println("logAround");
	}
```

3、spring配置文件bean.xml：

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context-3.0.xsd 
	http://www.springframework.org/schema/mvc
	http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
	 http://www.springframework.org/schema/aop 
	http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">

	<!--启动AspectJ注解模式 -->
	<aop:aspectj-autoproxy />


	<!--扫描器配置 扫描com.jk下的所有类 -->
	<context:component-scan base-package="com.jk"></context:component-scan>

</beans>
```


4、App.java下测试:

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
		UserService userService = (UserService) ctx.getBean("userService");

		User user = new User();
		user.setName("Jack");
		user.setAge(12);

		// 3.使用bean
		userService.addUser(user);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
```

将AspectBean.java中logAround方法注释后运行的结果:

```
logBefore
addUser
logAfter
AfterReturning
```

将AspectBean.java中logAround方法开启后运行的结果:

```
logAround
logAfter
AfterReturning
```