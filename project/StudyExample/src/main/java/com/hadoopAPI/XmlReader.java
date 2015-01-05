package com.hadoopAPI;

import org.apache.hadoop.conf.Configuration;

/**
 * xml ��ȡ����   ��Ҫ��linux�����У����ؼ��ز������ã�
 * @author darwen
 * 2013-12-10  ����4:45:39
 */
public class XmlReader {


	public static void main(String[] args) {
         Configuration conf = new Configuration();
         conf.addResource("myConfiguration.xml");  
         System.out.println("flower name is "+ conf.get("flower"));
         System.out.println("flower color is "+ conf.get("color"));
         System.out.println("blossomed ? "+ conf.get("blossom"));
	}

}
