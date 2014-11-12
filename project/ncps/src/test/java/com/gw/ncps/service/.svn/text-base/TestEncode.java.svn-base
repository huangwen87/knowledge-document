package com.gw.ncps.service;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestEncode {
	// 驱动程序名
	String driver = "com.mysql.jdbc.Driver";
	// URL指向要访问的数据库名scutcs
	String url = "jdbc:mysql://10.15.107.76:3306/1ncpstest";
	// MySQL配置时的用户名
	String user = "root";
	// Java连接MySQL配置时的密码
	String password = "111111";
	String sql = "select text from News where id = 1";
	String sql2 = "insert into News(id,text) values('0000','测试中文！')";
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public String test() throws SQLException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Blob b = rs.getBlob(1);
				String str = new String(b.getBytes(1, (int) b.length()));
				return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("finally");
			con.close();
			ps.close();
			rs.close();
		}
		return "";
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		TestEncode t = new TestEncode();
		String str = null;
		try {
			str = t.test();
			// str = "朱江铭";
			System.out.println(str);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
