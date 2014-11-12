package com.gw.ps.dupstat;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;


/**
 * 垃圾去重数量统计
 * @author Darwen
 * */

public class NewsRmDupStat implements Runnable{
	
	private String driver = "com.mysql.jdbc.Driver"; // 驱动  
	private String url = "jdbc:mysql://10.15.89.178:3306/ncps";// 定义URL  
	private String user = "root"; // 用户名字  
	private String password = "111111"; // 用户密码  
	
	
	private NewsRmDupStat(){
		
	}
	
	public static NewsRmDupStat getInstance(){
		return InnerHolder.INSTANCE;
	}
	
	private static class InnerHolder {
		static final NewsRmDupStat INSTANCE = new NewsRmDupStat();
	}
	
	public synchronized void start() {
		new Thread(this).start();
	}
	
	
	public void init(){
		NewsRmDupStat.getInstance().start();
	}
	
	public void run() {
		try {
				Mysql mysql = new Mysql(driver, url, user, password); 
				Thread.sleep(1000);
				Map<Long, Integer> map = mysql.query_init("news_duprmv_content");
				mysql.insert("dupIDcount", map);
				
				while(true) {
					Map<Long, Integer> map_loc = mysql.query_update("news_duprmv_content");
					
					mysql.insert("dupIDcount", map_loc);
					
					Thread.sleep(60*1000);
				}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
