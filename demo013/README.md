Spring-C3P0数据源和JdbcTemplate
===

1、C3P0和JdbcTemplate的maven依赖：

~~~
    <!--mysql驱动和c3p0数据源-->
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

    <!--使用JdbcTemplate所需要的包-->
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>4.3.9.RELEASE</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-tx</artifactId>
        <version>4.3.9.RELEASE</version>
    </dependency>
~~~

2、C3P0配置文件，db.properties:

~~~
jdbc.user=root
jdbc.password=123456
jdbc.driverClass=com.mysql.jdbc.Driver
jdbc.jdbcUrl=jdbc:mysql://127.0.0.1:3306/my-test

jdbc.initPoolSize=5
jdbc.maxPoolSize=10
~~~

3、spring配置文件：bean.xml:

~~~
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
	
	<!--扫描器配置 扫描com.jk下的所有类 -->
	<context:component-scan base-package="com.jk"></context:component-scan>
	
	<!-- 导入 C3P0 数据源配置文件 -->
	<context:property-placeholder location="classpath:db.properties"/>
	
	<!-- 配置 C3P0 数据源 -->
	<bean id="dataSource"
		class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="user" value="${jdbc.user}"></property>
		<property name="password" value="${jdbc.password}"></property>
		<property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
		<property name="driverClass" value="${jdbc.driverClass}"></property>

		<property name="initialPoolSize" value="${jdbc.initPoolSize}"></property>
		<property name="maxPoolSize" value="${jdbc.maxPoolSize}"></property>
	</bean>
	

	<!-- 配置 Spirng 的 JdbcTemplate -使用C3P0 数据源dataSource->
	<bean id="jdbcTemplate" 
		class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource"></property>
	</bean>

</beans>
~~~

4、使用App.java

~~~
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
~~~


5、打印结果:

~~~
1
~~~





