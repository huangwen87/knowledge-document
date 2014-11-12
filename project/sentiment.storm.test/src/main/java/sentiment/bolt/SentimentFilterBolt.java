package sentiment.bolt;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sentiment.configure.Configure;
//import sentiment.configure.Configure;
import sentiment.filter.Bayes;
import sentiment.filter.English;
import sentiment.segmentation.factory.NLPIRFactory;
import sentiment.segmentation.factory.WordSegmentFactory;

import classify.protobuf.InnerUsing;
import classify.protobuf.PbNewsSentimentUntreated;

//import com.gw.protobuf.NewsFilter.NewsFilterDust;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

@SuppressWarnings("serial")
public class SentimentFilterBolt extends BaseBasicBolt{
	private static WordSegmentFactory wsfactory = null;
	private static Bayes bayes;
	private static Bayes bayesTitle;
	private static Bayes bayesWeibo;
	private static English eng;
	/*
	private static Configure configure = Configure.getConfigure();
	private static Bayes bayes  = new Bayes(configure.bayesfilterDriver, configure.bayesfilterUrl, configure.bayesfilterUser, configure.bayesfilterPassword, 0);
	private static Bayes bayesTitle = new Bayes(configure.bayesfilterDriver, configure.bayesfilterUrl, configure.bayesfilterUser, configure.bayesfilterPassword, 1);
	private static Bayes bayesWeibo = new Bayes(configure.bayesfilterDriver, configure.bayesfilterUrl, configure.bayesfilterUser, configure.bayesfilterPassword, 2);
	private static English eng = new English();
	*/
	/*
	private static String[] weiboFilter = {
		"旅游业",
		"银行业",
		"汽车制造业",
		"通信行业",
		"家电制造业",
		"石油行业",
		"航空业",
		"物流业",
		"半导体行业",
		"酒类制造业",
		"网络媒体业",
		"房地产业",
		"影视动漫业",
		"零售业",
		"保险业",
		"软件与信息技术服务业",
	};
	*/
	
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map conf, TopologyContext context){
		Configure.getConfigure().init((String)conf.get("ConfPath"));
		Configure configure = Configure.getConfigure();
		wsfactory = new NLPIRFactory(configure);
		bayes = wsfactory.MakeContentFilter();
		bayesTitle = wsfactory.MakeTitleFilter();
		bayesWeibo = wsfactory.MakeWeiboFilter();
		eng = new English();
	}
	
	public void execute(Tuple input, BasicOutputCollector collector) {
		
		PbNewsSentimentUntreated.NewsSentimentUntreated newsData = (PbNewsSentimentUntreated.NewsSentimentUntreated)input.getValue(0);
		/*
		PbNewsSentimentUntreated.NewsSentimentUntreated.Builder newsDataB = PbNewsSentimentUntreated.NewsSentimentUntreated.newBuilder();
		newsDataB.setTypeOf("11");
		newsDataB.addCompanyNames("石油行业");
		newsDataB.addCompanyNames("汽车制造业");
		newsDataB.setNewsCode("111111");
		newsDataB.setTitle("经典发言:1,不要再喝了,体检就脂肪肝,马上肝硬化,就完了;2,国庆了,不要再送礼...");
		PbNewsSentimentUntreated.NewsSentimentUntreated newsData = newsDataB.build();
		*/
		InnerUsing.InnerNews.Builder result  = InnerUsing.InnerNews.newBuilder();
		if(newsData.getNewsType().equals("CA")){
			result.setNewsCode(newsData.getNewsCode());
			result.setTitle(newsData.getTitle());
			result.setContent(newsData.getText());
			result.setResult("C");
			result.addAllClassNameList(newsData.getIndustryNamesList());
			result.addAllCompanyNames(newsData.getCompanyNamesList());
			result.setIsFiltered(false);
			result.setSource("C");
			result.setTypeof(newsData.getTypeOf());
			result.setPublishdate(newsData.getPublishdate());
			collector.emit(new Values(result.build()));
		}else if(newsData.getNewsType().equals("CB")){
			result.setNewsCode(newsData.getNewsCode());
			result.setTitle(newsData.getTitle());
			result.setContent(newsData.getText());
			result.setResult("C");
			result.addAllClassNameList(newsData.getIndustryNamesList());
			result.addAllCompanyNames(newsData.getCompanyNamesList());
			result.setIsFiltered(true);
			result.setSource("C");
			result.setTypeof(newsData.getTypeOf());
			result.setPublishdate(newsData.getPublishdate());
			collector.emit(new Values(result.build()));
		}else{
			if(newsData.getTypeOf().equals("1")){
				boolean isFiltered = true;
				if(!eng.isTotalEnglish(newsData.getText())){
					if(newsData.getTitle().length() < 36){
						String titlenodust = bayesTitle.bayes_filter_sent(newsData.getTitle());
						if(null != titlenodust && titlenodust.length() > 3){
							String nodust = bayes.bayes_filter_sent(newsData.getText());
							String noHtml = nodust.replaceAll("<[^>]+>", "");
							if(noHtml.length() < 100)
								nodust = null;
							Pattern p = Pattern.compile(".*?保险业.*?");
							Matcher m = p.matcher(newsData.getIndustryNamesList().toString());
							if(m.matches())
								nodust = null;
							if(null != nodust){
								//NewsFilterDust.Builder result = NewsFilterDust.newBuilder();
								isFiltered = false;
								result.setNewsCode(newsData.getNewsCode());
								result.setTitle(newsData.getTitle());
								result.setContent(nodust);
								result.setResult("B");
								result.addAllClassNameList(newsData.getIndustryNamesList());
								result.addAllCompanyNames(newsData.getCompanyNamesList());
								result.setAccessTime(newsData.getEntryDate());
								result.setIsFiltered(false);
								result.setSource("B");
								result.setTypeof(newsData.getTypeOf());
								result.setPublishdate(newsData.getPublishdate());
								collector.emit(new Values(result.build()));
							}
						}
					}
				}
				if(isFiltered){
					result.setNewsCode(newsData.getNewsCode());
					result.setTitle(newsData.getTitle());
					result.setContent(newsData.getText());
					result.setResult("B");
					result.addAllClassNameList(newsData.getIndustryNamesList());
					result.addAllCompanyNames(newsData.getCompanyNamesList());
					result.setIsFiltered(true);
					result.setSource("B");
					result.setTypeof(newsData.getTypeOf());
					result.setAccessTime(newsData.getEntryDate());
					result.setPublishdate(newsData.getPublishdate());
					collector.emit(new Values(result.build()));
				}
			}else if(newsData.getTypeOf().equals("11") || newsData.getTypeOf().equals("12")){
				//Pattern p;
				//Matcher m;		
				boolean needFilter = false;
				
				/*
				for(String indus : weiboFilter){
					if(newsData.getIndustryNamesList().contains(indus) && newsData.getIndustryNamesCount() == 1){
						needFilter = true;
						break;
					}
				}
				 */
				if(newsData.getIndustryNamesCount() == 1)
					needFilter = true;
			
				if(needFilter){
					//System.out.println("needs filter");
					String weiboTitle = bayesWeibo.bayes_filter_sent(newsData.getTitle());
					if(null == weiboTitle || weiboTitle.length() < 10){
						System.out.println("false");
						result.setNewsCode(newsData.getNewsCode());
						result.setTitle(newsData.getTitle());
						result.setContent(newsData.getText());
						result.setResult("B");
						result.addAllClassNameList(newsData.getIndustryNamesList());
						result.addAllCompanyNames(newsData.getCompanyNamesList());
						result.setAccessTime(newsData.getEntryDate());
						result.setIsFiltered(false);
						result.setSource("B");
						result.setTypeof(newsData.getTypeOf());
						result.setPublishdate(newsData.getPublishdate());
						collector.emit(new Values(result.build()));
					}else{
						//System.out.println("true");
						result.setNewsCode(newsData.getNewsCode());
						result.setTitle(newsData.getTitle());
						result.setContent(newsData.getText());
						result.setResult("B");
						result.addAllClassNameList(newsData.getIndustryNamesList());
						result.addAllCompanyNames(newsData.getCompanyNamesList());
						result.setAccessTime(newsData.getEntryDate());
						result.setIsFiltered(true);
						result.setSource("B");
						result.setTypeof(newsData.getTypeOf());
						result.setPublishdate(newsData.getPublishdate());
						collector.emit(new Values(result.build()));
					}
				}else{
					result.setNewsCode(newsData.getNewsCode());
					result.setTitle(newsData.getTitle());
					result.setContent(newsData.getText());
					result.setResult("B");
					result.addAllClassNameList(newsData.getIndustryNamesList());
					result.addAllCompanyNames(newsData.getCompanyNamesList());
					result.setIsFiltered(false);
					result.setSource("B");
					result.setTypeof(newsData.getTypeOf());
					result.setAccessTime(newsData.getEntryDate());
					result.setPublishdate(newsData.getPublishdate());
					collector.emit(new Values(result.build()));
				}
			}else {
				result.setNewsCode(newsData.getNewsCode());
				result.setTitle(newsData.getTitle());
				result.setContent(newsData.getText());
				result.setResult("B");
				result.addAllClassNameList(newsData.getIndustryNamesList());
				result.addAllCompanyNames(newsData.getCompanyNamesList());
				result.setIsFiltered(false);
				result.setSource("B");
				result.setTypeof(newsData.getTypeOf());
				result.setAccessTime(newsData.getEntryDate());
				result.setPublishdate(newsData.getPublishdate());
				collector.emit(new Values(result.build()));
			}
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("filter"));
	}
	
}
