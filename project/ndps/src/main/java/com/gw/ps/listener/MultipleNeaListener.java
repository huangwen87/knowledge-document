package com.gw.ps.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gw.ps.dao.INeaPredictDao;
import com.gw.ps.dao.impl.TaskOnSqlServerDaoImpl;
import com.gw.ps.dao.impl.TotalDaoImpl;
import com.gw.ps.model.NeaPredict;
import com.gw.ps.model.NeaPredict2;
import com.gw.ps.model.NeaPredictFiltered;
import com.gw.ps.model.NewsSentimentFlowTotal;
import com.gw.ps.model.NicCategory;
import com.gw.ps.pbbean.PbNewsClassify;
import com.gw.ps.pbbean.PbNewsInnerUsing;
import com.gw.ps.pbbean.PbNewsSentiment;
import com.gw.ps.pbbean.PbNewsSentimentTreated;
import com.gw.ps.utils.ByteUtil;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

/**
 * 读取情感分析相关队列信息
 * 
 * @author JohnmyWork
 * 
 */
public class MultipleNeaListener {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "neaPredictDao")
	private INeaPredictDao neaPredictDao;
	@Resource(name = "tossdi")
	private TaskOnSqlServerDaoImpl tossdi;
	@Resource(name="totalDao")
	private TotalDaoImpl totalDao;
	
	@Autowired
	private String iswrite;
	
	
	List<NewsSentimentFlowTotal> nsft_list = new ArrayList<NewsSentimentFlowTotal>();
	

	public void sentimentPredict(Map<String, byte[]> map) {

		NeaPredict np = new NeaPredict();
		try {

			PbNewsSentiment.NewsSentiment sentiment = PbNewsSentiment.NewsSentiment
					.parseFrom(map.get("key"));

			np.setNewsCode(sentiment.getNewsCode());
			np.setSentiment(sentiment.getSentiment());
			np.setNegRate(sentiment.getNegRate());
			np.setPosRate(sentiment.getPosRate());
			np.setProcessTime(sentiment.getProcessTime());
			np.setLogTime(DateUtil.getCurrentDateStr());
			np.setText(sentiment.getText());
			np.setTitle(sentiment.getTitle());

			neaPredictDao.addNewsClass(np);

			// 加入分类信息
			np.setResult(sentiment.getResult());

			// StringBuffer sb1 = new StringBuffer();
			// for (String s : sentiment.getClassNameListList()) {
			// sb1.append(s + "\r");
			// }
			// np.setClassNameList(sb1.toString());

			StringBuffer sb2 = new StringBuffer();
			for (String s : sentiment.getCompanyNamesList()) {
				sb2.append(s + "\r");
			}
			np.setCompanyNames(sb2.toString());

			for (String s : sentiment.getClassNameListList()) {
				np.setClassNameList(s);
				neaPredictDao.addNewsSentiClass(np);
			}

		} catch (Exception e) {
			logger.error(np.toString());
			e.printStackTrace();
		}

	}

	public void classifyPredict(Map<String, byte[]> map) {

		NicCategory nc = new NicCategory();
		try {

			PbNewsClassify.NewsClassifyResult classifyResult = PbNewsClassify.NewsClassifyResult
					.parseFrom(map.get("key"));

			nc.setNewsCode(ByteUtil.long2HL(classifyResult.getNewsCode()));
			nc.setLogTime(DateUtil.getCurrentDateStr());
			nc.setResult(classifyResult.getResult());
			for (String s : classifyResult.getClassNameListList()) {
				nc.setCategoryName(s);
				logger.debug(nc.toString());
				neaPredictDao.addNewsClass(nc);
			}

		} catch (Exception e) {
			logger.error(nc.toString());
			e.printStackTrace();
		}

	}

	public void sentimentFiltered(Map<String, byte[]> map) {

		NeaPredictFiltered np = new NeaPredictFiltered();
		try {

			PbNewsInnerUsing.InnerNews sentiment = PbNewsInnerUsing.InnerNews
					.parseFrom(map.get("key"));

			np.setNewsCode(ByteUtil.long2HL(sentiment.getNewsCode()));
			np.setLogTime(DateUtil.getCurrentDateStr());
			np.setTitle(sentiment.getTitle());
			np.setSource(sentiment.getSource());
			np.setFiltered(sentiment.getIsFiltered());
			np.setResult(sentiment.getResult());
			np.setContent(sentiment.getContent());

			StringBuffer sb1 = new StringBuffer();
			for (String s1 : sentiment.getClassNameListList()) {
				sb1.append(s1).append(",");
			}
			if (sb1.length() > 0)
				np.setClassNameList(StringUtils.removeEnd(sb1.toString(), ","));

			StringBuffer sb2 = new StringBuffer();
			for (String s2 : sentiment.getCompanyNamesList()) {
				sb2.append(s2).append(",");
			}
			if (sb2.length() > 0)
				np.setCompanyNames(StringUtils.removeEnd(sb2.toString(), ","));

			neaPredictDao.addNewsClass(np);
		} catch (Exception e) {
			logger.error(np.toString());
			e.printStackTrace();
		}
	}

	public void getNewsSentimentTreated(Map<String, byte[]> map) {
		NeaPredict2 np = new NeaPredict2();
		NeaPredict npt = new NeaPredict();
		try {

			PbNewsSentimentTreated.NewsSentiment sentiment = PbNewsSentimentTreated.NewsSentiment
					.parseFrom(map.get("key"));

			if(sentiment.getResult().equals("B")){   //王树德那边来源数据
              try{
					np.setNewsCode(Long.valueOf(sentiment.getNewsCode()));
					np.setSentiment(sentiment.getSentiment());
					np.setNegRate(sentiment.getNegRate());
					np.setPosRate(sentiment.getPosRate());
					np.setProcessTime(sentiment.getProcessTime());
					np.setLogTime(DateUtil.getCurrentDateStr());
					np.setText(sentiment.getText());
					np.setTitle(sentiment.getTitle());
					np.setResult(sentiment.getResult());
		            np.setPublishdate(sentiment.getPublishdate());
					np.setDataFrom("wangshude");
					np.setSource("B");
					np.setAccessTime(sentiment.getAccessTime());
		
					np.setFiltered(!sentiment.getIsFiltered());
					if(iswrite.equals("true")){
					   insertData(np);// 这里要求np.setFiltered相反
					}
					//流出时间写入本地数据库利于统计
					NewsSentimentFlowTotal newsSentimentFlowTotal = new NewsSentimentFlowTotal();
					newsSentimentFlowTotal.setNewsCode(np.getNewsCode());
					newsSentimentFlowTotal.setTimeOut(DateUtil.getCurrentDateStr());
					
					nsft_list.add(newsSentimentFlowTotal);
					if(nsft_list.size() == 100){
					    totalDao.batchUpdate(nsft_list);
						nsft_list.clear();
					}
					
					StringBuffer sb2 = new StringBuffer();
					for (String s2 : sentiment.getCompanyNamesList()) {
						sb2.append(s2).append(",");
					}
					if (sb2.length() > 0)
						np.setCompanyNames(StringUtils.removeEnd(sb2.toString(), ","));
		
					StringBuffer sb1 = new StringBuffer();
					for (String s1 : sentiment.getClassNameListList()) {
						sb1.append(s1).append(",");
					}
					if (sb1.length() > 0)
						np.setClassNameList(StringUtils.removeEnd(sb1.toString(), ","));
					if (sentiment.getIsFiltered()) {
						np.setFiltered(sentiment.getIsFiltered());// 这里写入正常的np.setFiltered
						neaPredictDao.addNewsSenanaFiltered(np);// 加入表news_senana_filtered
					} else {
						// for (String s : sentiment.getClassNameListList()) {
						// np.setClassNameList(s);
						// neaPredictDao.addNewsSenanaIndcls(np);//
						// 加入表news_senana_indcls
						// }
		
						if ("1".equals(sentiment.getTypeOf()) || "3".equals(sentiment.getTypeOf()) ||
								"6".equals(sentiment.getTypeOf()) || "13".equals(sentiment.getTypeOf())) {
							neaPredictDao.addNewsSentestSample(np);// 加入表news_sentiment_test_samples
						} else if("11".equals(sentiment.getTypeOf()) || "12".equals(sentiment.getTypeOf()))  {
							neaPredictDao.addNewsSenWeiboSample(np);// 加入表weibo_sentiment_test_samples
						}
					}
              }catch(Exception e){
            	  logger.error(np.toString());
      			  e.printStackTrace();
              }
			}else if(sentiment.getResult().equals("C")){                  //王安宇那边数据

			 try{
				npt.setNewsCode(Long.valueOf(sentiment.getNewsCode()));
				npt.setSentiment(sentiment.getSentiment());
				npt.setNegRate(sentiment.getNegRate());
				npt.setPosRate(sentiment.getPosRate());
				npt.setProcessTime(sentiment.getProcessTime());
				npt.setLogTime(DateUtil.getCurrentDateStr());
				npt.setText(sentiment.getText());
				npt.setTitle(sentiment.getTitle());
				
				//王安宇那边没用的
				npt.setPublishdate(sentiment.getPublishdate());
				npt.setDataFrom("wanganyu");
				npt.setSource("C");
				npt.setAccessTime(sentiment.getAccessTime());
				npt.setFiltered(!sentiment.getIsFiltered());
				
				
				neaPredictDao.addNewsClass(npt);

				// 加入分类信息
				npt.setResult(sentiment.getResult());

				StringBuffer sb2 = new StringBuffer();
				for (String s : sentiment.getCompanyNamesList()) {
					sb2.append(s + "\r");
				}
				npt.setCompanyNames(sb2.toString());

				for (String s : sentiment.getClassNameListList()) {
					npt.setClassNameList(s);
					neaPredictDao.addNewsSentiClass(npt);
				}
				
			 }catch(Exception e){
				 logger.error(npt.toString());
				 e.printStackTrace();
			 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertData(NeaPredict2 np) {
		if (tossdi.getCount(np) > 0) {
			tossdi.update(np);
		} else {
			tossdi.setData(np);
		}
	}
}
