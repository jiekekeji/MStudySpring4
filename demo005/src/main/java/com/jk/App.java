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

		// 2.从 IOC 容器中获取 bean 的实例:以id的方式
		User user1 = (User) ctx.getBean("user_1");

		User user2 = (User) ctx.getBean("user_2");

		User user3 = (User) ctx.getBean("user_3");

		User user4 = (User) ctx.getBean("user_4");

		User user5 = (User) ctx.getBean("user_5");

		User user6 = (User) ctx.getBean("user_6");
		// 3.使用bean
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user3);
		System.out.println(user4);
		System.out.println(user5);
		System.out.println(user6);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
