package sentiment.prediction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.log4j.Logger;

import sentiment.configure.Configure;

import db.operation.RDBMSGetData;



public class InitialFromDb {
	private static Logger log = Logger.getLogger(InitialFromDb.class);
	private static RDBMSGetData rgd;
	private static ResultSet result = null;
	private static String features_sheet;
	private static String name_sheet;
	private static String seeds_sheet;
	private static String total_sheet;
	
	public static void init(String[] sheets, Configure m_configure){
		rgd = new RDBMSGetData(m_configure);
		features_sheet = sheets[0];
		name_sheet = sheets[1];
		seeds_sheet = sheets[2];
		total_sheet = sheets[3];
	}
	
	public static void getConnection(){
		rgd.getConnection();
	}
	
	public static void loadFeatures(HashMap<String, String> features){
		log.info("Initial features");
		//result = rgd.getData("SELECT word, category FROM sentiment_classify.features;");
		result = rgd.getData("SELECT word, category FROM " + features_sheet + ";");
		try{
			while(result.next()){
				features.put(result.getString(1), result.getString(2));
			}
			result.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		result = null;
	}
	
	public static void loadNegSeeds(HashSet<String> neg_features){
		log.info("Initial negative seeds");
		result = rgd.getData("SELECT word FROM " + seeds_sheet + " WHERE category = 'negative';");
		try{
			while(result.next()){
				neg_features.add(result.getString(1));
			}
			result.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		result = null;
	}
	
	public static void loadPosSeeds(HashSet<String> pos_features){
		log.info("Initial positive seeds");
		//result = rgd.getData("SELECT word FROM ncps.news_sentipredict_seeds WHERE category = 'positive';");
		result = rgd.getData("SELECT word FROM " + seeds_sheet + " WHERE category = 'positive';");
		try{
			while(result.next()){
				pos_features.add(result.getString(1));
			}
			result.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		result = null;
	}
	
	public static void loadFeaturesDT(Map<String, Map<String, Double>> featuresDT){
		log.info("Initial total statics");
		for(int i = 0; i < 2; i++){
			if(0 == i)
				result = rgd.getData("SELECT word, counts FROM " + total_sheet + " WHERE category = 'positive';");
			else
				result = rgd.getData("SELECT word, counts FROM " + total_sheet + " WHERE category = 'negative';");
			HashMap<String, Double> map = new HashMap<String, Double>();
			try{
				while(result.next()){
					map.put(result.getString(1), (double)result.getInt(2));
				}
				result.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			if(0 == i)
				featuresDT.put("positive", map);
			else
				featuresDT.put("negative", map);
			result = null;
			map = null;
		}
	}
	
	public static void loadLabels(Map<String, Integer> labels){
		log.info("Initial labels");
		result = rgd.getData("SELECT name, value FROM " + name_sheet + ";");
		try{
			while(result.next()){
				labels.put(result.getString(1), result.getInt(2));
			}
			result.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		result = null;
	}
	
	public static void loadFileNum(Map<String, Integer> fileNum){
		log.info("Initial file numbers");
		result = rgd.getData("SELECT word, counts FROM " + total_sheet + " WHERE category = 'total';");
		try{
			while(result.next()){
				fileNum.put(result.getString(1), result.getInt(2));
			}
			result.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		result = null;
	}
	
	public static void finish(){
		try{
			rgd.closeDB();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
