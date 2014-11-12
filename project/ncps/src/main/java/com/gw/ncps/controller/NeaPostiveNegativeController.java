package com.gw.ncps.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.gw.ncps.dto.NewsEmotionDTO;
import com.gw.ncps.dto.NewsEmotionDataLogDTO;
import com.gw.ncps.dto.NewsEmotionTitleFilterDTO;
import com.gw.ncps.dto.NewsEmotionVolumeDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.NewsSentimentVolume;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.impl.TaskOnSqlServerDaoImpl;
import com.gw.ncps.service.INewsPostiveNegativeService;

/**
 * 情感分析
 * 
 * @author darwen
 * */

@Controller
@RequestMapping(value = "nea/posORneg")
public class NeaPostiveNegativeController {

	private final Logger logger = LogUtil.getInstance(this.getClass());
	@Resource(name = "neaService")
	private INewsPostiveNegativeService neaService;
	
	@Resource(name="tossdi")
	private TaskOnSqlServerDaoImpl tossdi;
	
	

	/**
	 * 分页查询正负面详情
	 * 
	 * @param NewsEmotionDTO
	 * @param page
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-20
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String query(NewsEmotionDTO NewsEmotionDTO, Page page) {
		String[] ids = null;
		Date sqlDate = null;
		String date = NewsEmotionDTO.getDateStr();
		String id = NewsEmotionDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			NewsEmotionDTO.setDate(sqlDate);
		}

		if (!id.isEmpty()) {
			ids = id.split(",");
			NewsEmotionDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, NewsEmotionDTO);
		Page rpsPage = neaService.queryFilterAll(dto);
		String response = JsonUtil.object2Json(rpsPage);
		response = StringUtil.replaceLastErrorStr(response);
		logger.debug(response);
		return response;
	}

	/**
	 * 分页查询行业分类正负面
	 * 
	 * @param NewsEmotionDTO
	 * @param page
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-20
	 */
	@RequestMapping(value = "/queryIndustry", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryIndustry(NewsEmotionDTO NewsEmotionDTO, Page page) {
		String[] ids = null;
		Date sqlDate = null;
		String date = NewsEmotionDTO.getDateStr();
		String id = NewsEmotionDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			NewsEmotionDTO.setDate(sqlDate);
		}

		if (!id.isEmpty()) {
			ids = id.split(",");
			NewsEmotionDTO.setIds(ids);
		}
        
		//System.out.println("new: "+ NewsEmotionDTO.getClassify());
		
		SearchDTO dto = new SearchDTO(page, NewsEmotionDTO);
		Page rpsPage = neaService.queryIndustry(dto);
		String response = JsonUtil.object2Json(rpsPage);
		response = StringUtil.replaceLastErrorStr(response);
		logger.debug(response);
		return response;
	}

	/**
	 * 通过id查询新闻详情
	 * 
	 * @param id
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-20
	 */
	@RequestMapping(value = "/queryContentById", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryContentById(@RequestParam("id") String id) {
		return (String) neaService.queryContentById(id);
	}

	/**
	 * 通过id查询行业分类
	 * 
	 * @param id
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-20
	 */
	@RequestMapping(value = "/queryIndustryById", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryDustById(@RequestParam("id") String id) {
		return (String) neaService.queryIndustryById(id);
	}

	/**
	 * 分页查询微博内容
	 * 
	 * @param NewsEmotionDTO
	 * @param page
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-20
	 */
	@RequestMapping(value = "/queryweibo", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryWeibo(NewsEmotionDTO NewsEmotionDTO, Page page) {
		Date sqlDate = null;
		Date sqlDatePublish = null;
		String[] ids = null;
		String date = NewsEmotionDTO.getDateStr();
		String datePublish = NewsEmotionDTO.getPublishdateStr();
		String id = NewsEmotionDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			NewsEmotionDTO.setDate(sqlDate);
		}

		if (!datePublish.isEmpty()) {
			sqlDatePublish = DateUtil.convertStrDateToSqlDate(datePublish, DateUtil.DATE_FORMAT);
			NewsEmotionDTO.setPublishdate(sqlDatePublish);
		}
		
		if (!id.isEmpty()) {
			ids = id.split(",");
			NewsEmotionDTO.setIds(ids);
		}
		
		SearchDTO dto = new SearchDTO(page, NewsEmotionDTO);
		Page rpsPage = neaService.queryWeibo(dto);
		String response = JsonUtil.object2Json(rpsPage);
		response = StringUtil.replaceLastErrorStr(response);
		logger.debug(response);
		return response;
	}

	@RequestMapping(value = "/queryweibobyid", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryWeiboById(@RequestParam("id") String id) {
		return (String) neaService.queryWeiboById(id);
	}

	/**
	 * 分页查询新闻内容
	 * 
	 * @param NewsEmotionDTO
	 * @param page
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-20
	 */
	@RequestMapping(value = "/querynews", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryNews(NewsEmotionDTO NewsEmotionDTO, Page page) {
		Date sqlDate = null;
		Date sqlDatePublish = null;
		String[] ids = null;
		String date = NewsEmotionDTO.getDateStr();
		String datePublish = NewsEmotionDTO.getPublishdateStr();
		String id = NewsEmotionDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			NewsEmotionDTO.setDate(sqlDate);
		}
		
		if (!datePublish.isEmpty()) {
			sqlDatePublish = DateUtil.convertStrDateToSqlDate(datePublish, DateUtil.DATE_FORMAT);
			NewsEmotionDTO.setPublishdate(sqlDatePublish);
		}
		
		if (!id.isEmpty()) {
			ids = id.split(",");
			NewsEmotionDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, NewsEmotionDTO);
		Page rpsPage = neaService.queryNews(dto);
		String response = JsonUtil.object2Json(rpsPage);
		response = StringUtil.replaceLastErrorStr(response);
		logger.debug(response);
		return response;
	}

	/**
	 * 通过id查询新闻内容
	 * 
	 * @param id
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-20
	 */
	@RequestMapping(value = "/querynewsbyid", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryNewsById(@RequestParam("id") String id) {
		return (String) neaService.queryNewsById(id);
	}


	/**
	 * 行业名称查询
	 * 
	 * @author Darwen
	 * 
	 * */
	@RequestMapping(value = "/queryposornegclassifyname", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryPosOrNegClassifyName(@RequestParam("id") String id) {
		Object list = neaService.queryPosOrNegClassifyName(id);
		logger.debug(list);
		return JsonUtil.toJson(list);
	}
	
	
	/**
	 * 正负面  标题过滤新闻查询
	 * */
	@RequestMapping(value = "/querytitlefilternews", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String querytitlefilternews(NewsEmotionTitleFilterDTO newsEmotionTitleFilterDTO,Page page){
		String[] ids = null;
		Date sqlDate = null;
		Date sqlDatePublish = null;
		String date = newsEmotionTitleFilterDTO.getDateStr();
		String datePublish = newsEmotionTitleFilterDTO.getPublishdateStr();
		String id = newsEmotionTitleFilterDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			newsEmotionTitleFilterDTO.setDate(sqlDate);
		}
		if (!datePublish.isEmpty()) {
			sqlDatePublish = DateUtil.convertStrDateToSqlDate(datePublish, DateUtil.DATE_FORMAT);
			newsEmotionTitleFilterDTO.setPublishdate(sqlDatePublish);
		}
		if (!id.isEmpty()) {
			ids = id.split(",");
			newsEmotionTitleFilterDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, newsEmotionTitleFilterDTO);
		Page rpsPage = neaService.queryTitleFilterNews(dto);
		String response = JsonUtil.object2Json(rpsPage);
		response = StringUtil.replaceLastErrorStr(response);
		logger.debug(response);
		return response;
	}
	
	
	/**
	 * 正负面  标题过滤新闻 通过ID查询其内容
	 * 
	 * */
	@RequestMapping(value = "/queryFilterNewsById", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryFilterNewsById(@RequestParam("id") String id) {
		return (String) neaService.queryFilterNewsById(id);
	}
	
	
	/**
	 * 情感 数据实时日志查询     流入、流出等信息
	 * */
	@RequestMapping(value = "/queryDataLog", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryDataLog(NewsEmotionDataLogDTO newsEmotionDataLogDTO,Page page) {
		String[] ids = null;
		Date sqlDate = null;
		String date = newsEmotionDataLogDTO.getDateStr();
		String id = newsEmotionDataLogDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			newsEmotionDataLogDTO.setDate(sqlDate);
		}
		
		if (!id.isEmpty()) {
			ids = id.split(",");
			newsEmotionDataLogDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, newsEmotionDataLogDTO);
		Page rpsPage = neaService.queryDataLog(dto);
		String response = JsonUtil.object2Json(rpsPage);
		logger.debug(response);
		return response;
	}
	
	
	/**
	 * 情感数据流入、流出量查询
	 * */
	@RequestMapping(value = "/queryVolume", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryVolume(NewsEmotionVolumeDTO newsEmotionVolumeDTO,Page page) {		
		SearchDTO dto = new SearchDTO(page, newsEmotionVolumeDTO);
		Page rpsPage = neaService.queryVolume(dto);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bgeinID", newsEmotionVolumeDTO.getStartID());
		param.put("EndID", newsEmotionVolumeDTO.getEndID());
		int count = tossdi.getData(param);

		List listIn  = rpsPage.getDataRows();
		NewsSentimentVolume in = (NewsSentimentVolume) listIn.get(0);    //list可能为空，考虑
		in.setInflow(count);
		listIn.set(0, in);
		rpsPage.setDataRows(listIn);
		
		
		String response = JsonUtil.object2Json(rpsPage);
		logger.debug(response);
		return response;
	}
	

}
