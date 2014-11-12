package com.gw.ncps.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml", "classpath*:spring/spring-newsduplicateremoval.xml", "classpath*:spring/spring-login.xml" })
public class HelloJunit {

	@Resource(name = "ndrService")
	private INewsDuplicateRemovalService ndrService;

	@Test
	public void testQueryDustById() {
		String id = "	4272750";
		String str = (String) ndrService.queryContentById(id);
	}

}
