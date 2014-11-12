package com.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.client.MemcachedCache;
import com.model.Users;

/**
 * @class：
 * @author Darwen
 * @date:2014-3-11上午10:51:48
 *
 */
@Aspect
public class UserServiceInterceptor {
		public static final Logger log = Logger
				.getLogger(UserServiceInterceptor.class);

	   //将缓存客户端工具类 MemcachedCache 织入进来
		@Autowired
		private MemcachedCache memcachedCache;

		/*
		 * 定义pointcunt
		 */
		@Pointcut("execution(* com.impl.UserServiceImpl.*(..))")
		public void aPointcut() {

		}

		/**
		 * 环绕装备 用于拦截查询 如果缓存中有数据，直接从缓存中读取；否则从数据库读取并将结果放入缓存
		 * 
		 * @param call
		 * @param name
		 * @return
		 */
		@Around("aPointcut()&&args(name)")
		public Users doFindUserByNameAround(ProceedingJoinPoint call, String name) {
			System.out.println("====pointcut processing====");
			Users users = null;
			if (memcachedCache.getCache().containsKey("findUserByName_" + name)) {
				users = (Users) memcachedCache.get("findUserByName_" + name);
				log.debug("从缓存中读取！findUserByName_" + name);
				System.out.println("从缓存中读取！findUserByName_" + name);
			} else {
				try {
					users = (Users) call.proceed();
					if (users != null) {
						memcachedCache.put("findUserByName_" + name, users);
						log.debug("缓存装备被执行：findUserByName_" + name);
						System.out.println("缓存装备被执行：findUserByName_" + name);
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
			return users;
		}
}
