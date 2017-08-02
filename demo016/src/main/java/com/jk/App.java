package com.jk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class App {
	
	public static void main(String[] args) throws Exception {

		// 1. 创建 Spring 的 IOC 容器: 从类路径加载xml配置文件
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

		// 2.从 IOC 容器中获取 bean 的实例:以id的方式,注解时，用类名第一个字母小写的方式获取
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		UserService userService=ctx.getBean(UserService.class);

		System.out.println(jdbcTemplate);

		// 3.使用bean
		
		userService.minusBalance(jdbcTemplate);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}

}
