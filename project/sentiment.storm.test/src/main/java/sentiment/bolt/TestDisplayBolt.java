package sentiment.bolt;

import com.gw.protobuf.NewsSentiResult.NewsSentiment;

//import sentiment.util.NewsData;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class TestDisplayBolt extends BaseBasicBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1767127038286125734L;

	public void execute(Tuple input, BasicOutputCollector collector) {
		NewsSentiment newsData = (NewsSentiment)input.getValue(0);
		System.out.println("_______________ID: " + newsData.getNewsCode());
		//System.out.println("____________Title: " + newsData.getTitle());
		//System.out.println("_____________Text: " + newsData.getText());
		System.out.println("____________Senti: " + newsData.getSentiment());
		System.out.println("______________Pos: " + newsData.getPosRate());
		System.out.println("______________Neg: " + newsData.getNegRate());
		System.out.println("_____________Time: " + newsData.getProcessTime());
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}

}
