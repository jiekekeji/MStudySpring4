Spring--使用外部属性配置文件:
===


1、在类路径下加入配置文件person.properties，每个属性可以在前面加个前缀用于区别，如person.xxx=xxxx:

```
person.weight=100
person.color=yellow
```

2、使用步骤，在beanx.xml中，

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
   <!--第一步、加入命名空间  -->
   
   <!--在beans 下加入命名空间：xmlns:context="http://www.springframework.org/schema/context"  -->
   
   <!-- 第二步、导入配置文件，类路径下 -->
	<context:property-placeholder location="classpath:person.properties"/>
	
	<!--第三步、使用配置文件的配置 ${} -->
	<bean id="person1" class="com.jk.Person">
		<property name="weight" value="${person.weight}" />
		<property name="color" value="${person.color}" />
	</bean>
</beans>
```

最后运行结果：

```
person构造函数
Person [weight=100, color=yellow]
```
