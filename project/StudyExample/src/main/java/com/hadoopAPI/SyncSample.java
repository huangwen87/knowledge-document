package com.hadoopAPI;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * ���д��hdfs  ͬ��   
 * @author darwen
 * 2013-12-9  ����4:55:17
 */
public class SyncSample {


	public static void main(String[] args) throws IOException {
		
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "10.15.107.64:9001");
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path("syncSample.txt"); //��Ĭ�ϵ���ӱ����û���
		System.out.println("�ļ��Ƿ���ڣ�"+ fs.isFile(path));
		FSDataOutputStream fsout = fs.create(path);
		fsout.writeUTF("Hello World");
		fsout.flush();
		fsout.sync();
		fsout.close();

	}

}
