package com.gw.ps.dupstat;

import java.io.UnsupportedEncodingException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Map.Entry;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Mysql  
{
	private Connection conn;
	private long maxautoid;
	private Map<Long, Integer> map;
	
	public Mysql() {

	}
	
	public Mysql(String driver, String url, String user, String password) {
		maxautoid = 0L;
		map = new HashMap<Long, Integer>();
		
		try {
			connect(driver, url, user, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long get_maxautoid() {
		return maxautoid;
	}
	
	public void set_maxautoid(long maxautoid) {
		this.maxautoid = maxautoid;
	}
	
	/** 
	 * 此方法建立连接
	 * @param path: 分词相关文件的路径
	 * @throws SQLException 
	 */
	public void connect(String driver, String url, String user, String password) throws ClassNotFoundException , SQLException  
	{  
		Class.forName(driver);  
		conn = DriverManager.getConnection(url, user, password);
		if(!conn.isClosed())  
		{  
			System.out.println("Succeeded connecting to the Database!");  
		}  
	}  
	  
	public void insert(String table, Map<Long, Integer> map) throws UnsupportedEncodingException, SQLException  
	{  
		String sql = "replace into "+table+" values(?,?,?);";  
		PreparedStatement pst = conn.prepareStatement(sql);
		Iterator<Entry<Long, Integer>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<Long, Integer> entry = (Map.Entry<Long, Integer>) iter.next();
			Long ID2 = (Long)entry.getKey();
			Integer count = (Integer)entry.getValue();
			pst.setLong(1, ID2);
			pst.setInt(2, count);
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date da = new Date(System.currentTimeMillis());
			pst.setString(3, formatter.format(da));
			pst.addBatch();
		}
		pst.executeBatch();
		pst.clearBatch();
		pst.close();
	}

	public Map<Long, Integer> query_init(String table) throws SQLException, UnsupportedEncodingException {
		map.clear();
		Long maxid = 0L;
		
		try {
			String query = "SELECT autoid,ID2 FROM " + table + " ORDER BY autoid ASC";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				maxid = Long.valueOf(rs.getLong("autoid"));
				Long ID2 = Long.valueOf(rs.getLong("ID2"));
				if(map.containsKey(ID2)) {
					Integer count = map.get(ID2);
					count++;
					map.put(ID2, count);
				}
				else {
					map.put(ID2, 0);
				}
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		set_maxautoid(maxid);
		
		return map;
	} 
	
	public Map<Long, Integer> query_update(String table) throws SQLException, UnsupportedEncodingException {
		Map<Long, Integer> map_loc = new HashMap<Long, Integer>();
		Long maxid = get_maxautoid();
		try {
			String query = "SELECT autoid,ID2 FROM " + table + " where autoid>"+ maxid +" ORDER BY autoid ASC";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				maxid = Long.valueOf(rs.getLong("autoid"));
				Long ID2 = Long.valueOf(rs.getLong("ID2"));
				if(map.containsKey(ID2)) {
					Integer count = map.get(ID2);
					count++;
					map.put(ID2, count);
					map_loc.put(ID2, count);
				}
				else {
					map.put(ID2, 0);
					map_loc.put(ID2, 0);
				}
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		set_maxautoid(maxid);
		
		return map_loc;
	}

	/** 
	 * 关闭连接
	 * @throws SQLException 	  
	 */  
	public void close() throws SQLException  
	{  
		conn.close();
	}    
}  