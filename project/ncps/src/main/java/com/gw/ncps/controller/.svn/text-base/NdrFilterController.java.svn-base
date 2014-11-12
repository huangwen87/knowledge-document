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
import com.gw.ncps.dto.NewsFilterDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.service.INewsDuplicateRemovalService;

/**
 * 过滤结果
 * 
 * @author JohnmyWork
 * 
 */
@Controller
@RequestMapping(value = "/ndr/filter")
public class NdrFilterController {
	private final Logger logger = LogUtil.getInstance(this.getClass());
	@Resource(name = "ndrService")
	private INewsDuplicateRemovalService ndrService;

	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String query(NewsFilterDTO newsFilterDTO, Page page) {
		String[] ids = null;
		Date sqlDate = null;
		String date = newsFilterDTO.getDateStr();
		String id = newsFilterDTO.getId();
		if (!date.isEmpty()) {
			sqlDate = DateUtil.convertStrDateToSqlDate(date, DateUtil.DATE_FORMAT);
			newsFilterDTO.setDate(sqlDate);
		}

		if (!id.isEmpty()) {
			ids = id.split(",");
			newsFilterDTO.setIds(ids);
		}

		SearchDTO dto = new SearchDTO(page, newsFilterDTO);
		Page rpsPage = ndrService.queryFilterAll(dto);
		String response = JsonUtil.object2Json(rpsPage);
		logger.debug(response);
		return response;
	}

	@RequestMapping(value = "/queryContentById", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryContentById(@RequestParam("id") String id) {
		return (String) ndrService.queryContentById(id);
	}

	@RequestMapping(value = "/queryDustById", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String queryDustById(@RequestParam("id") String id) {
		return (String) ndrService.queryDustById(id);
	}
}
