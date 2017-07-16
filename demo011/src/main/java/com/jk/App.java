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
		
		User user=new User();
		user.setName("Jack");
		user.setAge(12);
		// 3.使用bean
		userService.add(user);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
