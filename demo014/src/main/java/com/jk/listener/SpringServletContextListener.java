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

		// 1、获取spring配置文件的名称和位置
		ServletContext servletContext = arg0.getServletContext();
		/**
		 * 1、getInitParameter()方法是在GenericServlet接口中新定义的一个方法，用来调用初始化在web.
		 * xml中存放的参量。
		 * 如果通过在web.xml中的ServletContext上下文中定义参量，那么整个web应用程序中的servlet都可调用，web.
		 * xml中的格式为： <context-param> <param-name>test</param-name>
		 * <param-value>Is it me</param-value> < context -param> 2、调用
		 * <context-param>中的参量,调用格式为： String name =getServletContext().
		 * getInitParameter(“name”); 或 String name =
		 * getServletConfig().getServletContext().getInitParameter(“name”);
		 */
		String config = servletContext.getInitParameter("configLocation");
		// 2、创建IOC容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		// 3、将IOC容器放在ServletContext的一个属性中
		servletContext.setAttribute("ApplicationContext", ctx);

	}

}
