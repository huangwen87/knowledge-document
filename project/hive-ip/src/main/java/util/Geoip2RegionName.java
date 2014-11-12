package util;

public class Geoip2RegionName {
	
	
	public static String getCode(String regionName){
		if(regionName == null)
			return "10099";
		String result = new String();
		try{
			if(regionName.contains("Anhui"))
				result = "10001";
			else if(regionName.contains("Zhejiang"))
				result = "10002";
			else if(regionName.contains("Jiangxi"))
				result = "10003";
			else if(regionName.contains("Jiangsu"))
				result = "10004";
			else if(regionName.contains("Jilin"))
				result = "10005";
			else if(regionName.contains("Qinghai"))
				result = "10006";
			else if(regionName.contains("Fujian"))
				result = "10007";
			else if(regionName.contains("Heilongjiang"))
				result = "10008";
			else if(regionName.contains("Henan"))
				result = "10009";
			else if(regionName.contains("Hebei"))
				result = "10010";
			else if(regionName.contains("Hunan"))
				result = "10011";
			else if(regionName.contains("Hubei"))
				result = "10012";
			else if(regionName.contains("Xinjiang"))
				result = "10013";
			else if(regionName.contains("Xizang"))
				result = "10014";
			else if(regionName.contains("Gansu"))
				result = "10015";
			else if(regionName.contains("Guangxi"))
				result = "10016";
			else if(regionName.contains("Guizhou"))
				result = "10018";
			else if(regionName.contains("Liaoning"))
				result = "10019";
			else if(regionName.contains("Nei Mongol"))
				result = "10020";
			else if(regionName.contains("Ningxia"))
				result = "10021";
			else if(regionName.contains("Beijing"))
				result = "10022";
			else if(regionName.contains("Shanghai"))
				result = "10023";
			else if(regionName.contains("Shanxi"))
				result = "10024";
			else if(regionName.contains("Shandong"))
				result = "10025";
			else if(regionName.contains("Shaanxi"))
				result = "10026";
			else if(regionName.contains("Tianjin"))
				result = "10028";
			else if(regionName.contains("Yunnan"))
				result = "10029";
			else if(regionName.contains("Guangdong"))
				result = "10030";
			else if(regionName.contains("Hainan"))
				result = "10031";
			else if(regionName.contains("Sichuan"))
				result = "10032";
			else if(regionName.contains("Chongqing"))
				result = "10033";
			else
				result = "10099";
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
