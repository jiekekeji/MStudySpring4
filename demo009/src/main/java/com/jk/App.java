package com.jk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rs.Cat;
import com.rs.Student;

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
//		Teacher teacher = (Teacher) ctx.getBean("teacher");
//		User user = (User) ctx.getBean("user");
		
		// 3.使用bean
		System.out.println(cat);
		System.out.println(student);
//		System.out.println(teacher);
//		System.out.println(user);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
