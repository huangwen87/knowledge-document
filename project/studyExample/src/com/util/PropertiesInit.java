package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  Properties 初始化 
 *  具体配置可以根据自己想加载的.properties文件来
 *  @author darwen
 *  2013-12-19  下午2:46:46
 */
public class PropertiesInit {
	
	private Properties pro = new Properties();
	
	public Properties getPro() {
		return pro;
	}

	public void setPro(Properties pro) {
		this.pro = pro;
	}

	public PropertiesInit(){
		
		InputStream fis;
		try {
			//四个反斜杠代表一个反斜杠, 避免操作系统不同的干扰
			//String workplace = System.getProperty("user.dir").replaceAll("\\\\", "/"); 
			//注意：这里使用this.getClass.getClassLoader()方法而没有使用ClassLoader
			fis = this.getClass().getClassLoader().getResourceAsStream("plugins/conf/config.properties");
			pro.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
