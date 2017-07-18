package com.jk;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectBean {

	/**
	 * execution 表达式： 
	 * 第一个返回值 * :函数的返回值任意类型 
	 * 第二个 目标函数 com.jk.*.* : con.jk包下的任意类的任意方法
	 * 第三个函数参数 (..) :任意类型 和数量的参数
	 */
	@Before("execution(* com.jk.*.*(..))")
	public void logBefore() {
		System.out.println("logBefore");
	}

	@After("execution(* com.jk.*.*(..))")
	public void logAfter() {
		System.out.println("logAfter");
	}

	@AfterReturning("execution(* com.jk.*.*(..))")
	public void logAfterRunning() {
		System.out.println("AfterReturning");
	}
	
	@AfterThrowing("execution(* com.jk.*.*(..))")
	public void logAfterThrowing() {
		System.out.println("AfterReturning");
	}
	
	@Around("execution(* com.jk.*.*(..))")
	public void logAround() {
		System.out.println("logAround");
	}
	
}
