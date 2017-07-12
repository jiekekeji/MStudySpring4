Spring--HelloWorld入门程序
===

1、创建maven的java项目，创建过程如下：

![image](https://github.com/jiekekeji/MStudySpring4/blob/master/demo001/prevew/demo001-1.gif)

创建完成的项目是没有 src/main/resources 目录的，该目录要以Source Folder 的方式创建，该目录是maven的classpath目录。

2、在pom.xml添加spring的依赖如下：

```
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
		<!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>1.1.3</version>
		</dependency>

	</dependencies>
```

3、在src/main/java下的包com.jk创建一个User 类，编辑如下：

```
package com.jk;

public class User {

	private long id;
	private String name;
	private int age;

	public User() {
		System.out.println("空参数构造函数");
	}

	private User(long id, String name, int age) {
		System.out.println("有参数构造函数：" + "id=" + id + ":name=" + name + ":age=" + age);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
```

4、在src/main/resources下(src/main/resources是maven项目的classpath路径)，新建spring的配置文件 bean.xml,配置如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">

	<!-- 配置一个 bean -->
	<bean id="user" class="com.jk.User">
	</bean>
</beans>
```

其中id是bean的唯一标识；class全类名(要求bean中必须要有无参的构造器，不然会报错)；

5、在src/main/java下的包com.jk的App类中添加main方法，编辑内容如下：

```
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// 1. 创建 Spring 的 IOC 容器: 从类路径加载xml配置文件
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

		// 2.从 IOC 容器中获取 bean 的实例:以id的方式
		// User user = (User) ctx.getBean("user");

		// 2.从 IOC 容器中获取 bean 的实例:以类型的方式获取
		User user = (User) ctx.getBean(User.class);

		System.out.println(user);

	}
}
```
从容器中获取Bean:

```
    // 2.从 IOC 容器中获取 bean 的实例:以id的方式
    // User user = (User) ctx.getBean("user");
    
    // 2.从 IOC 容器中获取 bean 的实例:以类型的方式获取
    User user = (User) ctx.getBean(User.class);
```

最后运行main方法，打印内容：

```
空参数构造函数
User [id=0, name=null, age=0]
```

6、ApplicationContext 的主要实现类从不同位置加载配置文件。

    ClassPathXmlApplicationContext：从 类路径下加载配置文件。
    
    FileSystemXmlApplicationContext: 从文件系统中加载配置文件。
    
    ConfigurableApplicationContext 扩展于 ApplicationContext，新增加两个主要方法：refresh() 和 close()， 让 ApplicationContext 具有启动、刷新和关闭上下文的能力。
    
    ApplicationContext 在初始化上下文时就实例化所有单例的 Bean。
    
    WebApplicationContext 是专门为 WEB 应用而准备的，它允许从相对于 WEB 根目录的路径中完成初始化工作。


