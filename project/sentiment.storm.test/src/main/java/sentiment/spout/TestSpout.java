package sentiment.spout;

import java.util.Map;

import sentiment.util.NewsData;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class TestSpout extends BaseRichSpout{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3834902265189896538L;
	
	private SpoutOutputCollector collector;

	
	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		this.collector = collector;
	}

	public void nextTuple() {
		NewsData newsData = new NewsData();
		newsData.setId(1);
		newsData.setTitle("股市大跌");
		newsData.setText("暴力执法");
		collector.emit(new Values(newsData));
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("news"));
	}

}
