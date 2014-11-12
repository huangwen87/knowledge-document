package com.gw.ncps.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;



/**
 * 文件名称:  Utils.java  
 * 文件功能:  工具类
 * 更改历史: 
 *    时间          用户       说明  
 *    2008-10-21   liqi      created
 */
public class Utils {
	
	/**
	 * 
	 * 功能说明：判断系统运行的平台
	 * 参数及返回值: 
	 * @return
	 */
	public static boolean isWindowsPlatform() {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			return true;
		}

		return false;
	}
	
	/**
	 * 
	 * 功能说明：获取IP地址
	 * 参数及返回值:
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	
	
	private static String DATE_PATTERN = "yyyyMMdd";
	private static String DATE_PATTERN_1 = "yyyy-MM-dd";
	private static String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static String TIME_PATTERNALL = "yyyyMMddHHmmss";
	
	/**
	 * 
	 * 功能说明：获取当前时间的前一天时间，并返回字符串。
	 * 参数及返回值: 
	 * @return
	 * @throws BmException
	 */
	public static String convertPreviousDate2String( Date date ) {
		if ( date == null ) {
			date = new Date();
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add( Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat( DATE_PATTERN_1 );
		
		return format.format( calendar.getTime() );
	}
	
	/**
	 * 
	 * 功能说明：将日期格式的字符串转换成时间格式的字符串。
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static String convertDate2Time( String date , String type ) {
		if ( date == null || date.length() == 0 ) {
			return null;
		}
		
		if( "startTime".equals( type ) ) {
			date = date + " 00:00:00";
		} else {
			date = date +" 23:59:59";
		}
		
		return date;
	}
	
	/**
	 * 
	 * 功能说明：将日期格式的字符串转换成时间格式的字符串。
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static String convertDate2Time( String date , int hour, String type ) {
		if ( date == null || date.length() == 0 ) {
			return null;
		}
		
		if( "startTime".equals( type ) ) {
			if( hour == 0 ) {
				date = date + " 00:00:00";
			} else {
				date = date + " " + hour + ":00:00";
			}
		} else {
			if( hour == 0 ) {
				date = date +" 23:59:59";
			} else {
				date = date + " " + hour + ":59:59";
			}
		}
		
		return date;
	}
	/**
	 * 
	 * 功能说明：将日期格式的字符串转换成时间格式的字符串。
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static String convertDate2TimeString( Date date , String type ) {
		if( date == null ) {
			date = new Date();
		}
		
		String dateString = convertDate2String(date, DATE_PATTERN_1);
		if( "startTime".equals( type ) ) {
			dateString = dateString + " 00:00:00";
		} else if ("endTime".equals( type ) ) {
			dateString = dateString +" 23:59:59";
		} else {
			return dateString;
		}
		
		return dateString;
	}
	
	
	/**
	 * 
	 * 功能说明：将时间转换成字符串格式。
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static String convertTime2String( Date date ) {
		SimpleDateFormat format = new SimpleDateFormat( TIME_PATTERN );
		return format.format(date);
	}
	
	/**
	 * 
	 * 功能说明： 将日期字符串转换成日期。
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static Date convertString2Time( String time ) {
		SimpleDateFormat format = new SimpleDateFormat( TIME_PATTERN );
		try {
			return format.parse( time );
		} catch (ParseException e) {
			return new Date();
		}
	}
	/**
	 * 
	 * 功能说明：将日期转换成字符串格式。
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static String convertDate2String( Date date ) {
		SimpleDateFormat format = new SimpleDateFormat( DATE_PATTERN_1 );
		return format.format(date);
	}
	
	/**
	 * 
	 * 功能说明：将日期转换成字符串格式。
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static String convertDate2String( Date date, String datePattern ) {
		SimpleDateFormat format = new SimpleDateFormat( datePattern );
		return format.format(date);
	}
	
	/**
	 * 
	 * 功能说明： 将日期字符串转换成日期。
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static Date convertString2Date( String date ) {
		SimpleDateFormat format = new SimpleDateFormat( DATE_PATTERN );
		try {
			return format.parse( date );
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	
	/**
	 * 
	 * 功能说明：将日期串转换成日期格式
	 * 参数及返回值: 
	 * @return
	 * @throws BmException
	 */
	public static Date convertString2Date( String date, String datePattern ) {
		if ( date == null ) {
			return new Date();
		}
		
		SimpleDateFormat format = new SimpleDateFormat( datePattern );
		try {
			return format.parse( date );
		} catch (ParseException e) {
			return new Date();
		}
	}
	/**
	 * 
	 * 功能说明：计算两个时间的差,精确到分
	 * 参数及返回值: 
	 * @return
	 * @throws BmException
	 */
	public static int minutesBetween( Date startDate, Date endDate ) {
		if ( startDate == null || endDate == null ) {
			return -1;
		}
		long longTime = endDate.getTime() - startDate.getTime();
			return (int) ( ( longTime/1000 ) / 60 ); 
	}
	
	/**
	 * 
	 * 功能说明：计算两个时间的差
	 * 参数及返回值: 
	 * @return
	 * @throws BmException
	 */
	public static int daysBetween( String beforeDate, String afterDate, String datePattern ) {
		if ( beforeDate == null || afterDate == null ) {
			return -1;
		}
		
		Date startDate = convertString2Date(beforeDate, datePattern);
		Date endDate = convertString2Date(afterDate, datePattern);
		
		long longTime = endDate.getTime() - startDate.getTime();
		
		return (int)( ( ( longTime/1000 ) / 3600 ) /24 ); 
	}
	
	/**
	 * 
	 * 功能说明：计算两个时间的差
	 * 参数及返回值: 
	 * @return
	 * @throws BmException
	 */
	public static int yearsBetween( String beforeDate, String afterDate, String datePattern ) {
		if ( beforeDate == null || afterDate == null ) {
			return -1;
		}
		
		Date startDate = convertString2Date(beforeDate, datePattern);
		Date endDate = convertString2Date(afterDate, datePattern);
		
		long longTime = endDate.getTime() - startDate.getTime();
		
		return (int)( ( ( ( longTime/1000 ) / 3600 ) /24 ) / 365 ); 
	}
	
	/**
	 * 功能说明：将日期转换成字符串 
	 * 参数及返回值:
	 * @param date
	 * @return
	 */
	public static String converDate2String2(Date date){
		SimpleDateFormat format = new SimpleDateFormat( TIME_PATTERNALL );
		return format.format(date);
	}
	
	/**
	 * 
	 * @功能说明：将时间转换成****年**月**日 00:00:00格式
	 * @参数及返回值：
	 * @param date
	 * @return
	 */
	public static String converDate2ChinaString(Date date){
		SimpleDateFormat format1 = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG,Locale.CHINA);
		SimpleDateFormat format2 = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM,Locale.CHINA);
		return format1.format(date)+format2.format(date);
	}
	
	/**
	 * 
	 * 功能说明：将long格式化成字符串类型。例如：当digit=4时，1 -> 0001、10 -> 0010
	 * 参数及返回值:
	 * @param id
	 * @param digit
	 * @return
	 */
	public static String formatLong2String( long id, int digit ) {
		String str = String.valueOf( id );
		int length = digit - str.length();
		if ( length > 0 ) {
			StringBuffer buffer = new StringBuffer();
			for( int i = 0 ; i < length ; i++ ) {
				buffer.append(0);
			}
			return buffer.append(str).toString();
		} else {
			return str;
		}
	}
	
	/**
	 * 
	 * 功能说明：将字符串数字，进行自增，然后以字符串返回。 
	 * 参数及返回值:
	 * @return
	 */
	public static String increate( String id ) {
		long tempId = Long.parseLong( id );
		tempId++;
		return formatLong2String( tempId, id.length() );
	}
	
	/**
	 * 
	 * 功能说明：将长整型数组转换为字符串，格式如xx,xx,xx
	 * 参数及返回值: 
	 * @param cardIds
	 * @return
	 */
	public static String convertArray2String( String [] array) {
		if (array != null) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < array.length; i++) {
				buffer.append(array[i]);
				if (i < array.length - 1) {
					buffer.append(",");
				}
			}
			
			return buffer.toString();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @功能说明：时间大小比较
	 * @参数及返回值：
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean compareTime( String startTime , String endTime ) {
		StringBuffer b1 = new StringBuffer( startTime );
		StringBuffer b2 = new StringBuffer( endTime );
		
		int t1 = Integer.parseInt( b1.deleteCharAt( b1.indexOf("-") ).deleteCharAt( b1.indexOf("-") ).toString() );
		int t2 = Integer.parseInt( b2.deleteCharAt( b2.indexOf("-") ).deleteCharAt( b2.indexOf("-") ).toString() );
		
		if ( t1 <= t2 ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * 功能说明：设置图片的高度
	 * 参数及返回值: 
	 * @return
	 * @throws BmException
	 */
	public static int getImageHeight( int size, int defaultValue ) {
		
		if ( size == 1 ) {
			return defaultValue;
		} else if ( size > 1 && size <= 20 ) {
			return 300;
		} else if ( size > 20 && size < 40 ) {
			return 500;
		} else if ( size > 40 && size < 60 ) {
			return 600;
		} else {
			return 800;
		}
	}
	
	public static boolean isNotNull(String str){
		if(null != str && !"".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	
	private static char[] chars = {
	   '0','1','2','3','4','5','6','7','8','9',
	   'a','b','c','d','e','f','g','h','i','j','k','m','n','p','r','s','t','u','v','w','x','y','z',
	   'A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'
	};

	
	public static String generateRandomChars( int length ) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for( int i = 0; i < length; i++ ) {
			buffer.append( chars[ random.nextInt( chars.length ) ] );
		}
		
		return buffer.toString();
	}
	
	public static String generateRandomChars() {
		return generateRandomChars( 18 );
	}
	
	/**
	 * 
	 * 功能说明：从http头部检测是否是IE浏览器。
	 * 参数及返回值: 
	 * @return
	 * @throws
	 */
	public static boolean isIE( HttpServletRequest request ) {
		String userAgent = request.getHeader( "User-Agent" );
		if ( userAgent.indexOf( "MSIE" ) != -1 ) {
			return true;
		}
		
		return false;
	}
	
	 /**
	  * 判断是否润年
	  * 
	  * @param ddate
	  * @return
	  */
	 public static boolean isLeapYear(int year) {
		 if(((year/4 == 0)&&(year/100!=0))||(year/400==0))   
			 return true;
		 return false;
	 }
	 
	 /**
	  * 
	  * @功能说明：获取某年某月的最后一天日期
	  * @参数及返回值：
	  * @param year
	  * @param month
	  * @return
	  */
	 public static int getLastDay(int year,int month){
		 int days = 0;
		 if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
			 days = 31;
		 }else if(month==4||month==6||month==9||month==11){
			 days = 30;
		 }else if(month==2){
			 if(isLeapYear(year)){
				 days = 29;
			 }else{
				 days = 28;
			 }
		 }
		 return days;
	 }
	public static void main(String[] args) {
	}
	
	public static String convertTimeString2DateString( String time ) {
		if ( time == null ) {
			return null;
		}
		
		int index = time.indexOf( " " );
		
		if ( index != -1 ) {
			time = time.substring( 0, index );
		}
		
		return time;
	}
	
	
	/** 
     * 验证注册信息是否符合编码规则 
     * @param regEX 验证规则      
     * @param rptCode 注册信息      
     * @return 验证结果，验证通过返回true，失败返回false 
     */ 
    public static boolean isRegularRptCode(String regEx,String rptCode) { 
    	if(null != rptCode &&!"".equals(rptCode)){
	        Pattern p1 = Pattern.compile(regEx);        
	        Matcher m1 = p1.matcher(rptCode);       
	        boolean rs1 = m1.matches();
	        return rs1; 
    	}else{
    		return false;
    	}
    }

	public static boolean isValidFile(String imageName) {
		// TODO Auto-generated method stub
		String suffix = imageName.substring(imageName.lastIndexOf(".")); // 得到上传文件的扩展名
        if(!suffix.equalsIgnoreCase(".jpg") && !suffix.equalsIgnoreCase(".bmp")&& 
        		!suffix.equalsIgnoreCase(".rar")&&!suffix.equalsIgnoreCase(".gif") )
		{
			return false;
		}
       
        return true;
	} 
	
	public static Timestamp getTime(String date)
	{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp t = null;
		try {
			t = new Timestamp (sf.parse(date).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	
}


