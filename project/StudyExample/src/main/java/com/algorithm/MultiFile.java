package com.algorithm;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.lib.MultipleOutputs;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * �������ļ�    ���в����������ļ�
 * @author darwen
 * 2013-12-4  ����2:40:31
 */
public class MultiFile extends Configured implements Tool{
	
	
	@SuppressWarnings("deprecation")
	public static class MapClass extends MapReduceBase 
	    implements Mapper<LongWritable, Text, NullWritable, Text>{
		
		private MultipleOutputs mos;
		private OutputCollector<NullWritable, Text> collector;
		
        public void configure(JobConf conf){
        	mos = new MultipleOutputs(conf);
        }		
		
		@Override
		public void map(LongWritable key, Text value,
				OutputCollector<NullWritable, Text> output, Reporter reporter)
				throws IOException {
			
		    String[] arr = value.toString().split(" ",-1);
		    String chrono = arr[0];
		    String geo    = arr[1];
		    
		    collector = mos.getCollector("chrono", reporter);
		    collector.collect(NullWritable.get(), new Text(chrono));
		    
		    collector = mos.getCollector("geo", reporter);
		    collector.collect(NullWritable.get(), new Text(geo));
		}
		
		public void close() throws IOException{
			mos.close();
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public int run(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "10.15.107.64:9001");
		JobConf job = new JobConf(conf, MultiFile.class);
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		FileInputFormat.setInputPaths(job, in);
		FileOutputFormat.setOutputPath(job, out);
		
		job.setJobName("MultiFile");
		job.setMapperClass(MapClass.class);
		job.setInputFormat(TextInputFormat.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(0);
		
		MultipleOutputs.addNamedOutput(job, 
				                       "chrono", 
				                       TextOutputFormat.class, 
				                       NullWritable.class, 
				                       Text.class);
		
		MultipleOutputs.addNamedOutput(job, 
				                       "geo", 
				                       TextOutputFormat.class, 
				                       NullWritable.class, 
				                       Text.class);
		JobClient.runJob(job);
		return 0;
	}
	
	
	public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new MultiFile(),args);
        System.exit(res);
	}

}
