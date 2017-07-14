package com.jk;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("postProcessAfterInitialization:" + "arg0=" + arg0 + ":arg1=" + arg1);

		// 处理后的对象一定要返回
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		System.out.println("postProcessBeforeInitialization:" + "arg0=" + arg0 + ":arg1=" + arg1);

		// 处理后的对象一定要返回
		return arg0;
	}
}
