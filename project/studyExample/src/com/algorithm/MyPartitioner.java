package com.algorithm;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * �Զ���ķ���---> �ض���Mapper�����
 * @author darwen
 * 2013-12-6  ����4:21:35
 */
public class MyPartitioner {
	
	
	/**
	 * mapper �е�key�ֲ�  �Ա����ʹ��
	 */
	public static class MapClass extends Mapper<Object, Text, Text, Text>{
		
		private Text keyinfo = new Text();
		private Text valueinfo = new Text();

		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			
             String[] values = value.toString().split(" ");
             int len = values.length;
             System.out.println("len: " + len);
             if(len > 3){
            	 keyinfo.set("long");
             }else if(len < 3){
            	 keyinfo.set("short");
             }else
            	 keyinfo.set("normal");
             valueinfo.set(value);
             context.write(keyinfo, valueinfo);
			
		}
		
	}
	
	
	public static class PartitionerBar extends Partitioner<Text, Text>{

		@Override
		public int getPartition(Text key, Text value, int numPartitions) {
			int result = 0;
			System.out.println("numPartitions:" +numPartitions);//ֵΪ3
			if(key.toString().equals("long")){
				result = 0 % numPartitions;
			}else if(key.toString().equals("short")){
				result = 1 % numPartitions;
			}else{
				result = 2 % numPartitions;
			}
			return result;
		}
		
	}
	
	public static class ReduceClass extends Reducer<Text, Text, Text, Text>{

		@Override
		protected void reduce(Text key, Iterable<Text> values,Context context)
				throws IOException, InterruptedException {
			Iterator<Text> itor = values.iterator();
			while(itor.hasNext()){
				context.write(key, itor.next());
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
	    conf.set("mapred.job.tracker", "10.15.107.64:9001");
        String[] ioArgs = args;
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: MyPartitioner <in> <out>");
            System.exit(2);
        }
        Job job = new Job(conf, "MyPartitioner");
        job.setJarByClass(MyPartitioner.class);
        // ����Map��combiner��Reduce������
        job.setMapperClass(MapClass.class);
        job.setPartitionerClass(PartitionerBar.class);
        job.setReducerClass(ReduceClass.class);
        job.setNumReduceTasks(3);
        
        // �����������
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        
        // ������������Ŀ¼
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
