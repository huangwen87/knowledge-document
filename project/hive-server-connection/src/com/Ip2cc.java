package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Ip2cc {

	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive://10.15.112.17:10000/default";
    private static String user = "root";
    private static String password = "111111";
    private static String sql = "";
    private static ResultSet res;
    private static final Logger log = Logger.getLogger(TestMain.class);
    
	public static void main(String[] args) throws Exception{
		     Class.forName(driverName);
         Connection conn = DriverManager.getConnection(url, user, password);
         Statement stmt = conn.createStatement();
         String tableName = "test";
         
         //String quary_sql = "select value, ip2cc(value) from " + tableName + " group by ip2cc(value)"; 
         String quary_sql = "select value, ip2cc2(value) from " + tableName; 
         ResultSet rs = stmt.executeQuery(quary_sql); 
         while(rs.next()){ 
             System.out.println("ip: "+rs.getString(1)+" 归属省份: "+rs.getString(2)); 
         } 
	}

}
