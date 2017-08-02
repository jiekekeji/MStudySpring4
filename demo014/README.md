Spring-spring在web在的使用思路-1
===

1、创建maven web项目，在pom.xml中加入依赖如下依赖：

~~~
<dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/javax.servlet/servlet-api -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
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
    <build>
        <finalName>demo014</finalName>
        <pluginManagement>
            <plugins>
                <!--tomcat7 maven 插件-->
                <plugin>
                    <groupId>org.apache.tomcat.maven</groupId>
                    <artifactId>tomcat7-maven-plugin</artifactId>
                    <version>2.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
~~~

2、在类路径下新建spring的配置文件 beans.xml：

~~~
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">

	<!-- 配置一个 bean -->
	<bean id="user" class="com.jk.bean.User">
		<property name="name" value="小丽"></property>
		<property name="age" value="22"></property>
	</bean>
</beans>
~~~

3、新建一个listener用于在web程序启动时初始化IOC容器，并将容器放入全局域中，SpringServletContextListener.java：

~~~
    package com.jk.listener;
    import javax.servlet.ServletContext;
    import javax.servlet.ServletContextEvent;
    import javax.servlet.ServletContextListener;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;
    public class SpringServletContextListener implements ServletContextListener {
        public void contextDestroyed(ServletContextEvent arg0) {
        }
        public void contextInitialized(ServletContextEvent arg0) {
    
            // 1、获取ServletContext
            ServletContext servletContext = arg0.getServletContext();
    
            // 2、加载类路径下的beans.xml，创建IOC容器
            ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
    
            // 3、将IOC容器放在ServletContext的一个属性中
            servletContext.setAttribute("ApplicationContext", ctx);
    
        }
    }
~~~


4、在web.xml中配置第三步的SpringServletContextListener，web.xml:


~~~
    <!DOCTYPE web-app PUBLIC
     "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
     "http://java.sun.com/dtd/web-app_2_3.dtd" >
    <web-app>
        <display-name>Archetype Created Web Application</display-name>
    
        <listener>
            <listener-class>com.jk.listener.SpringServletContextListener</listener-class>
        </listener>
    
        <!-- servlet 配置 -->
        <servlet>
            <servlet-name>userWeb</servlet-name>
            <servlet-class>com.jk.web.UserWeb</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>userWeb</servlet-name>
            <url-pattern>/userweb</url-pattern>
        </servlet-mapping>
    </web-app>
~~~

5、新建servlet:UserWeb.java,从域中取出IOC容器:

~~~
package com.jk.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.jk.bean.User;

public class UserWeb extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 从application域对象中得到IOC容器的引用
		ServletContext servletContext = getServletContext();
		ApplicationContext ctx = (ApplicationContext) servletContext.getAttribute("ApplicationContext");
		// 从IOC容器中得到需要的bean
		User user = (User) ctx.getBean("user");
		System.out.println(user);

		resp.getWriter().append("Served at: ");

	}
}
~~~

6、在web.xml中配置UserWeb：

~~~
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
	<display-name>Archetype Created Web Application</display-name>

	<listener>
		<listener-class>com.jk.listener.SpringServletContextListener</listener-class>
	</listener>

	<!-- servlet 配置 -->
	<servlet>
		<servlet-name>userWeb</servlet-name>
		<servlet-class>com.jk.web.UserWeb</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>userWeb</servlet-name>
		<url-pattern>/userweb</url-pattern>
	</servlet-mapping>
</web-app>
~~~


启动tomcat7 maven插件，访问/userweb 打印如下：

~~~
User [name=小丽, age=22]
~~~




