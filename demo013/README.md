Spring-C3P0和JdbcTemplate
===

1、C3P0和JdbcTemplate的maven依赖：

~~~
		<!--mysql驱动和c3p0数据源-->
		<!-- https://mvnrepository.com/artifact/c3p0/c3p0 -->
		<dependency>
		    <groupId>c3p0</groupId>
		    <artifactId>c3p0</artifactId>
		    <version>0.9.1.2</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
		    <groupId>mysql</groupId>
		    <artifactId>mysql-connector-java</artifactId>
		    <version>5.1.38</version>
		</dependency>

		<!--使用JdbcTemplate所需要的包-->
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>4.3.9.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>4.3.9.RELEASE</version>
        </dependency>
~~~

2、spring的配