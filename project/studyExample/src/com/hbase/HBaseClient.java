package com.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class HBaseClient {

    private static Configuration conf = null;
    
    static{
    	 conf = HBaseConfiguration.create();
    }
    
    
    public static void createTable(String tablename, String[] cfs) throws Exception {
    	
    	HBaseAdmin admin = new HBaseAdmin(conf);
    	if(admin.tableExists(tablename)){
    		System.out.println("table has existed.");
    	}
    	else{
    		HTableDescriptor tableDesc = new HTableDescriptor(tablename);
    		for(int i =0; i < cfs.length; i++){
    			tableDesc.addFamily(new HColumnDescriptor(cfs[i]));
    		}
    		admin.createTable(tableDesc);
    		System.out.println("create table successfully.");
    	}
    }
    
    
	
	public static void main(String[] args) {
        try {
        	createTable("dsinfo", new String[]{"publish","mark"});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
