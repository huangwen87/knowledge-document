package com.gw.ncps.respository;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;

/**
 * 情感接口
 * 
 * @author darwen
 * */

public interface INeaManipulateDao {

	Object queryContentById(Object obj);

	Object queryIndustryById(Object obj);

	Page queryFilterAll(SearchDTO dto);

	Page queryMatchAll(SearchDTO dto);

	Object queryHistory(Object o);

	Page queryWeibo(SearchDTO dto);

	Page queryNews(SearchDTO dto);

	Object queryNewsById(String id);

	Object queryWeiboById(String id);

	Object queryClassifyName();

	Page queryIndustry(SearchDTO dto);
	
	Object queryPosOrNegClassifyName(String id);
	
	Page queryTitleFilterNews(SearchDTO dto);
	
	Object queryFilterNewsById(Object o);
	
	Page queryDataLog(SearchDTO dto);
	
	Page queryVolume(SearchDTO dto);
}
