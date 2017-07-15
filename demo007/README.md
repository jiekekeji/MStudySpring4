Spring--Bean的作用域：
===

在 Spring 中, 可以在 <bean> 元素的 scope 属性里设置 Bean 的作用域. 

默认情况下, Spring 只为每个在 IOC 容器里声明的 Bean 创建唯一一个实例, 整个 IOC 容器范围内都能共享该实例：所有后续的 getBean() 调用和 Bean 引用都将返回这个唯一的 Bean 实例.该作用域被称为 singleton, 它是所有 Bean 的默认作用域.

![image](https://github.com/jiekekeji/MStudySpring4/blob/master/demo007/prevew/demo007-1.gif)

1、默认不配置scope的情况下为singleton，为beans.xml配置文件

```
<!-- person1 默认是单例的 singleton-->
<bean id="person1" class="com.jk.Person">
    <property name="weight" value="#{100}" />
    <property name="color" value="#{'黑色'}" />
</bean>
```
App.java中从容器中获取bean实例：

```
// 1. 创建 Spring 的 IOC 容器: 从类路径加载xml配置文件
ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

// 2.从 IOC 容器中获取 bean 的实例:以id的方式
Person person1 = (Person) ctx.getBean("person1");
Person person12 = (Person) ctx.getBean("person1");


// 3.使用bean
System.out.println(person1.hashCode());
System.out.println(person12.hashCode());


// 4、关闭容器
ctx.registerShutdownHook();
```

打印结果：构造函数值调用一次，两次获取的bean的hashcode是相同的：

```
person构造函数
291357518
291357518
```

2、配置scope ="prototype"，beans.xml配置文件

```
<bean id="car" class="com.jk.Car" scope ="prototype">
    <property name="name" value="#{'丰田'}" />
    <property name="price" value="#{2000}" />
</bean>
```

App.java中从容器中获取bean实例：
```
// 1. 创建 Spring 的 IOC 容器: 从类路径加载xml配置文件
ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

// 2.从 IOC 容器中获取 bean 的实例:以id的方式
Car car1 = (Car) ctx.getBean("car");
Car car2 = (Car) ctx.getBean("car");

// 3.使用bean
System.out.println(car1.hashCode());
System.out.println(car2.hashCode());

// 4、关闭容器
ctx.registerShutdownHook();

```

打印结果：构造函数值调用两次，两次获取的bean的hashcode是不相同的：

```
car构造函数
car构造函数
1525821837
1385385632
```