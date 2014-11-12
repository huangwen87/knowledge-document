package com.model;

import java.io.Serializable;

/**
 * @class：
 * @author Darwen
 * @date:2014-3-11上午10:27:47
 *
 */
public class Users implements Serializable{
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + "]";
	}
	
	

}
