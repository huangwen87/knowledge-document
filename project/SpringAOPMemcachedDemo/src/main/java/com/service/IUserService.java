package com.service;

import com.model.Users;

/**
 * @class：
 * @author Darwen
 * @date:2014-3-11上午10:27:06
 *
 */
public interface IUserService {
	
	public Users findUserByName(String name);

}
