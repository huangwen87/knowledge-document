package com.gw.grammar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExtraction {


	public static void main(String[] args) {
		
		String aa = "\"2014-11-24 09:28:57 625\" \"LVT11014221\" " +
				"\"117.158.129.230\" \"114.80.170.174:7000\" \"libNews2.so-NewsMarket\"" +
				" \"OUT:8837860-8-752423\" \"0000000000010000#0000000000010001#0000000000010006#0000000000010002#0000000300020008#;20141124092849;;1;5;1,2,3,8,13,14,16,30;1-100;\"" +
				" \"RDTYPE_CALUNIT\" \"127\" \"690\" \"Raw_Field\""; 
//		String[] str = aa.split(" ");
//		for(String s : str){
//			System.out.println("s: " + s);
//		}
		
		String regular = "(.+?)(OUT[^\\s]*)";
		
		Pattern p = Pattern.compile(regular); 
        Matcher m = p.matcher(aa); 
        if (m.find()) { 
                String s0 = m.group(); 
                String s1 = m.group(1); 
                String s2 = m.group(2);
                //System.out.println(s0); 
                //System.out.println(s1); 
                //System.out.println(s2); 
                System.out.println("匹配到的字符串： " + s1 + s2);
        }else{
        	 System.out.println("----fail-----"); 
        }
       

	}

}
