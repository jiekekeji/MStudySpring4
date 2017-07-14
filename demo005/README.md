Spring--SpEL使用表达式装配：
===

一、Bean生命周期步骤：
----------

Spring 对Bean 进行实例化。

Spring 将值和Bean 的引用注入进Bean 对应的属性中。

如果Bean 实现了BeanNameAware接口，Spring 将Bean的ID传递给setBeanName() 接口方法。

如果Bean 实现了BeanFactoryAware接口，Spring 将调用setBeanFactory()接口方法，将BeanFactory 容器实例传入。

如果Bean 实现了ApplicationContextAware 接口，Spring 将调用setApplicationContext()接口方法，将应用上下文的引用传入。

如果Bean 实现了BeanPostProcessor 接口，Spring 将调用它们的postProcessBeforeInitialization() 接口方法。

如果Bean 实现了InitializingBean 接口，Spring 将调用它们的afterPropertiesSet() 接口方法。类似地，如果Bean 使用init-method 声明了初始化方法，该方法也会被调用。

如果Bean 实现了BeanPostProcessor 接口，Spring 将调用它们的postPoressAfterInitialization() 接口方法。

此时此刻，Bean 已经准备就绪，可以被应用程序使用了，它们将一直驻留在应用上下文中，直到该应用上下文被销毁。

如果Bean 实现了DisposableBean 接口，Spring 将调用它的destroy()接口方法。同样，如果Bean 使用destroy-method 声明了销毁方法，该方法也会被调用。

网上盗了一张图：

![image](https://github.com/jiekekeji/MStudySpring4/blob/master/demo004/prevew/demo004-1.gif)

二、Bean生命周期演示：
----------

1、先定义一个bean，User.java,实现相应的接口：

```
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

    //自定义初始化的处理方法，需在beans.xml配置
	public void myInit() {
		System.out.println("myInit");
	}

    //自定义销毁的处理方法，需在beans.xml配置
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
```

2、在定义一个MyBeanPostProcessor.java实现BeanPostProcessor接口，在生命周期过程中插一脚，加入自己的处理：

```
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
```

3、在beans.xml的配置文件中加入如下配置：

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:util="http://www.springframework.org/schema/util"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd">

	<bean id="user" class="com.jk.User" scope="singleton" init-method="myInit"
		destroy-method="myDestroy">
		<property name="name" value="Jack" />
	</bean>

	<bean id="myBeanPostProcessor" class="com.jk.MyBeanPostProcessor" />
</beans>
```

4、在App.java中使用Bean：

```
package com.jk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		// 1. 创建 Spring 的 IOC 容器: 从类路径加载xml配置文件
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

		// 2.从 IOC 容器中获取 bean 的实例:以id的方式
		User user1 = (User) ctx.getBean("user");

		// 3.使用bean
		System.out.println(user1);

		// 4、关闭容器
		ctx.registerShutdownHook();

	}
}
```


打印结果：

```
User构造函数
User setName 函数
setBeanName:user
setBeanFactory
setApplicationContext
postProcessBeforeInitialization:arg0=User [name=Jack]:arg1=user
afterPropertiesSet
myInit
postProcessAfterInitialization:arg0=User [name=Jack]:arg1=user
User [name=Jack]
七月 14, 2017 10:09:17 上午 org.springframework.context.support.ClassPathXmlApplicationContext doClose
信息: Closing org.springframework.context.support.ClassPathXmlApplicationContext@428aaed5: startup date [Fri Jul 14 10:09:17 CST 2017]; root of context hierarchy
destroy
myDestroy
```