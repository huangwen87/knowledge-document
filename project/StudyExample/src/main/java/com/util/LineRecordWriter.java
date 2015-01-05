package com.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 * Hadoop��MapReduce�ж��ļ����
 * ---description------------------------------------------
 *��  RecordWriter��һ��ʵ�֣����ڰ�<Key, Value>ת��Ϊһ���ı�;
 *  ��Hadoop�У��������ΪTextOutputFormat��һ��������ڣ�
 *  protected����Ȩ�ޣ������ͨ�����޷����ʡ���������ǰ�LineRecordWriter
 *  ��TextOutputFormat��ȡ��������Ϊһ�������Ĺ�����ʹ�á�
 * --------------------------------------------------------
 * @author darwen
 * 2013-12-12  ����4:23:52
 */
public class LineRecordWriter<K,V> extends RecordWriter<K, V>{
	
	private static String utf8 = "UTF-8";
	private static final byte[] newline;
	static{
		try{
			newline = "\n".getBytes(utf8);
		}catch(UnsupportedEncodingException e){
			throw new IllegalArgumentException("can't find " + utf8 +"encoding");
		}
	}
	
	protected DataOutputStream out;
	private final byte[] keyValueSeparator;
	
	public LineRecordWriter(DataOutputStream out, String keyValueSepatator){
		this.out = out;
		try{
			this.keyValueSeparator = keyValueSepatator.getBytes(utf8);
		}catch(UnsupportedEncodingException e){
			throw new IllegalArgumentException("can't find " + utf8 +"encoding");
		}
	}
	
	public LineRecordWriter(DataOutputStream out){
		this(out, "\t");
	}
	
	private void writeObject(Object o) throws IOException{
		if(o instanceof Text){
			Text to = (Text)o;
			out.write(to.getBytes(), 0, to.getLength());
		}else{
			out.write(o.toString().getBytes(utf8));
		}
	}
	
	public synchronized void write(K key, V value) throws IOException{
		boolean nullKey = key == null || key instanceof NullWritable;  
        boolean nullValue = value == null || value instanceof NullWritable;  
        if (nullKey && nullValue) {  
            return;  
        }  
        if (!nullKey) {  
            writeObject(key);  
        }  
        if (!(nullKey || nullValue)) {  
            out.write(keyValueSeparator);  
        }  
        if (!nullValue) {  
            writeObject(value);  
        }  
        out.write(newline);  
	}

	@Override
	public synchronized void close(TaskAttemptContext context) throws IOException,
			InterruptedException {
		out.close();
	}

}
