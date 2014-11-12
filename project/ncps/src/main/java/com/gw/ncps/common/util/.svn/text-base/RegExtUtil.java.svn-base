package com.gw.ncps.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExtUtil {

	/**
	 * 比较字符串是否符合正则表达式规则
	 * 
	 * @param regEx
	 *            正则表达式
	 * @param input
	 *            匹配字符串
	 * @return
	 */
	public static boolean matcher(String regEx, String input) {
		if (input != null && !"".equals(input)) {
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(input);
			return m.matches();
		} else {
			return false;
		}
	}
}
