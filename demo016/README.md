Spring-事务配置，基于@Transactional注解的使用
===

1、创建java工程，在pom.xml文件中加入如下依赖：

~~~
<dependencies>

		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>4.2.5.RELEASE</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>4.2.5.RELEASE</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-beans -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>4.2.5.RELEASE</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-expression -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-expression</artifactId>
			<version>4.2.5.RELEASE</version>
		</dependency>

		<!--aspectj和logging日志包 -->
		<!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>1.1.3</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.7.4</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.springframework/spring-aspects -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aspects</artifactId>
			<version>4.1.6.RELEASE</version>
		</dependency>

		<!--mysql驱动和c3p0数据源 -->
		<!-- https://mvnrepository.com/artifact/c3p0/c3p0 -->
		<dependency>
			<groupId>c3p0</groupId>
			<artifactId>c3p0</artifactId>
			<version>0.9.1.2</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.38</version>
		</dependency>

		<!--使用JdbcTemplate所需要的包 -->
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.2.5.RELEASE</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>4.2.5.RELEASE</version>
		</dependency>

	</dependencies>
~~~


2、在类路径下新建spring的配置文件bean.xml和数据库的配置文件db.properties，内容分别如下：

db.properties：

~~~
jdbc.user=root
jdbc.password=123456
jdbc.driverClass=com.mysql.jdbc.Driver
jdbc.jdbcUrl=jdbc:mysql://localhost:3306/my-test

jdbc.initPoolSize=5
jdbc.maxPoolSize=10
~~~

bean.xml:

~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">

	<!--扫描器配置 扫描com.jk下的所有类 -->
	<context:component-scan base-package="com.jk"></context:component-scan>

	<!-- 导入 C3P0 数据源配置文件 -->
	<context:property-placeholder location="classpath:db.properties" />

	<!-- 配置 C3P0 数据源 -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="user" value="${jdbc.user}"></property>
		<property name="password" value="${jdbc.password}"></property>
		<property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
		<property name="driverClass" value="${jdbc.driverClass}"></property>

		<property name="initialPoolSize" value="${jdbc.initPoolSize}"></property>
		<property name="maxPoolSize" value="${jdbc.maxPoolSize}"></property>
	</bean>


	<!-- 配置 Spirng 的 JdbcTemplate -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource"></property>
	</bean>


	<!-- Spring事务管理器的配置，管理C3P0数据源dataSource -->
	<!-- 配置事务管理器 -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"></property>
	</bean>

	<!-- 启用事务注解 -->
	<tx:annotation-driven transaction-manager="transactionManager" />

</beans>
~~~

3、创建java类  UserService.jva:

~~~
package com.jk;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {

	//1、加上Transactional事务注解
	@Transactional
	public void minusBalance(JdbcTemplate jdbcTemplate) throws Exception {
		// 从id=11的用户余额中减去10，在id=12的用户余额中加上10

		String sql1 = "UPDATE spring_user SET balance=balance-10 WHERE id=11";

		String sql2 = "UPDATE spring_user SET balance=balance+10 WHERE id=12";

		String sql3 = "SELECT balance from spring_user WHERE id=11";

		// 2、先给id=12的余额加上10
		jdbcTemplate.update(sql2);

		// 3、判断id=11的余额是否足够，-10后不能小于0
		int balance11 = jdbcTemplate.queryForObject(sql3, Integer.class);
		System.out.println(balance11);

		if (balance11 < 60) {
			System.out.println("余额不足" + balance11);
			// 抛出异常回滚事务
			throw new RuntimeException("余额不足");
		}

		// 4、如果足够 从id=11的余额中减去10
		jdbcTemplate.update(sql1);
	}
}
~~~

4、在App.java中测试minusBalance方法：

~~~
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
~~~