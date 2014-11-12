package com.gw.ncps.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.INeaManipulateDao;
import com.gw.ncps.service.INewsPostiveNegativeService;

public class NeaManipulateServiceImpl implements INewsPostiveNegativeService {
	@Resource(name = "neaDao")
	private INeaManipulateDao neaDao;

	@Override
	public Object queryHistory(Object o) {
		return neaDao.queryHistory(o);
	}

	@Override
	public Object queryContentById(Object o) {
		String s = (String) neaDao.queryContentById(o);
		return s;
	}

	@Override
	public Object queryIndustryById(Object o) {
		String s = "";
		Map<?, ?> map = (Map<?, ?>) neaDao.queryIndustryById(o);
		if (null != map) {
			byte[] b = (byte[]) map.get("dust");
			try {
				return b != null && b.length > 0 ? new String(b, "utf-8") : s;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	@Override
	public Page queryFilterAll(SearchDTO dto) {
		return neaDao.queryFilterAll(dto);
	}

	@Override
	public Page queryMatchAll(SearchDTO dto) {
		return neaDao.queryMatchAll(dto);
	}

	@Override
	public Page queryWeibo(SearchDTO dto) {
		return neaDao.queryWeibo(dto);
	}

	@Override
	public Page queryNews(SearchDTO dto) {
		return neaDao.queryNews(dto);
	}

	@Override
	public String queryNewsById(String id) {
		return (String) neaDao.queryNewsById(id);
	}

	@Override
	public String queryWeiboById(String id) {
		return (String) neaDao.queryWeiboById(id);
	}

	@Override
	public Object queryClassifyName() {
		return neaDao.queryClassifyName();
	}

	@Override
	public Page queryIndustry(SearchDTO dto) {
		return neaDao.queryIndustry(dto);
	}

	@Override
	public Object queryPosOrNegClassifyName(String id) {
		return neaDao.queryPosOrNegClassifyName(id);
	}

	@Override
	public Page queryTitleFilterNews(SearchDTO dto) {
		return neaDao.queryTitleFilterNews(dto);
	}

	@Override
	public Object queryFilterNewsById(Object o) {
		String s = (String) neaDao.queryFilterNewsById(o);
		return s;
	}

	@Override
	public Page queryDataLog(SearchDTO dto) {
		return neaDao.queryDataLog(dto);
	}


	@Override
	public Page queryVolume(SearchDTO dto) {
		return neaDao.queryVolume(dto);
	}

}
