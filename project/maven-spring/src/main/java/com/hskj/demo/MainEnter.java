package com.hskj.demo;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hskj.service.UserService;
import com.hskj.util.LogUtil;

/**
 * 测试主函数
 * @author darwen
 * 2014-11-12 上午9:09:28
 */
public class MainEnter {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	public static void main(String[] args) {
		
		MainEnter me = new MainEnter();
		me.userServiceTest();

	}

	public void userServiceTest() {
		logger.info("=======userServiceTest  start=======");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"conf/applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		try {
			System.out.println(userService.countAll());
		} catch (Exception e) {
			logger.error("Exception", e);   //打印栈信息
			logger.error("出错信息，自由说明");
			e.printStackTrace();
		}
		logger.info("=======userServiceTest  end=======");
	}

}
