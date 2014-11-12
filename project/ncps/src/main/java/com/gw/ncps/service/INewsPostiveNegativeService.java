package com.gw.ncps.service;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;

public interface INewsPostiveNegativeService {

	Object queryHistory(Object o);

	Object queryContentById(Object o);

	Page queryFilterAll(SearchDTO dto);

	Page queryMatchAll(SearchDTO dto);

	Object queryIndustryById(Object o);

	Page queryWeibo(SearchDTO dto);

	Page queryNews(SearchDTO dto);

	String queryNewsById(String id);

	String queryWeiboById(String id);

	Object queryClassifyName();

	Page queryIndustry(SearchDTO dto);
	
	Object queryPosOrNegClassifyName(String id);
	
	Page queryTitleFilterNews(SearchDTO dto);
	
	Object queryFilterNewsById(Object o);
	
	Page queryDataLog(SearchDTO dto);
	
	Page queryVolume(SearchDTO dto);
}
