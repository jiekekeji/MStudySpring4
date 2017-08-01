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

		// ��application������еõ�IOC����������
		ServletContext servletContext = getServletContext();
		ApplicationContext ctx = (ApplicationContext) servletContext.getAttribute("ApplicationContext");
		// ��IOC�����еõ���Ҫ��bean
		User user = (User) ctx.getBean("user");
		System.out.println(user);
		
		resp.getWriter().append("Served at: ");

	}

}
