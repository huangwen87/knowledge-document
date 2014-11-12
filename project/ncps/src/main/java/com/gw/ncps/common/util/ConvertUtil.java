package com.gw.ncps.common.util;

public class ConvertUtil {

	public static Integer obj2Integer(Object object) {
		return object == null ? null : Integer.valueOf(object.toString());
	}

	public static String obj2String(Object object) {
		return object == null ? null : object.toString();
	}
	
	public static Long obj2Long(Object object){
		return object == null ? null : Long.valueOf(object.toString());
	}
}
