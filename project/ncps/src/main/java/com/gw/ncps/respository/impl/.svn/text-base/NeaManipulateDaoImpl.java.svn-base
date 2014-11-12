package com.gw.ncps.respository.impl;

import java.util.List;

import com.gw.ncps.dto.NewsDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.INeaManipulateDao;

/**
 * 情感实现类
 * 
 * @author darwen
 * */

public class NeaManipulateDaoImpl extends BaseDaoImpl implements INeaManipulateDao {

	private static final String NAMESPACE = "com.gw.ncps.respository.INeaManipulateDao.";

	@Override
	public Object queryContentById(Object o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryContentById", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Page queryFilterAll(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryFilter", NAMESPACE + "queryFilterCount", dto);
	}

	@Override
	public Object queryHistory(Object o) {
		String tableName = ((NewsDTO) o).getTablename();
		return getSqlSession().selectList(NAMESPACE + "query" + tableName.toLowerCase(), o);
	}

	@Override
	public Object queryIndustryById(Object o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryIndustryById", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Page queryMatchAll(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryMatch", NAMESPACE + "queryMatchCount", dto);
	}

	@Override
	public Page queryWeibo(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryWeibo", NAMESPACE + "queryWeiboCount", dto);
	}

	@Override
	public Page queryNews(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryNews", NAMESPACE + "queryNewsCount", dto);
	}
	
	@Override
	public Page queryIndustry(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryIndustry", NAMESPACE + "queryIndustryCount", dto);
	}

	@Override
	public Object queryNewsById(String id) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryNewsById", id);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryWeiboById(String id) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryWeiboById", id);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryClassifyName() {
		List<?> obj =getSqlSession().selectList(NAMESPACE + "queryNewsClassifyName");
	    if (obj.size() > 0)
			return obj;
	    return null;
	}

	@Override
	public Object queryPosOrNegClassifyName(String id) {
		List<?> obj =getSqlSession().selectList(NAMESPACE + "queryPosOrNegClassifyName",id);
	    if (obj.size() > 0)
			return obj;
	    return null;
	}

	@Override
	public Page queryTitleFilterNews(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryTitleFilterNews", NAMESPACE + "queryTitleFilterNewsCount", dto);  
	}

	@Override
	public Object queryFilterNewsById(Object o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryFilterNewsById", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Page queryDataLog(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryDataLog", NAMESPACE + "queryDataLogCount", dto);
	}


	@Override
	public Page queryVolume(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryVolume", NAMESPACE + "queryVolumeCount", dto);
	}

}
