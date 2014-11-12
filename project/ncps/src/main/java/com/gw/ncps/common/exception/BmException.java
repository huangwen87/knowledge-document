package com.gw.ncps.common.exception;

/**
 * 自定义异常类
 * 
 */
public class BmException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -485825256677060522L;

	public BmException() {
		super();
	}

	public BmException(String message) {
		super(message);
	}

	public BmException(String message, Exception e) {
		super(message, e);
	}
}
