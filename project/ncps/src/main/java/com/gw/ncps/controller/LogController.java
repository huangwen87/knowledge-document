package com.gw.ncps.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gw.ncps.common.util.JsonUtil;
import com.gw.ncps.dto.NewsDTO;
import com.gw.ncps.service.INewsDuplicateRemovalService;

/**
 * 日志统计
 * @author JohnmyWork
 *
 */
@Controller
@RequestMapping(value = "/log")
public class LogController {

	@Resource(name = "ndrService")
	private INewsDuplicateRemovalService ndrService;

	/**
	 * 查询新闻去重统计历史记录
	 * @return
	 */
	@RequestMapping(value = "/{tableName}", method = RequestMethod.POST)
	public @ResponseBody String queryHistory(@PathVariable String tableName) {
		Object list =  ndrService.queryHistory(new NewsDTO(tableName));
		return JsonUtil.toJson(list);
	}
	
	/**
	 * 查询新闻去重统计新增记录
	 * @param maxtimestamp
	 * @return
	 */
	@RequestMapping(value = "/{tableName}/{maxtimestamp}", method = RequestMethod.POST)
	public @ResponseBody String queryByTimeStamp(@PathVariable Long maxtimestamp,@PathVariable String tableName) {
		Object list =  ndrService.queryHistory(new NewsDTO(tableName,maxtimestamp));
		return JsonUtil.toJson(list);
	}
	
}
