package com.gw.ncps.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gw.ncps.common.util.DateUtil;
import com.gw.ncps.common.util.JsonUtil;
import com.gw.ncps.common.util.LogUtil;
import com.gw.ncps.common.util.StringUtil;
import com.gw.ncps.dto.NewsTrashDTO;
import com.gw.ncps.dto.NewsWordDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.service.INdrManipulateService;

/**
 * 去重样本管理
 * 
 * @author JohnmyWork
 * 
 */
@Controller
@RequestMapping(value = "/ndr/sample")
public class NdrManipulateController {

	private final Logger logger = LogUtil.getInstance(this.getClass());
	@Resource(name = "ndrManipulateService")
	private INdrManipulateService ndrManipulateService;

	// 添加垃圾样本
	@RequestMapping(value = "/addTrash", method = RequestMethod.GET)
	public @ResponseBody
	String addTrash(NewsTrashDTO newsTrashDTO) {
		if (newsTrashDTO.getTrash().length() < 1) {
			return "0";
		}
		newsTrashDTO.setDateStr(DateUtil.getCurrentDateStr());
		int rst = ndrManipulateService.addTrash(newsTrashDTO);
		logger.info(rst);
		return String.valueOf(rst);

	}
	
	@RequestMapping(value="/addWord", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public @ResponseBody
	String wordAdd(NewsWordDTO newsWordDTO)
	{
		 String news = newsWordDTO.getWord();
		 if(news.isEmpty())
			 return JsonUtil.toJson("没有输入数据");
		 String[] arr = news.split("\n");
		 int count = 0;
		 for(int k = 0 ; k < arr.length; k++)
		 {
			 String temp = arr[k].trim();
			 if(temp.length()>0)
			 {
			      Object list =  ndrManipulateService.queryHistory(new NewsWordDTO("word",temp));
			      if(list.toString().equals("[0]"))	  //没有重复			 
			      {
				     Date now = new Date(); 
				     Object result = ndrManipulateService.insertWord(new NewsWordDTO(temp, now));
		             count++;
			      }
			 }
		 }
		 return JsonUtil.toJson("更新"+count+"个词且已结束");
	}
	
	@RequestMapping(value="/addEmotion", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody
	String addEmotion(NewsWordDTO newsWordDTO)
	{
		    if (newsWordDTO.getWord().length() < 1) {
				return "0";
			}
		    newsWordDTO.setDateStr(DateUtil.getCurrentDateStr());
			int rst = ndrManipulateService.addEmotionSample(newsWordDTO);
			logger.info(rst);
			return String.valueOf(rst);
	}
	
	@RequestMapping(value="/addEmotionTitle", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody
	String addEmotionTitle(NewsWordDTO newsWordDTO)
	{
		    if (newsWordDTO.getWord().length() < 1) {
				return "0";
			}
		    newsWordDTO.setDateStr(DateUtil.getCurrentDateStr());
			int rst = ndrManipulateService.addEmotionTitleSample(newsWordDTO);
			logger.info(rst);
			return String.valueOf(rst);
	}
	
	
	
	@RequestMapping(value = "/queryword", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryWord(Page page) {
		SearchDTO dto = new SearchDTO(page, null);
		Page rpsPage = ndrManipulateService.queryMatchAll(dto);
		return JsonUtil.object2Json(rpsPage);
	}
	
	
	@RequestMapping(value = "/querytrash", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryTrash(Page page) {
		SearchDTO dto = new SearchDTO(page, null);
		Page rpsPage = ndrManipulateService.queryMatchAllTrash(dto);
		return JsonUtil.object2Json(rpsPage);
	}
	
	
	@RequestMapping(value = "/queryemotion", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryEmotion(Page page) {
		SearchDTO dto = new SearchDTO(page, null);
		Page rpsPage = ndrManipulateService.queryMatchAllEmotion(dto);
		return JsonUtil.object2Json(rpsPage);
	}
	
	
	@RequestMapping(value = "/queryemotiontitle", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryEmotionContent(Page page) {
		SearchDTO dto = new SearchDTO(page, null);
		Page rpsPage = ndrManipulateService.queryMatchAllEmotionContent(dto);
		String s = JsonUtil.object2Json(rpsPage);
		s = StringUtil.replaceLastErrorStr(s);
		return s;
	}
	
    /**
     * 情感标题样本 删除 
     * */
	@RequestMapping(value = "/delemotiontitlesamplebyid", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String delEmotionTitleSampleById(@RequestParam("id") String id) {
		String result = ndrManipulateService.delEmotiontTitleSampleById(id);
		logger.debug(result); 
		return result;
	}
	
	
    /**
     * 情感内容样本 删除 
     * */
	@RequestMapping(value = "/delemotioncontentsamplebyid", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String delEmotionContentSampleById(@RequestParam("id") String id) {
		String result = ndrManipulateService.delEmotionContentSampleById(id);
		logger.debug(result); 
		return result;
	}
	
    /**
     * 去重垃圾样本 删除 
     * */
	@RequestMapping(value = "/delduplicateremovalsamplebyid", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String delDuplicateRemovalSampleById(@RequestParam("id") String id) {
		String result = ndrManipulateService.delDuplicateRemovalSampleById(id);
		logger.debug(result); 
		return result;
	}
	
    /**
     * 分词词库 删除 
     * */
	@RequestMapping(value = "/delwordlibbyid", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String delWordLibById(@RequestParam("id") String id) {
		String result = ndrManipulateService.delWordLibById(id);
		logger.debug(result); 
		return result;
	}
}
