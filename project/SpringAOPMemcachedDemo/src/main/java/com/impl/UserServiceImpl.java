package com.impl;

import com.model.Users;
import com.service.IUserService;

/**
 * @class：
 * @author Darwen
 * @date:2014-3-11上午10:25:42
 *
 */
public class UserServiceImpl implements IUserService{
	
	    @Override
	    public Users findUserByName(String name) {
	    	Users users = new Users();
	    	users.setUsername(name);
	    	users.setPassword("111111");
	        return users;
	    }
}
