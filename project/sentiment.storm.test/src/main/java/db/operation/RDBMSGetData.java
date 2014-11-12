package db.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sentiment.configure.Configure;

public class RDBMSGetData {
	private Connection con = null;
	private PreparedStatement stmt = null;
	private static Configure configure;
	private RDBMSConnector conn = new RDBMSConnector();
	
	public RDBMSGetData(Connection con){
		this.con = con;
	}
	
	public RDBMSGetData(Configure m_configure){
		//path = System.getProperty("user.dir").replaceAll("%20", " ");
		//path = path + "/Files/";
		configure = m_configure;
	}
	
	public void getConnection(){
		try{
			
			this.con = conn.getConnection(configure);
			//this.con = conn.getConnection("jdbc:mysql://10.15.107.75:3306/sentiment_classify", "root", "111111");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getData(String sql){
		ResultSet result = null;
		try{
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public void closeDB() throws SQLException{
		con.close();
	}
	
	public static void main(String[] args){
		/*
		try{
			RDBMSGetData rgt = new RDBMSGetData();
			rgt.getConnection();
			ResultSet res = rgt.getData("SELECT word FROM seeds WHERE id = 114;");
			res.next();
			System.out.println(res.getString(1));
			rgt.closeDB();
		}catch(SQLException se){
			se.printStackTrace();
		}
		*/
	}
}