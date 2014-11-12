package sentiment.configure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Configure implements Serializable{

	/**
	 * 
	 */
	public String dbUrl;
	public String dbDriver;
	public String nlpirData;
	public String nlpirUsrDict;
	public String bayesfilterDriver;
	public String bayesfilterUrl;
	public String bayesfilterUser;
	public String bayesfilterPassword;
	public String mqSourceUrl;
	public String mqDestinationUrl;
	
	//private static final Configure m_configure = new Configure("/home/dyj/storm-cluster/storm-0.8.2/etc/");
	//private static final Configure m_configure = new Configure("/etc/sentiment/");
	private static final Configure m_configure = new Configure();
	
	public static Configure getConfigure(){
		return m_configure;
	}
	
	public Configure(){
		
	}
	
	public void init(String path){
		//String filename = path + "Configure.xml";
		String filename = path;
		SAXReader reader = new SAXReader();
		try{
			Document doc = reader.read(filename);
			Element root = doc.getRootElement();
			Element mysql = root.element("mysql");
			StringBuilder builder = new StringBuilder();
			builder.append(mysql.elementText("JdbcUrl"));
			builder.append("&user=");
			builder.append(mysql.elementText("User"));
			builder.append("&password=");
			builder.append(mysql.elementText("Password"));
			dbUrl = builder.toString();
			dbDriver = mysql.elementText("DriverClass");
			
			Element nlpir = root.element("nlpir");
			nlpirData = nlpir.elementText("DataPath");
			nlpirUsrDict = nlpir.elementText("UsrDictPath");
			
			Element bayesf = root.element("bayesfilter");
			bayesfilterDriver = bayesf.elementText("DriverClass");
			bayesfilterUrl = bayesf.elementText("JdbcUrl");
			bayesfilterUser = bayesf.elementText("User");
			bayesfilterPassword = bayesf.elementText("Password");
			
			Element mqsource = root.element("MQSource");
			mqSourceUrl = mqsource.elementText("Url");
			
			
			Element mqdestination = root.element("MQDestination");
			mqDestinationUrl = mqdestination.elementText("Url");
			
			
		}catch(DocumentException e){
			e.printStackTrace();
		}
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeBytes(this.dbUrl);
		out.writeBytes("++--++");
		out.writeBytes(this.dbDriver);
		out.writeBytes("++--++");
		out.writeBytes(this.bayesfilterDriver);
		out.writeBytes("++--++");
		out.writeBytes(this.bayesfilterPassword);
		out.writeBytes("++--++");
		out.writeBytes(this.bayesfilterUrl);
		out.writeBytes("++--++");
		out.writeBytes(this.bayesfilterUser);
		out.writeBytes("++--++");
		out.writeBytes(this.mqDestinationUrl);
		out.writeBytes("++--++");
		out.writeBytes(this.mqSourceUrl);
		out.writeBytes("++--++");
		out.writeBytes(this.nlpirData);
		out.writeBytes("++--++");
		out.writeBytes(this.nlpirUsrDict);
	}
	
	private void readObject(ObjectInputStream in) throws IOException{
		String tmp = in.readUTF();
		String[] parts = tmp.split("++--++");
		if(parts.length != 10)
			throw new RuntimeException("Deserialization Failed!");
		this.dbUrl = parts[0];
		this.dbDriver = parts[1];
		this.bayesfilterDriver = parts[2];
		this.bayesfilterPassword = parts[3];
		this.bayesfilterUrl = parts[4];
		this.bayesfilterUser = parts[5];
		this.mqDestinationUrl = parts[6];
		this.mqSourceUrl = parts[7];
		this.nlpirData = parts[8];
		this.nlpirUsrDict = parts[9];
	}
}
