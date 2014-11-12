package com.gw.ps.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import com.gw.bayes.BayesTrain;
import com.gw.ps.dao.impl.CleanDaoImpl;
import com.gw.ps.listener.LogEventListener;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.LogUtil;

/**
 * 定时清除数据，只保留一周数据
 * 
 * @author JohnmyWork
 * 
 */
public class CleanDataSchedule {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "cleanDao")
	private CleanDaoImpl cleanDao;

	// @Scheduled(cron = "0/5 * * * * ?")
	@Scheduled(cron = "0 0 7 * * ?")
	public void clean() {
		long now = System.currentTimeMillis();
		long aWeekAgo = now - 7 * 24 * 60 * 60 * 1000;
		long twoDaysAgo = now - 2 * 24 * 60 * 60 * 1000;
		long twoWeeksAgo = now - 14 * 24 * 60 * 60 * 1000;

		Date date1w = new Date(aWeekAgo);
		String dateStr = DateUtil.convertDateToStr(date1w, DateUtil.TIME_FORMAT);

		Date date2d = new Date(twoDaysAgo);
		String dateStr2 = DateUtil.convertDateToStr(date2d, DateUtil.TIME_FORMAT);
		
		Date date2w = new Date(twoWeeksAgo);
		String date2Str = DateUtil.convertDateToStr(date2w, DateUtil.TIME_FORMAT);

		try {
			Properties pro = new Properties();
			pro.load(LogEventListener.class.getClassLoader().getResourceAsStream("filter.properties"));

			// 按时间戳一周前
			String tableByTime = pro.getProperty("tablebytimestamp");
			if (tableByTime != null && tableByTime != "") {
				for (String tbt : tableByTime.split(",")) {
					int i = cleanDao.clean(tbt, aWeekAgo);
					logger.info(tbt + "-----" + i);
				}
			}

			// 按日期一周前
			String tableByDate = pro.getProperty("tablebydate");
			if (tableByDate != null && tableByDate != "") {
				for (String tbd : tableByDate.split(",")) {
					int i = cleanDao.clean(tbd, dateStr);
					logger.info(tbd + "-----" + i);
				}
			}
			
			// 按日期二周前
			String tableByWeek = pro.getProperty("tablebyweek");
			if (tableByWeek != null && !tableByWeek.isEmpty()) {
				for (String tbd2w : tableByWeek.split(",")) {
					int i = cleanDao.clean(tbd2w, date2Str);
					logger.info(tbd2w + "-----" + i);
				}
			}
			
			// 按日期两天前
			String tbd2d = pro.getProperty("tbd2d");
			if (tbd2d != null && tbd2d != "") {
				for (String t2d : tbd2d.split(",")) {
					int i = cleanDao.clean(t2d, dateStr2);
					logger.info(t2d + "-----" + i);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "0 0 22 * * ?")
	public void initBayesTrain() {
		BayesTrain bayestrain = new BayesTrain();
		try {
			long beforeTime = System.currentTimeMillis();
			logger.info("---------start train-----------");
			bayestrain.train();
			long afterTime = System.currentTimeMillis();
			long count = afterTime - beforeTime;
			logger.info("---------end train-----------" + count);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
