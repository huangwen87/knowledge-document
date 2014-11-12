package com.gw.ncps.respository.impl;

import java.util.List;

import com.gw.ncps.dto.NewsDTO;
import com.gw.ncps.dto.NewsDuplicateInfoSourceDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.INewsDuplicateRemovalDao;

public class NewsDuplicateRemovalDaoImpl extends BaseDaoImpl implements INewsDuplicateRemovalDao {

	private static final String NAMESPACE = "com.gw.ncps.respository.INewsDuplicateRemovalDao.";

	@Override
	public Object queryHistory(Object obj) {
		String tableName = ((NewsDTO) obj).getTablename();
		return getSqlSession().selectList(NAMESPACE + "query" + tableName.toLowerCase(), obj);
	}

	@Override
	public Object queryContentById(Object o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryContentById", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryDustById(Object o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryDustById", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Page queryFilterAll(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryFilter", NAMESPACE + "queryFilterCount", dto);
	}

	@Override
	public int addNewsClass(Object obj) {
		return getSqlSession().insert(NAMESPACE + "add" + obj.getClass().getSimpleName().split("News")[1].toLowerCase(), obj);
	}

	@Override
	public Page queryMatchAll(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryMatch", NAMESPACE + "queryMatchCount", dto);
	}

	@Override
	public Page queryVolumeIn(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryVolumeIn", NAMESPACE + "queryVolumeInCount", dto);
	}

	@Override
	public Page queryVolumeOut(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryVolumeOut", NAMESPACE + "queryVolumeOutCount", dto);
	}


	@Override
	public Object queryDust(Object o) {
		return getSqlSession().selectList(NAMESPACE+"queryDust",o);
	}


	@Override
	public Page queryDup(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryDup", NAMESPACE + "queryDupCount", dto);
	}

	@Override
	public Page queryFilterInfoSource(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryFilterInfoSource", NAMESPACE + "queryFilterInfoSourceCount", dto);
	}

	@Override
	public Object queryFilterInfoSourceDuplicateRatio(NewsDuplicateInfoSourceDTO o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryFilterInfoSourceDuplicateRatio", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryFilterInfoSourceUNDuplicateRatio(NewsDuplicateInfoSourceDTO o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryFilterInfoSourceUNDuplicateRatio", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryFilterInfoSourceTotal(NewsDuplicateInfoSourceDTO o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryFilterInfoSourceTotal", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryFilterInfoSourceContentNULLRatio(NewsDuplicateInfoSourceDTO o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryFilterInfoSourceContentNULLRatio", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryFilterInfoSourceDustRatio(NewsDuplicateInfoSourceDTO o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryFilterInfoSourceDustRatio", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryFilterInfoSourceTotalDustRatio(NewsDuplicateInfoSourceDTO o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryFilterInfoSourceTotalDustRatio", o);
		if (obj.size() > 0)
			return obj.get(0);
		return null;
	}

	@Override
	public Object queryContentAll() {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryContentAll");
	    return obj;
	}

	@Override
	public Object queryContentFilterAll(Object o) {
		List<?> obj = getSqlSession().selectList(NAMESPACE + "queryContentFilterAll",o);
	    return obj;
	}
}
