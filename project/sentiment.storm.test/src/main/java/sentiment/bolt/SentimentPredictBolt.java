package sentiment.bolt;

import java.util.Map;

import classify.protobuf.InnerUsing;

//import com.gw.protobuf.NewsFilter.NewsFilterDust;
import com.gw.protobuf.NewsSentiResult.NewsSentiment;

import sentiment.configure.Configure;
import sentiment.show.EmotionResult;
import sentiment.show.InterpretAsString;
import backtype.storm.task.TopologyContext;
//import sentiment.util.CommonUsing;
//import sentiment.util.NewsData;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SentimentPredictBolt extends BaseBasicBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8127231062430877229L;
	
	private static InterpretAsString ias;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map conf, TopologyContext context){
		Configure.getConfigure().init((String)conf.get("ConfPath"));
		ias = new InterpretAsString(Configure.getConfigure());
	}

	public void execute(Tuple input, BasicOutputCollector collector) {
		InnerUsing.InnerNews newsData = (InnerUsing.InnerNews)input.getValue(0);
		if(newsData.getIsFiltered() == false){
			long startTime = System.nanoTime();
			EmotionResult res = null;
			if(newsData.getContent().length() > 0)
				res = ias.explain(newsData.getTitle(), newsData.getContent());
			long endTime=System.nanoTime();
			NewsSentiment.Builder sentiResult = NewsSentiment.newBuilder();
			sentiResult.setNewsCode(newsData.getNewsCode());
			if(null != res){
				sentiResult.setSentiment(res.getResult());
				sentiResult.setNegRate(res.getNeg());
				sentiResult.setPosRate(res.getPos());
			}else{
				sentiResult.setSentiment(0);
				sentiResult.setNegRate(0.0);
				sentiResult.setPosRate(0.0);
			}
			sentiResult.setTitle(newsData.getTitle());
			sentiResult.setText(newsData.getContent());
			sentiResult.setProcessTime(endTime - startTime);
			sentiResult.setResult(newsData.getResult());
			sentiResult.addAllClassNameList(newsData.getClassNameListList());
			sentiResult.addAllCompanyNames(newsData.getCompanyNamesList());
			sentiResult.setAccessTime(newsData.getAccessTime());
			sentiResult.setIsFiltered(false);
			sentiResult.setTypeOf(newsData.getTypeof());
			sentiResult.setPublishdate(newsData.getPublishdate());
			collector.emit(new Values(sentiResult.build()));
		}else{
			NewsSentiment.Builder sentiResult = NewsSentiment.newBuilder();
			sentiResult.setNewsCode(newsData.getNewsCode());
			sentiResult.setTitle(newsData.getTitle());
			sentiResult.setText(newsData.getContent());
			sentiResult.setResult(newsData.getResult());
			sentiResult.addAllClassNameList(newsData.getClassNameListList());
			sentiResult.addAllCompanyNames(newsData.getCompanyNamesList());
			sentiResult.setAccessTime(newsData.getAccessTime());
			sentiResult.setIsFiltered(true);
			sentiResult.setTypeOf(newsData.getTypeof());
			sentiResult.setPublishdate(newsData.getPublishdate());
			collector.emit(new Values(sentiResult.build()));
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("sentiment"));
	}

}
