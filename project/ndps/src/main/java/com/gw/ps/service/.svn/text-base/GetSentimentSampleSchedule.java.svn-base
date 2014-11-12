package com.gw.ps.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import com.gw.ps.dao.impl.GetSentimentSampleDaoImpl;
import com.gw.ps.dao.impl.TaskOnSqlServerGetSentimentSampleDaoImpl;
import com.gw.ps.model.NewsSentimentSample;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.JsonUtil;
import com.gw.ps.utils.LogUtil;

/**
 * @function：定时获取情感样本
 * @author Darwen
 * @data:2013-10-11下午3:04:26
 *
 */
public class GetSentimentSampleSchedule {

	private final Logger logger = LogUtil.getInstance(this.getClass());
	
	@Resource(name="tossgssdi")
	private TaskOnSqlServerGetSentimentSampleDaoImpl tossgssdi;
	
	@Resource(name="getSampleDao")
	private GetSentimentSampleDaoImpl getSampleDao;
	
	@Scheduled(cron = "0 0 7 * * ?")
	public void getdata() {
		long now = System.currentTimeMillis();
		long aDayAgo = now - 24 * 60 * 60 * 1000;
		
		Date date1day = new Date(aDayAgo);
		String dateStr = DateUtil.convertDateToStr(date1day, DateUtil.TIME_FORMAT);
		
		try{
			
		     List<NewsSentimentSample> list = tossgssdi.getData(dateStr);
		     
		     for (NewsSentimentSample nss : list) {
		    	  getSampleDao.insertData(nss);
		     }
		     logger.info(list.isEmpty() ? 0 : list.size() +"-----insert");
		     
		  
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
