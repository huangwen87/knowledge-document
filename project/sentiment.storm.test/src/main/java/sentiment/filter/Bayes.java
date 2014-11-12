package sentiment.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import sentiment.configure.Configure;
import sentiment.util.Mysql;
import sentiment.util.Filter;
import sentiment.util.Ictclas;

public class Bayes
{	
	// log
	static private Logger log = Logger.getLogger(Bayes.class.getName());
	private Timer timer = new Timer(true);
	
	private Ictclas ictclas;
	private Filter filter;
	
	private Double log_P_C;
	private Double log_P_D;
	private Double log_P_wi_C;
	private Double log_P_wi_D;
	
	private Map<String, Double> map_log_P_C;
	private Map<String, Double> map_log_P_D;
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	public Bayes(String driver, String url, String user, String password, int key, Configure m_configure) {
		
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		
		ictclas = new Ictclas(m_configure);
		filter = new Filter();
		
		log_P_C = new Double(0);
		log_P_D = new Double(0);
		log_P_wi_C = new Double(0);
		log_P_wi_D = new Double(0);
		
		map_log_P_C = new HashMap<String, Double>();
		map_log_P_D = new HashMap<String, Double>();
		
		if(0 == key){
			load("news_sentifilter_logpcd", "news_sentifilter_logpc", "news_sentifilter_logpd");
			timer.schedule(new TimerTask(){
	    		@Override
	    		public void run() {
	    			load("news_sentifilter_logpcd", "news_sentifilter_logpc", "news_sentifilter_logpd");
	    		}
	    	}, 86400000, 86400000);
		}else if(1 == key){
			load("news_sentiment_title_filter_logpcd", "news_sentiment_title_filter_logpc", "news_sentiment_title_filter_logpd");
			timer.schedule(new TimerTask(){
	    		@Override
	    		public void run() {
	    			load("news_sentiment_title_filter_logpcd", "news_sentiment_title_filter_logpc", "news_sentiment_title_filter_logpd");
	    		}
	    	}, 86400000, 86400000);
		}else if(2 == key){
			load("news_sentiment_weibo_filter_logpcd", "news_sentiment_weibo_filter_logpc", "news_sentiment_weibo_filter_logpd");
			timer.schedule(new TimerTask(){
	    		@Override
	    		public void run() {
	    			load("news_sentiment_weibo_filter_logpcd", "news_sentiment_weibo_filter_logpc", "news_sentiment_weibo_filter_logpd");
	    		}
	    	}, 86400000, 86400000);
		}
	}
	
	public void exit() {
		ictclas.exit();
	}
	
	public void load_log_P_CD(String table) {
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			Mysql mysql = new Mysql(driver, url, user, password);
			map = mysql.query_bayes(table);
			mysql.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		
		if(map.containsKey("log_P_C")) {
			log_P_C = map.get("log_P_C");
		}
		else {
			log.info("mysql 中没有 log_P_C!!!");
		}
		
		if(map.containsKey("log_P_D")) {
			log_P_D = map.get("log_P_D");
		}
		else {
			log.info("mysql 中没有 log_P_D!!!");
		}
		
		if(map.containsKey("log_P_wi_C")) {
			log_P_wi_C = map.get("log_P_wi_C");
		}
		else {
			log.info("mysql 中没有 log_P_wi_C!!!");
		}
		
		if(map.containsKey("log_P_wi_D")) {
			log_P_wi_D = map.get("log_P_wi_D");
		}
		else {
			log.info("mysql 中没有 log_P_wi_D!!!");
		}
	}
	
	public void load_map_log_P_C(String table) {
		try {
			Mysql mysql = new Mysql(driver, url, user, password);
			Map<String, Double> map = mysql.query_bayes(table);
			mysql.close();
			if(map.size()>0) {
				map_log_P_C.clear();
				map_log_P_C = map;
			}
			else {
				log.error("load_map_log_P_C has error!!!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
	}
	
	public void load_map_log_P_D(String table) {
		try {
			Mysql mysql = new Mysql(driver, url, user, password);
			Map<String, Double> map = mysql.query_bayes(table);
			mysql.close();
			if(map.size()>0) {
				map_log_P_D.clear();
				map_log_P_D = map;
			}
			else {
				log.error("load_map_log_P_D has error!!!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
		}
	}
	
	public void load(String table_log_P_CD, String table_map_log_P_C, String table_map_log_P_D) {
		long begin = System.currentTimeMillis();
		
		load_log_P_CD(table_log_P_CD);
		load_map_log_P_C(table_map_log_P_C);
		load_map_log_P_D(table_map_log_P_D);
		long end = System.currentTimeMillis();
		long dis = end - begin;
		log.info("load bayes data time(ms):	"+dis);
	}
	
	//true:dust   false:not dust
	public boolean is_dust(String str) {
		String line = filter.delHtml(str);
		String line_clas = ictclas.clasSentence(line);
		String line_filt = filter.filterByClas(line_clas);
		String words[] = line_filt.split(" ");
		
		Double P_C_W = log_P_C;
		Double P_D_W = log_P_D;
		
		if(words.length<=0) {
			return false;
		}
		
		for(int i=0;i<words.length;i++) {
			if(map_log_P_C.containsKey(words[i])) {
				P_C_W = P_C_W + map_log_P_C.get(words[i]);
			}
			else {
				P_C_W = P_C_W + log_P_wi_C;
			}
			
			if(map_log_P_D.containsKey(words[i])) {
				P_D_W = P_D_W + map_log_P_D.get(words[i]);
			}
			else {
				P_D_W = P_D_W + log_P_wi_D;
			}
		}
		
		if(P_D_W>P_C_W) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public String bayes_filter_sent(String s) {
		String res_str = "";
		if((s==null) || (s.length()<=0)) {
			return res_str;
		}
		
		String flag_str = "。";
		String str = s.replaceAll("[！？!?]", flag_str);
		String[] sents = str.split(flag_str);
		
		for(int i=0;i<sents.length;i++) {
			if(sents[i].length()<1) {
				continue;
			}
			
			boolean is_dust = is_dust(sents[i]);
			if(is_dust) {
				//rw.writeFile(dust_dir, sents[i]);
			}
			else {
				res_str = res_str + sents[i];
			}
		}

		return res_str;
	}

	public String del_html(String str) {
		if((str==null) || (str.length()==0)) {
			return "";
		}
		
		String line = filter.delHtml(str);
		
		if((line==null) || (line.length()==0)) {
			return "";
		}
		
		String line_regex_filter = filter.regex_filter(line);
		
		return line_regex_filter;
	}
	
	//true:dust   false:not dust
	public String del_dust(String str) {
		if((str==null) || (str.length()==0)) {
			return "";
		}
		
		String line_clas = ictclas.clasSentence(str);
		
		if((line_clas==null) || (line_clas.length()==0)) {
			return "";
		}
		
		String line_filt = filter.filterByClas(line_clas);
		
		if((line_filt==null) || (line_filt.length()==0)) {
			return "";
		}
		
		String words[] = line_filt.split(" ");
		
		Double P_C_W = log_P_C;
		Double P_D_W = log_P_D;
		
		for(int i=0;i<words.length;i++) {
			if(map_log_P_C.containsKey(words[i])) {
				P_C_W = P_C_W + map_log_P_C.get(words[i]);
			}
			else {
				P_C_W = P_C_W + log_P_wi_C;
			}
			
			if(map_log_P_D.containsKey(words[i])) {
				P_D_W = P_D_W + map_log_P_D.get(words[i]);
			}
			else {
				P_D_W = P_D_W + log_P_wi_D;
			}
		}

		if(P_D_W>P_C_W) {
			return "&##&dust&##&";
		}
		else{
			String res = "";
			for(int i=0;i<words.length;i++) {
				res = res + words[i] + " ";
			}
			return res;
		}
	}
	
	public static void main(String[] args){
		/*
		//String s = "安源煤业：第五届董事会第十四次会议决议公告公告日期：2013-07-25证券代码：600397 股票简称：安源煤业 编号：2013-021 安源煤业集团股份有限公司第五届董事会第十四次会议决议公告本公司董事会及全体董事保证本公告内容不存在任何虚假记载、误导性陈述或者重大遗漏，并对其内容的真实性、准确性和完整性承担个别及连带责任。安源煤业集团股份有限公司（以下简称“公司”）第五届董事会第十四次会议于 2013 年 7 月 18 日以传真或电子邮件方式通知，并于 2013 年 7 月 24 日上午9:00 以通讯方式召开。本次会议应到董事 9 人，实到董事 9 人。会议由公司董事长李良仕先生主持，公司监事列席了会议，符合《中华人民共和国公司法》、《公司章程》及相关法律法规的规定。会议认真讨论和审议了本次会议议程事项，对有关议案进行了书面记名投票表决。审议并通过了《安源煤业关于会计估计变更的议案》，其中 9 票赞成，0 票反对，0 票弃权。特此公告。 安源煤业集团股份有限公司董事会 二○一三年七月二十四日1";
		String s = "今天天气不错";
		Bayes bayes = new Bayes("com.mysql.jdbc.Driver", "jdbc:mysql://10.15.107.75:3306/ncps", "root", "111111", 0);
		//bayes.load("news_sentifilter_logpcd", "news_sentifilter_logpc", "news_sentifilter_logpd");
		System.out.println(bayes.bayes_filter_sent(s));
		*/
	}
}
