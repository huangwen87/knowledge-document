package com.gw.ncps.task.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.gw.ncps.common.util.LogUtil;
import com.gw.ncps.model.NewsFilterSimplify;
import com.gw.ncps.service.INewsDuplicateRemovalService;
import com.gw.ncps.task.SatifyList;

/**
 * @function：
 * @author Darwen
 * @data:2013-11-8下午1:05:58
 *
 */
public class InitContentTableTask {
	
	private final Logger logger = LogUtil.getInstance(this.getClass());

	@Resource(name = "ndrService")
	private INewsDuplicateRemovalService ndrService;
	
	//跟项目启动   初始化数据库内容到内存
	public void init() {
		try{
				List<NewsFilterSimplify> list = (List<NewsFilterSimplify>)ndrService.queryContentAll(); 
				SatifyList sl = new SatifyList();
				sl.addList(list);    //表加载到内存
				logger.info("===contentInit==="+SyncInit.getList().size());
		}catch(Exception e){
			e.printStackTrace();
		}
				
	}





}
