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

		// 1����ȡServletContext
		ServletContext servletContext = arg0.getServletContext();

		// 2��������·���µ�beans.xml������IOC����
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		// 3����IOC��������ServletContext��һ��������
		servletContext.setAttribute("ApplicationContext", ctx);

	}

}
