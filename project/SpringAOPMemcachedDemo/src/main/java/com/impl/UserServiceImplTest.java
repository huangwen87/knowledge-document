package com.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model.Users;
import com.service.IUserService;

/**
 * @class：
 * @author Darwen
 * @date:2014-3-11上午11:17:57
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml", "classpath*:spring-service.xml"})
public class UserServiceImplTest {

	@Resource(name = "userService")
	private IUserService userService;
	
	@Test
	public void testFindUserByName() {
		String id = "huangwen";
		Users str = (Users) userService.findUserByName(id);
		System.out.println("users: " + str.toString());
	}

}
