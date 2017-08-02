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
