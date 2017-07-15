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
//		Person person1 = (Person) ctx.getBean("person1");
//		Person person12 = (Person) ctx.getBean("person1");
		
		Car car1 = (Car) ctx.getBean("car");
		Car car2 = (Car) ctx.getBean("car");

		// 3.使用bean
//		System.out.println(person1.hashCode());
//		System.out.println(person12.hashCode());

		System.out.println(car1.hashCode());
		System.out.println(car2.hashCode());
		
		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
