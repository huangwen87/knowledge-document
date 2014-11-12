package com.gw.ncps.controller;

import java.sql.Date;

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
import com.gw.ncps.dto.NewsIndustryDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.service.INewsIndustryClassifyService;

/**
 * 行业分类
 * 
 * @author darwen
 * */

@Controller
@RequestMapping(value = "nic/industry")
public class NicIndustryController {

	private final Logger logger = LogUtil.getInstance(this.getClass());
	@Resource(name = "nicService")
	private INewsIndustryClassifyService nicService;

	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String query(NewsIndustryDTO newsIndustryDTO, Page page) {
		String[] ids = null;
		Date sqlDate = null;
		String date = newsIndustryDTO.getDateStr();
		String id = newsIndustryDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			newsIndustryDTO.setDate(sqlDate);
		}

		if (!id.isEmpty()) {
			ids = id.split(",");
			newsIndustryDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, newsIndustryDTO);
		Page rpsPage = nicService.queryFilterAll(dto);
		String response = JsonUtil.object2Json(rpsPage);
		response = StringUtil.replaceLastErrorStr(response);
		logger.debug(response);
		return response;
	}

	@RequestMapping(value = "/queryContentById", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryContentById(@RequestParam("id") String id) {
		return (String) nicService.queryContentById(id);
	}

	@RequestMapping(value = "/queryIndustryById", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryDustById(@RequestParam("id") String id) {
		return (String) nicService.queryIndustryById(id);
	}

	/**
	 * 分页查询分类样本
	 * 
	 * @param newsIndustryDTO
	 * @param page
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-5
	 */
	@RequestMapping(value = "/queryclassifysample", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryClassifySample(NewsIndustryDTO newsIndustryDTO, Page page) {
		String[] ids = null;
		String id = newsIndustryDTO.getId();

		if (!id.isEmpty()) {
			ids = id.split(",");
			newsIndustryDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, newsIndustryDTO);
		Page rpsPage = nicService.queryClassifySample(dto);
		String response = JsonUtil.object2Json(rpsPage);
		logger.debug(response);
		return response;
	}

	/**
	 * 删除样本
	 * 
	 * @param id
	 * @return String
	 * @author JohnmyWork
	 * @date 2013-8-6
	 */
	@RequestMapping(value = "/delclassifysamplebyid", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String delClassifySampleById(@RequestParam("id") String id) {
		String result = nicService.delClassifySampleById(id);
		logger.debug(result);
		return result;
	}

}
