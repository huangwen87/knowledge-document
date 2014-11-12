package com.gw.ncps.respository.impl;

import java.util.List;

import com.gw.ncps.dto.NewsDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.INicManipulateDao;


/**
 * 情感实现类
 * @author darwen
 * */

public class NicManipulateDaoImpl extends BaseDaoImpl implements INicManipulateDao {

	private static final String NAMESPACE = "com.gw.ncps.respository.INicManipulateDao.";
	
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
	public Page queryClassifySample(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryClassifySample", NAMESPACE + "queryClassifySampleCount", dto);
	}

	@Override
	public String delClassifySampleById(String id) {
		return String.valueOf(getSqlSession().delete(NAMESPACE + "delClassifySampleById", id));
	}

	@Override
	public Object queryClassifyName() {
	    List<?> obj =getSqlSession().selectList(NAMESPACE + "queryClassifyName");
	    if (obj.size() > 0)
			return obj;
	    return null;
	}

}
