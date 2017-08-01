package com.jk;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		// 1. 创建 Spring 的 IOC 容器: 从类路径加载xml配置文件
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

		// 2.从 IOC 容器中获取 bean 的实例:以id的方式,注解时，用类名第一个字母小写的方式获取
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

		System.out.println(jdbcTemplate);

		// 3.使用bean,插入語句
		String sql = "INSERT INTO spring_user (user_name,password) VALUES (?,?);";

		System.out.println(jdbcTemplate.update(sql, "jack", "123456"));

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
