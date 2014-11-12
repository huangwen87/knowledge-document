package com.gw.ncps.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import com.gw.ncps.common.util.LogUtil;
import com.gw.ncps.model.NewsFilterSimplify;
import com.gw.ncps.service.INewsDuplicateRemovalService;
import com.gw.ncps.task.impl.SyncInit;

/**
 * @function：
 * @author Darwen
 * @data:2013-11-8上午10:00:12
 *
 */

public class FixedContentTableTask{
	
	private final Logger logger = LogUtil.getInstance(this.getClass());
	
	@Resource(name = "ndrService")
	private INewsDuplicateRemovalService ndrService;
	
	
	//每天换新一次表内容  到内存
	@Scheduled(cron = "0 10 8 * * ?")
	public void updateEveryDay() {	
		try{
			    logger.info("===updateEveryDay===start=====");
			    synchronized(SyncInit.class){
				    SyncInit.setSum(0);
				    List<NewsFilterSimplify> newList = new ArrayList<NewsFilterSimplify>(); 
				    SyncInit.setList(newList); //清空已经存在的数据
					List<NewsFilterSimplify> list = (List<NewsFilterSimplify>)ndrService.queryContentAll(); 
					SatifyList sl = new SatifyList();
					sl.addList(list);    //表加载到内存
			    }
				logger.info("===updateEveryDay==="+SyncInit.getList().size());
	        }catch(Exception e){
		        e.printStackTrace();
	        }
	}
	
	
	
	//每五分钟更新一次
	@Scheduled(cron = "0 0/5 * * * ?")
	public void updateFiveMinutes() {	
		try{
			  synchronized(SyncInit.class){
					List<NewsFilterSimplify> list = (List<NewsFilterSimplify>)ndrService.queryContentFilterAll(SyncInit.getSum());
					SatifyList sl = new SatifyList();
					sl.addList(list);    //表加载到内存
					//logger.info("===FixedContentTableTaskFiveMinutes==="+list.size());
			  }
	        }catch(Exception e){
		        e.printStackTrace();
	        }
	}
	
	
}
