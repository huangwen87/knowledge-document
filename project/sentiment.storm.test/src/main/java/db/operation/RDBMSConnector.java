package db.operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;

import sentiment.configure.Configure;

/**
 * Get connection to the specified database
 * Default database type : MYSQL
 * @author zhufeng
 *
 */
public class RDBMSConnector {
	
	private Connection con = null;
	private String dbUrl = null;
	private String dbClass = "com.mysql.jdbc.Driver";
	
	/**
	 * 
	 * @param sqlDBUrl
	 * 		The URL of the database
	 * @param sqlUser
	 * 		User name to log into database
	 * @param sqlPassword
	 * 		User password to log into database
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection(final String sqlDBUrl, final String sqlUser, final String sqlPassword) throws ClassNotFoundException, SQLException{
		StringBuilder builder = new StringBuilder();
		builder.append(sqlDBUrl).append("?user=").append(sqlUser).append("&password=").append(sqlPassword);
		dbUrl = builder.toString();
		Class.forName(dbClass);
		con = DriverManager.getConnection(dbUrl);
		return con;
	}
	
	/**
	 * Another way of initialize database
	 * @param path
	 * @return
	 * @throws DocumentException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection(Configure m_configure) throws ClassNotFoundException, SQLException{
		/*
		String filename = path + "Configure.xml";
		SAXReader reader = new SAXReader();
		Document doc = reader.read(filename);
		Element root = doc.getRootElement();
		Element mysql = root.element("mysql");
		StringBuilder builder = new StringBuilder();
		builder.append(mysql.elementText("JdbcUrl"));
		builder.append("&user=");
		builder.append(mysql.elementText("User"));
		builder.append("&password=");
		builder.append(mysql.elementText("Password"));
		dbClass = mysql.elementText("DriverClass");
		dbUrl = builder.toString();
		Class.forName(dbClass);
		con = DriverManager.getConnection(dbUrl);
		return con;
		*/
		
		//Configure configure = Configure.getConfigure();
		Class.forName(m_configure.dbDriver);
		con = DriverManager.getConnection(m_configure.dbUrl);
		return con;
	}
	
}
