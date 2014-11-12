package com.gw.ncps.respository;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;

/**
 * 分类接口
 * @author darwen
 * */

public interface INicManipulateDao {
	
	public Object queryContentById(Object obj);

	public Object queryIndustryById(Object obj);

	public Page queryFilterAll(SearchDTO dto);
	
	public Page queryMatchAll(SearchDTO dto);

	public Object queryHistory(Object o);

	public Page queryClassifySample(SearchDTO dto);

	public String delClassifySampleById(String id);
	
	public Object queryClassifyName();

}
