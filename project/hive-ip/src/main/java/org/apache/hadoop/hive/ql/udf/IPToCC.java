package org.apache.hadoop.hive.ql.udf;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

/**
 * desc： web版本 打入资源
 * @author darwen
 * 2014-7-2 上午10:31:31
 */
public class IPToCC extends UDF{
	
    private static LookupService cl = null;  
    private static String ipPattern = "\\d+\\.\\d+\\.\\d+\\.\\d+";  
    private static String ipNumPattern = "\\d+";  
      
    static LookupService getLS() throws IOException{  
        String dbfile = "GeoLiteCity.dat"; 
//    	String[] path_candidate = new String[2];
//    	path_candidate[0] = IPToCC.class.getResource("/").getPath().substring(1).replaceAll("%20", " ");		//windows web path
//    	path_candidate[1] = IPToCC.class.getResource("/").getPath().replaceAll("%20", " ");                     //linux web path
//    	File file;
//    	String dbfile = "";
//    	for(int i = 0; i < 2; i++){
//    		file = new File(path_candidate[i]);
//    		if(file.exists())
//    		{
//    			dbfile = path_candidate[i];
//    			break;
//    		}
//    	}
        if(cl == null)  
            cl = new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);  
            //cl = new LookupService(dbfile + "GeoLiteCity.dat", LookupService.GEOIP_STANDARD);
        return cl;  
    }  
      
    /** 
     * @param str like "114.43.181.143" 
     * */  
      
    public static String evaluate(String str) {  
        try{  
            Location Al = null;  
            Matcher mIP = Pattern.compile(ipPattern).matcher(str);  
            Matcher mIPNum = Pattern.compile(ipNumPattern).matcher(str);  
            if(mIP.matches())  
                Al = getLS().getLocation(str);  
            else if(mIPNum.matches())  
                Al = getLS().getLocation(Long.parseLong(str));
            if(Al == null || Al.region == null)
            	return "10099";
            return myFormat(Al.region, Al.countryCode);
        }catch(Exception e){  
            e.printStackTrace();  
            if(cl != null)  
                cl.close();  
            return null;
        }  
    }
    
    /**
     * 格式成想要的结果
     * */
    public static String myFormat(String region, String countryCode){
    	String result = "";
    	try{
    		if(countryCode.equals("CN")){
    			result = Integer.parseInt(region) + 10000 + "";
    		}else{
    			result = "10099";
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return result;
    }
    

    public static void main(String[] args) {
    	String str = evaluate("124.89.88.149");
    	//String str = evaluate("124.89.88.149");
    	 //String str = evaluate("123.125.156.201");
    	//String str = evaluate("NULL");
    	System.out.println("str1: " + str);
    }
    
}
