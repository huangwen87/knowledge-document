package org.apache.hadoop.hive.ql.udf;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

/**
 * desc：  hive 放入lib方式（路径直接打入）
 * @author darwen
 * 2014-7-2 上午10:30:55
 */
public class CopyOfIPToCC extends UDF{
	
    private static LookupService cl = null;  
    private static String ipPattern = "\\d+\\.\\d+\\.\\d+\\.\\d+";  
    private static String ipNumPattern = "\\d+";  
      
    static LookupService getLS() throws IOException{  
        String dbfile = "GeoLiteCity.dat"; 
        if(cl == null)  
            //cl = new LookupService(dbfile, LookupService.GEOIP_MEMORY_CACHE);  
            cl = new LookupService(dbfile , LookupService.GEOIP_STANDARD);
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
            if(Al == null)
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
    	//String str = evaluate("114.43.181.143");
    	//String str = evaluate("60.177.89.13");
    	String str = evaluate("58.30.16.0");
    	//String str = evaluate("NULL");
    	System.out.println("str1: " + str);
    }
    
}
