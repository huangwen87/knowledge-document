package com.gw.ps.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.gw.ps.dao.impl.CleanDaoImpl;
import com.gw.ps.dao.impl.TaskOnSqlServerGetDataDaoImpl;
import com.gw.ps.dao.impl.TotalDaoImpl;
import com.gw.ps.model.NeaSentimentNews;
import com.gw.ps.model.NewsSentimentFlowTotal;
import com.gw.ps.pbbean.PbNewsSentimentUntreated;
import com.gw.ps.utils.DateUtil;

/**
 * 定时获取sqlserver中的新闻
 * 
 * @author JohnmyWork
 * @date 2013-9-5
 */
public class ManipulateForSqlServerSchedule implements Runnable{

    private final int number = 50000;
	
	private static TaskOnSqlServerGetDataDaoImpl tossgddi;

	private static JmsTemplate myJmsTemplate;
	
	private static CleanDaoImpl cleanDao;
	
	private static TotalDaoImpl totalDao;
	

	public static void setTossgddi(TaskOnSqlServerGetDataDaoImpl tossgddi) {
		ManipulateForSqlServerSchedule.tossgddi = tossgddi;
	}


	public static void setCleanDao(CleanDaoImpl cleanDao) {
		ManipulateForSqlServerSchedule.cleanDao = cleanDao;
	}
	
	
	public static void setMyJmsTemplate(JmsTemplate myJmsTemplate) {
		ManipulateForSqlServerSchedule.myJmsTemplate = myJmsTemplate;
	}
	
	public static void setTotalDao(TotalDaoImpl totalDao) {
		ManipulateForSqlServerSchedule.totalDao = totalDao;
	}


	@Override
	public void run() {
		int data_maxid = cleanDao.query("news_maxid_select");
		int maxid = data_maxid;
		
		while(true){
		if (maxid == data_maxid) {
				// 查询数据库获得最大maxid
				maxid = cleanDao.query("news_maxid_select");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bgeinID", maxid);
		param.put("EndID", number + maxid);  
       // maxid+=number;
		try{
			List<NeaSentimentNews> list = tossgddi.getData(param);
			
			for (NeaSentimentNews nsn : list) {
				maxid = nsn.getId();
				cleanDao.update("news_maxid_update", maxid +"");
				
				
				if (nsn.getCategoryLists().contains(",")) {
	
					List<String> companyIds = new ArrayList<String>();
					List<String> companyNames = new ArrayList<String>();
					List<String> industryIds = new ArrayList<String>();
					List<String> industryNames = new ArrayList<String>();
	
					String categoryLists = nsn.getCategoryLists();
					String[] sa1 = categoryLists.split(",\\|");
					for (int i = 1; i < sa1.length; i++) {
						if (sa1[i].contains(":")) {
							String[] elements = sa1[i].split(":\\|");
							if (elements.length > 0 && !elements[0].isEmpty())
								companyIds.add(elements[0]);
							if (elements.length > 1 && !elements[1].isEmpty())
								companyNames.add(elements[1]);
							if (elements.length > 2 && !elements[2].isEmpty())
								industryIds.add(elements[2]);
							if (elements.length > 3 && !elements[3].isEmpty())
								industryNames.add(elements[3]);
						}
					}
	
					nsn.setCompanyIds(companyIds);
					nsn.setCompanyNames(companyNames);
					nsn.setIndustryIds(industryIds);
					nsn.setIndustryNames(industryNames);
	
				}
				nsn.setLogTime(DateUtil.getCurrentDateStr());
				
				//流入、流出量统计
				NewsSentimentFlowTotal newsSentimentFlowTotal = new NewsSentimentFlowTotal();
				newsSentimentFlowTotal.setNewsID(nsn.getId());  //自增ID
				newsSentimentFlowTotal.setNewsCode(nsn.getNewsId());  //newsCode
				newsSentimentFlowTotal.setTimeIn(DateUtil.getCurrentDateStr());
				totalDao.setData(newsSentimentFlowTotal);  //写入流入数据
				//sendToMq(toPbBean(nsn));    //因为情感不需要，所以注释掉，不发送数据到MQ
			  }
		}catch(Exception e){
			e.printStackTrace();
		}
	  }
	}
	
	public void go() {
       new Thread(this).start();
	}

	/**
	 * 转换为pb协议文件
	 * 
	 * @param nsn
	 * @return PbNewsSentimentUntreated.NewsSentimentUntreated
	 * @author JohnmyWork
	 * @date 2013-9-6
	 */
	private PbNewsSentimentUntreated.NewsSentimentUntreated toPbBean(NeaSentimentNews nsn) {
		PbNewsSentimentUntreated.NewsSentimentUntreated.Builder builder = PbNewsSentimentUntreated.NewsSentimentUntreated.newBuilder();
		builder.setNewsCode(String.valueOf(nsn.getNewsId()));
		if (nsn.getTitle() != null) {
			builder.setTitle(nsn.getTitle());
		}
		if (nsn.getPublishDate() != null) {
			builder.setPublishdate(nsn.getPublishDate());
		}
		if (nsn.getText() != null) {
			builder.setText(nsn.getText());
		}
		if (nsn.getNewsType() != null) {
			builder.setTypeOf(nsn.getNewsType());
		}
		if (nsn.getNewsType() != null) {
			builder.setNewsType(nsn.getNewsType());
		}
		if (nsn.getEntryDate() != null) {
			builder.setEntryDate(nsn.getEntryDate());
		}
		if (nsn.getCompanyIds() != null && !nsn.getCompanyIds().isEmpty()) {
			builder.addAllCompanyIds(nsn.getCompanyIds());
		}
		if (nsn.getCompanyNames() != null && !nsn.getCompanyNames().isEmpty()) {
			builder.addAllCompanyNames(nsn.getCompanyNames());
		}
		if (nsn.getIndustryIds() != null && !nsn.getIndustryIds().isEmpty()) {
			builder.addAllIndustryIds(nsn.getIndustryIds());
		}
		if (nsn.getIndustryNames() != null && !nsn.getIndustryNames().isEmpty()) {
			builder.addAllIndustryNames(nsn.getIndustryNames());
		}
		return builder.build();

	}

	/**
	 * 发送至mq
	 * 
	 * @param nsu
	 *            void
	 * @author JohnmyWork
	 * @date 2013-9-6
	 */
	private void sendToMq(final PbNewsSentimentUntreated.NewsSentimentUntreated nsu) {
		myJmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setBytes("key", nsu.toByteArray());
				return message;
			}
		});
	}










	
	
}
