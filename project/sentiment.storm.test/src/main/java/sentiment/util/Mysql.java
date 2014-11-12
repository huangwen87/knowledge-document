package sentiment.util;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Mysql  
{
	private Connection conn;
	private long lastID_shingle;
	private long lastID_shingling;
	private Filter filter = null;
	
	public Mysql() {
		filter = new Filter();
		lastID_shingle = 0L;
		lastID_shingling = 0L;
		try {
			connect("com.mysql.jdbc.Driver", "jdbc:mysql://10.15.107.75:3306/1ncpstest", "root", "111111");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Mysql(String driver, String url, String user, String password) {
		filter = new Filter();
		lastID_shingle = 0L;
		lastID_shingling = 0L;
		try {
			connect(driver, url, user, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long get_lastID_shingle() {
		return lastID_shingle;
	}
	
	public void set_lastID_shingle(long lastid) {
		this.lastID_shingle = lastid;
	}
	
	public long get_lastID_shingling() {
		return lastID_shingling;
	}
	
	public void set_lastID_shingling(long lastid) {
		this.lastID_shingling = lastid;
	}
	
	/** 
	 * 
	 * @param path: 
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
	  
	/** 
	 * 
	 * @param path: 
	 * @throws SQLException 
	 */
	public void createTable(String table, String sql) throws SQLException  
	{    
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("drop table if exists " + table + ";");// 
		stmt.executeUpdate(sql);// 
		stmt.close() ;
	}  
	  
	/** 
	 * 
	 * @param table:	list:
	 * @throws UnsupportedEncodingException SQLException 
	 */
	public void insert(String table, List<String> list) throws UnsupportedEncodingException, SQLException  
	{  
		String sql = "insert into "+table+" values(?,?);";  
		PreparedStatement pst = conn.prepareStatement(sql);
		Integer count = new Integer(0);
		for(String str : list) {
			count++;
			pst.setString(1, count.toString());
			pst.setString(2, str);
			pst.addBatch();
		}
		pst.executeBatch();
		pst.clearBatch();
		pst.close();
	}
	
	/** 
	 * 
	 * @param table:	list:
	 * @throws UnsupportedEncodingException SQLException 
	 */
	public void insert_news(String table, long ID, String title, String text, long ID2, boolean isdup, boolean isdust, String flag, boolean iscd, Long signal) throws UnsupportedEncodingException, SQLException  
	{  
		String sql = "insert into "+table+" values(?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement pst = conn.prepareStatement(sql);

		pst.setLong(1, ID);
		pst.setString(2, text);
		pst.setString(3, title);
		pst.setLong(4, ID2);
		pst.setBoolean(5, isdup);
		pst.setBoolean(6, isdust);

		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		Date da = new Date(System.currentTimeMillis());
		pst.setString(7, formatter.format(da));
		pst.setString(8, flag);
		pst.setBoolean(9, iscd);
		pst.setString(10, signal.toString());
		
		
		pst.addBatch();
		pst.executeBatch();
		pst.clearBatch();
		pst.close();
	}
	
	/** 
	 * 
	 * @param table:	list:
	 * @throws UnsupportedEncodingException SQLException 
	 */
	public void insert_match(String table, String ID, Set<String> set) throws UnsupportedEncodingException, SQLException  
	{  
		String sql = "insert into "+table+" values(?,?,?,?);";  
		PreparedStatement pst = conn.prepareStatement(sql);
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()){
			pst.setString(1, ID);
			String temp_str = (String)iter.next();
			String str[] = temp_str.split("&##&");
			pst.setString(2, str[1]);
			pst.setString(3, str[2]);
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date da = new Date(System.currentTimeMillis());
			pst.setString(4, formatter.format(da));
			pst.addBatch();
		}
		pst.executeBatch();
		pst.clearBatch();
		pst.close();
	}
	
	public void insert_bayes_train(String table, Map<String, Double> map) throws UnsupportedEncodingException, SQLException  
	{  
		String sql = "insert into "+table+"(word, val, time) values (?,?,?);";  
		PreparedStatement pst = conn.prepareStatement(sql);
		Iterator<Entry<String, Double>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, Double> entry = (Map.Entry<String, Double>) iter.next();
			String word = (String)entry.getKey();
			Double val_D = (Double)entry.getValue();
			double val = Double.valueOf(val_D.toString());
			pst.setString(1, word);
			pst.setDouble(2, val);
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date da = new Date(System.currentTimeMillis());
			pst.setString(3, formatter.format(da));
			pst.addBatch();
		}
		pst.executeBatch();
		pst.clearBatch();
		pst.close();
	}
	
	/** 
	 * 
	 * @param table:
	 * @throws SQLException UnsupportedEncodingException 
	 * @return 
	 */
	public List<String> query(String table) throws SQLException, UnsupportedEncodingException {
		List<String> list = new ArrayList<String>();
		try {
			String query = "select * from " + table;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getString("sentence"));
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}  
	
	public Map<String, Double> query_bayes(String table) throws SQLException, UnsupportedEncodingException {
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			String query = "SELECT * FROM " + table + " ORDER BY time ASC";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String word = rs.getString("word");
				Double val = Double.valueOf(rs.getDouble("val"));
				map.put(word, val);
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	} 
	
	public List<String> query_signal(String table) throws SQLException, UnsupportedEncodingException {
		List<String> list = new ArrayList<String>();
		try {
			String query = "SELECT * FROM " + table + " WHERE ID>0 ORDER BY time ASC";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ID = rs.getString("ID");
				String signal = rs.getString("signal");
				String temp = signal +"##"+ ID;
				list.add(temp);
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	} 
	
	public List<String> query_shingle(String table) throws SQLException, UnsupportedEncodingException {
		List<String> list = new ArrayList<String>();
		try {
			String query = "SELECT * FROM " + table + " WHERE ID>0";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ID = rs.getString("ID");
				String temp = ID + "&##&" + rs.getString("shingle");
				list.add(temp);
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	} 
	
	public List<String> query_shingling(String table) throws SQLException, UnsupportedEncodingException {
		List<String> list = new ArrayList<String>();
		try {
			String query = "SELECT * FROM " + table + " WHERE ID>0";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ID = rs.getString("ID");
				String temp = ID + "&##&" + rs.getString("wordnum") + "&##&" + rs.getString("shingling");
				list.add(temp);
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	} 
	
	public Set<String> query_word_general(String table){
		Set<String> set = new HashSet<String>();
		try {
			String query = "SELECT * FROM " + table;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String word = rs.getString("word");
				word = filter.ToDBC(word);
				word = word.toLowerCase();
				boolean isGeneral = word.matches("[A-Za-z0-9\u4E00-\u9FA5]+");
				if(isGeneral) {
					if(!set.contains(word)) {
						set.add(word);
					}
				}
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return set;
	} 

	public Set<String> query_word_special(String table) throws SQLException, UnsupportedEncodingException {
		Set<String> set = new HashSet<String>();
		try {
			String query = "SELECT * FROM " + table;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String word = rs.getString("word");
				word = filter.ToDBC(word);
				word = word.toLowerCase();
				boolean isGeneral = word.matches("[A-Za-z0-9\u4E00-\u9FA5]+");
				if(!isGeneral) {
					if(!set.contains(word)) {
						set.add(word);
					}
				}
			}

			stmt.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return set;
	} 
	
	/** 
	 * 
	 * @throws SQLException 	  
	 */  
	public void close() throws SQLException  
	{  
		conn.close();
	}    
	
	public static void main(String[] args){
		Mysql mysql = new Mysql("com.mysql.jdbc.Driver", "jdbc:mysql://10.15.107.75:3306/ncps", "root", "111111");
		try{
			List<String> res = mysql.query("news_sentifilter_dust");
			for(String s : res)
				System.out.println(s);
		}catch(SQLException e){
			e.printStackTrace();
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
}  
