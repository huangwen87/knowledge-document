package com.totalname;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

import kevin.zhang.Ictclas;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Total2 {
	
	private static Ictclas ictclas = new Ictclas();	
	
	public static class MapperClass extends Mapper<Object, Text, Text, IntWritable>{
		

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
     
		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {			
			String ictclasResult = ictclas.clasSentence(value.toString());
			StringTokenizer itor = new StringTokenizer(ictclasResult);
			while(itor.hasMoreTokens()){
				try{
					String str = itor.nextToken().trim().toString();
					int index = str.indexOf("/");
					if(index < 1)     //一些中文字符 比如"中文空格符"  去除这种不规则的字符干扰
						continue;
					word.set(str.substring(0, index));
					context.write(word, one);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	
	public static class ReducerClass extends Reducer<Text, IntWritable, Text, IntWritable>{

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			
			int sum = 0;
			for(IntWritable val : values){
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
			
		}
		
	}

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
		    Configuration conf = new Configuration(); 
	        Job job = new Job(conf, "TotalName2");
	        job.setJarByClass(Total2.class);
	        job.setMapperClass(MapperClass.class);
	        job.setReducerClass(ReducerClass.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(IntWritable.class);
	        
	        FileInputFormat.addInputPath(job, new Path("hdfs://linux:8020/user/hadoop/Total_in/"));
	        FileOutputFormat.setOutputPath(job, new Path("/user/hadoop/Total_out/"));
	        System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
