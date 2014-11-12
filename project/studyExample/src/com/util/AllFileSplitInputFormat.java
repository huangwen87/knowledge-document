package com.util;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

/**
 * �����ļ��ֿ飨������hdfs�ֿ飩
 * @author darwen
 * 2013-12-6  ����2:21:50
 */
public class AllFileSplitInputFormat extends FileInputFormat<LongWritable, Text>{

	@Override
	public RecordReader<LongWritable, Text> createRecordReader(
			InputSplit split, TaskAttemptContext context) throws IOException,
			InterruptedException {
		return new LineRecordReader();
	}

	
	/**
	 * һ���ļ�Ϊ������ķֿ�
	 */
	@Override
	protected boolean isSplitable(JobContext context, Path filename) {
		return false;
	}
	
	

}
