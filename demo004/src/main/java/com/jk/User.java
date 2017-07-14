package com.jk;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class User
		implements BeanNameAware, BeanFactoryAware, InitializingBean, ApplicationContextAware, DisposableBean {

	private String name;

	public User() {
		System.out.println("User构造函数");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("User setName 函数");
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

	public void myInit() {
		System.out.println("myInit");
	}

	public void myDestroy() {
		System.out.println("myDestroy");
	}

	public void destroy() throws Exception {
		System.out.println("destroy");

	}

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("setApplicationContext");

	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");

	}

	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("setBeanFactory");
	}

	public void setBeanName(String arg0) {
		System.out.println("setBeanName:" + arg0);

	}

}
