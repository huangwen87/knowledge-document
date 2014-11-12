package org.apache.hadoop.hive.ql.udf;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class CopyOfIPToCC2 {

	public static void main(String[] args) throws UnknownHostException, IOException, GeoIp2Exception {
		
		//File database = new File("D:\\workplace_eclipse_hadoop1.0.2\\hive-ip\\GeoLite2-City.mmdb");
		File database = new File("GeoLite2-City.mmdb");
		
		// This creates the DatabaseReader object, which should be reused across
		// lookups.
		DatabaseReader reader = new DatabaseReader.Builder(database).build();

		// Replace "city" with the appropriate method for your database, e.g.,
		// "country".
		CityResponse response = reader.city(InetAddress.getByName("58.17.79.45"));

		System.out.println(response.getCountry().getIsoCode()); // 'US'
		System.out.println(response.getCountry().getName()); // 'United States'
		System.out.println(response.getCountry().getNames().get("zh-CN")); // '美国'

		System.out.println(response.getMostSpecificSubdivision().getName()); // 'Minnesota'
		System.out.println(response.getMostSpecificSubdivision().getIsoCode()); // 'MN'

		System.out.println(response.getCity().getName()); // 'Minneapolis'

		System.out.println(response.getPostal().getCode()); // '55455'

		System.out.println(response.getLocation().getLatitude()); // 44.9733
		System.out.println(response.getLocation().getLongitude()); // -93.2323


	}

}
