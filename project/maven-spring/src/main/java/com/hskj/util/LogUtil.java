package com.hskj.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 自定义日志配置文件，方便扩展
 * 
 */
public class LogUtil {

	static InputStream fis;
	
	static {
		try {
			Properties prop = new Properties();
			
			String base = System.getProperty("user.dir").replaceAll("\\\\", "/");
			File file = new File(base + "/conf/mylog4j.properties");
			if(file.exists()){
				//打jar时使用
				fis = new BufferedInputStream(new FileInputStream(file));
			}else{
				//maven工程下运行时，在src/main/resources中找
				fis = LogUtil.class.getClassLoader().getResourceAsStream("conf/mylog4j.properties");
			}
			prop.load(fis);
			PropertyConfigurator.configure(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 依据不同类的Class对象创建不同的Logger类对象
	 * 
	 * @param clazz
	 * @return
	 */
	public static Logger getInstance(Class<?> clazz) {
		if (clazz == null)
			return null;
		return Logger.getLogger(clazz);
	}

}
