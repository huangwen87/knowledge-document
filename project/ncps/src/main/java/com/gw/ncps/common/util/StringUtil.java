package com.gw.ncps.common.util;

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 去除字符串中'口'形字符
	 * 
	 * @param s
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-9-3
	 */
	public static String replaceLastErrorStr(String s) {
		char c = 0;
		return s.replace(c, ' ');
	}
}
