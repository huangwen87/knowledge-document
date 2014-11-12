package com.gw.ps.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import com.gw.ps.dao.impl.TotalDaoImpl;
import com.gw.ps.model.NewsFlowTime;
import com.gw.ps.monitor.init.MonitorReportServiceTotal;
import com.gw.ps.monitor.yt.TotalMessage;
import com.gw.ps.utils.DateUtil;
import com.gw.ps.utils.JsonUtil;
import com.gw.ps.utils.LogUtil;

/**
 * 每个一定时间取出流入、出数据
 * 
 * @author JohnmyWork
 * 
 */
public class TotalDataSchedule2 {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name="totalDao")
	private TotalDaoImpl totalDao;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void clean() {
		long now = System.currentTimeMillis();
		long aFiveMinutesAgo = now -  60 * 1000;

		Date date5min = new Date(aFiveMinutesAgo);
		String dateStr_end = DateUtil.convertDateToStr(date5min, DateUtil.TIME_FORMAT);
		
		Date nows = new Date(now);
		String dataStr_start = DateUtil.convertDateToStr(nows, DateUtil.TIME_FORMAT);

		NewsFlowTime nft = new NewsFlowTime();
		nft.setDateStr_start(dataStr_start);
		nft.setDateStr_end(dateStr_end);

		try {
				  int inTotal = totalDao.inflow(nft);  //流入数据量
				  int outTotal = totalDao.outflow(nft); //流出数据量
				  nft.setInflow(inTotal);
				  nft.setOutflow(outTotal);
				  sendMsgTotal(nft); //发送流量数据到监控
				  
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 *功能：发送去重流入、流出量到监控系统
	 *
	 * @param bn 
	 * @return void 
	 * @author Darwen
	 * @date: 2013-10-14下午4:43:18
	 */
	public void sendMsgTotal(NewsFlowTime bn){
		TotalMessage tm = new TotalMessage();
		tm.setCmd(119);
		tm.setInflow(bn.getInflow());
		tm.setOutflow(bn.getOutflow());
		MonitorReportServiceTotal.add(tm);
		logger.debug(JsonUtil.toJson(tm));
	}
	
	

}
