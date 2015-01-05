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
 * 根据文件分块（而不是hdfs分块）
 * @author darwen
 * 2013-12-6  下午2:21:50
 */
public class AllFileSplitInputFormat extends FileInputFormat<LongWritable, Text>{

	@Override
	public RecordReader<LongWritable, Text> createRecordReader(
			InputSplit split, TaskAttemptContext context) throws IOException,
			InterruptedException {
		return new LineRecordReader();
	}

	
	/**
	 * 一个文件为其自身的分块
	 */
	@Override
	protected boolean isSplitable(JobContext context, Path filename) {
		return false;
	}
	
	

}
