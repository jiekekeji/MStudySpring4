package com.jk;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {

	//1、加上Transactional事务注解
	@Transactional
	public void minusBalance(JdbcTemplate jdbcTemplate) throws Exception {
		// 从id=11的用户余额中减去10，在id=12的用户余额中加上10

		String sql1 = "UPDATE spring_user SET balance=balance-10 WHERE id=11";

		String sql2 = "UPDATE spring_user SET balance=balance+10 WHERE id=12";

		String sql3 = "SELECT balance from spring_user WHERE id=11";

		// 2、先给id=12的余额加上10
		jdbcTemplate.update(sql2);

		// 3、判断id=11的余额是否足够，-10后不能小于0
		int balance11 = jdbcTemplate.queryForObject(sql3, Integer.class);
		System.out.println(balance11);

		if (balance11 < 60) {
			System.out.println("余额不足" + balance11);
			// 抛出异常回滚事务
			throw new RuntimeException("余额不足");
		}

		// 4、如果足够 从id=11的余额中减去10
		jdbcTemplate.update(sql1);

	}
}
