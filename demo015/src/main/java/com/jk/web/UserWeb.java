package com.jk.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jk.bean.User;

public class UserWeb extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1、获取IOC容器
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		// 2. 从 IOC 容器中得到 bean
		User user = ctx.getBean(User.class);

		// 3. 使用 bean
		System.out.println("user=" + user);

		resp.getWriter().write("ioc");

	}
}
