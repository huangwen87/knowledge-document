package com.totalname;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

import kevin.zhang.Ictclas;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Total {
	
	private static Ictclas ictclas = new Ictclas();	
	
	public static class MapperClass extends Mapper<Object, Text, Text, IntWritable>{
		
		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			Path[] caches = DistributedCache.getLocalCacheFiles(context
					.getConfiguration());
			if (caches == null || caches.length <= 0) {
				System.out.println("No DistributedCache docID File!");
				System.exit(1);
			}
			for(int i = 0; i < caches.length; i++){
				System.out.println("caches: " + caches[i].toString());
				File file = new File(caches[i].toString());
				if(file.isFile() && i == 0){
					ictclas.loadDic(caches[i].toString());
				}else if(i == 2){
					ictclas.init(caches[i].toString());
				}
				else
				{
					System.out.println("文件不对");
				}
			}
			
			
		}

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
     
		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {			
			String line = value.toString();
			String ictclasResult = ictclas.clasSentence(line);
			StringTokenizer itor = new StringTokenizer(ictclasResult);
			while(itor.hasMoreTokens()){
				String str = itor.nextToken().toString();
				int index = str.indexOf("/");
				word.set(str.substring(0, index));
				context.write(word, one);
			}
			
		}
		
	}
	
 
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
		    Configuration conf = new Configuration();
	        conf.set("mapred.job.tracker", "10.15.107.74:9001");
	        String[] otherArgs = new GenericOptionsParser(conf, args)
	                .getRemainingArgs();
	        if (otherArgs.length != 2) {
	            System.err.println("Usage: TotalName <in> <out>");
	            System.exit(2);
	        }
	        
	        String userdict_uri = "hdfs://10.15.107.74/user/hadoop/plugins/common/userdict.txt";
	        Path userdict_conf = new Path(userdict_uri);
	        DistributedCache.addCacheFile(userdict_conf.toUri(), conf); 
	        
	        String lib_uri = "hdfs://10.15.107.74/user/hadoop/plugins/common/libNLPIR_JNI.so";
	        Path lib_conf = new Path(lib_uri);
	        DistributedCache.addCacheFile(lib_conf.toUri(), conf); 
	        
	        String data_uri = "hdfs://10.15.107.74/user/hadoop/plugins/common/";
	        Path data_conf = new Path(data_uri);
	        DistributedCache.addCacheFile(data_conf.toUri(), conf); 
	        
	        DistributedCache.createSymlink(conf);
	        
	        
	        
	        Job job = new Job(conf, "TotalName");
	        job.setJarByClass(Total.class);
	        job.setMapperClass(MapperClass.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(IntWritable.class);
	        
	        job.setNumReduceTasks(0);
	        
	        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
	        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
//	        FileInputFormat.addInputPath(job, new Path("hdfs://10.15.107.74/user/hadoop/Total_in/"));
//	        FileOutputFormat.setOutputPath(job, new Path("/user/hadoop/Total_out/"));
	        System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}
