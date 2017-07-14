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
		User user1 = (User) ctx.getBean("user");

		// 3.使用bean
		System.out.println(user1);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
