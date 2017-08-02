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
