package com.gw.ps.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import com.gw.ps.dao.impl.TotalDaoImpl;
import com.gw.ps.model.NewsFlowTime;
import com.gw.ps.monitor.init.MonitorReportService;
import com.gw.ps.monitor.init.MonitorReportServiceTotal;
import com.gw.ps.monitor.yt.TempMessage;
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
public class TotalDataSchedule {

	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name="totalDao")
	private TotalDaoImpl totalDao;

	@Scheduled(cron = "0 0/5 * * * ?")  //下面对应的时间也得改
	public void clean() {
		//long now = System.currentTimeMillis();
		long now = System.currentTimeMillis();
		long aFiveMinutesAgo = now - 5 * 60 * 1000;

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
				  sendMsg(nft);   //发送消息
				  
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	/**
	 *功能：发送消息到日志监控
	 * @param bn 
	 * @return void 
	 * @author Darwen
	 * @date: 2013-9-5下午5:27:55
	 */
	public void sendMsg(NewsFlowTime bn) {
		TempMessage tm = new TempMessage();
		int inTotal = bn.getInflow();
		int outTotal = bn.getOutflow();
		String desc =null;
		if(inTotal==0 && outTotal!=0){
			tm.setLevel("1");
			desc = "在 "+bn.getDateStr_end()+" 到 "+bn.getDateStr_start()+"间流入量为0";
		}else if(inTotal!=0 && outTotal==0){
			tm.setLevel("1");
			desc = "在 "+bn.getDateStr_end()+" 到 "+bn.getDateStr_start()+"间流出量为0";
		}else if(inTotal==0 && outTotal==0){
			tm.setLevel("1");
			desc = "在 "+bn.getDateStr_end()+" 到 "+bn.getDateStr_start()+"间流入、出量都为0";
		}else{	
			tm.setLevel("5");
			desc = "在 "+bn.getDateStr_end()+" 到 "+bn.getDateStr_start()+"间流入量为："+inTotal+
					" 流出量为："+outTotal;

		}
        tm.setDesc(desc);
		MonitorReportService.add(tm);
		logger.debug(JsonUtil.toJson(tm));

	}

}
