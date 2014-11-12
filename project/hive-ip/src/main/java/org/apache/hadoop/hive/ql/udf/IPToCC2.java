package org.apache.hadoop.hive.ql.udf;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.hadoop.hive.ql.exec.UDF;

import util.Geoip2RegionName;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class IPToCC2 extends UDF{
	
	
	public static String evaluate(String str) throws UnknownHostException, IOException, GeoIp2Exception {
	   try{  
			File database = new File("GeoLite2-City.mmdb"); 
			DatabaseReader reader = new DatabaseReader.Builder(database).build();
			CityResponse response = reader.city(InetAddress.getByName(str));
			String region = response.getCity().getName();
			String province = response.getMostSpecificSubdivision().getName();
			if(region !=null && province != null)
               return Geoip2RegionName.getCode(province);
			else
			   return "10099";
		}catch(Exception e){  
            e.printStackTrace();
            return "10099";
        }  
	}
	

	public static void main(String[] args) throws UnknownHostException, IOException, GeoIp2Exception {
		String str = evaluate("124.89.88.149");
      	System.out.println(str);
	}

}
