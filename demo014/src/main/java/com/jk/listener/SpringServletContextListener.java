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

		// 1����ȡspring�����ļ������ƺ�λ��
		ServletContext servletContext = arg0.getServletContext();
		/**
		 * 1��getInitParameter()��������GenericServlet�ӿ����¶����һ���������������ó�ʼ����web.
		 * xml�д�ŵĲ�����
		 * ���ͨ����web.xml�е�ServletContext�������ж����������ô����webӦ�ó����е�servlet���ɵ��ã�web.
		 * xml�еĸ�ʽΪ�� <context-param> <param-name>test</param-name>
		 * <param-value>Is it me</param-value> < context -param> 2������
		 * <context-param>�еĲ���,���ø�ʽΪ�� String name =getServletContext().
		 * getInitParameter(��name��); �� String name =
		 * getServletConfig().getServletContext().getInitParameter(��name��);
		 */
		String config = servletContext.getInitParameter("configLocation");
		// 2������IOC����
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		// 3����IOC��������ServletContext��һ��������
		servletContext.setAttribute("ApplicationContext", ctx);

	}

}
