package sentiment.topology;

//import sentiment.bolt.GoToCandidateMQBolt;
//import sentiment.bolt.GoToFilteredMQBolt;

import sentiment.bolt.GoToMQBolt;
import sentiment.bolt.SentimentFilterBolt;
import sentiment.bolt.SentimentPredictBolt;
//import sentiment.bolt.TestDisplayBolt;
import sentiment.spout.MQNewsSpout;
//import sentiment.spout.TestSpout;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

public class MainTopology {

	public static void main(String[] args){
		boolean local = false;
		try{
			TopologyBuilder topologyBuilder = new TopologyBuilder();
			//topologyBuilder.setSpout("TestSpout", new TestSpout(), 1);
			topologyBuilder.setSpout("MQNewsSpout", new MQNewsSpout(), 1);
			topologyBuilder.setBolt("SentimentFilter", new SentimentFilterBolt(), 1).shuffleGrouping("MQNewsSpout");
			topologyBuilder.setBolt("SentimentPredictBolt", new SentimentPredictBolt(), 1).shuffleGrouping("SentimentFilter");
			//topologyBuilder.setBolt("GoToFilteredMQBolt", new GoToFilteredMQBolt(), 1).shuffleGrouping("SentimentFilter");
			//topologyBuilder.setBolt("TestDisplayBolt", new TestDisplayBolt(), 1).shuffleGrouping("SentimentPredictBolt");
			
			topologyBuilder.setBolt("GoToMQBolt", new GoToMQBolt(), 1).shuffleGrouping("SentimentPredictBolt");
			//topologyBuilder.setBolt("GoToCandidateMQBolt", new GoToCandidateMQBolt(), 1).shuffleGrouping("SentimentPredictBolt");
			Config config = new Config();
			config.setDebug(true);
			config.put("ConfPath", args[1]);
			if(!local){
				config.setNumWorkers(2);
                StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
			}else{
				
				config.setMaxTaskParallelism(1);
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("SentimentPredictWangSource", config, topologyBuilder.createTopology());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
