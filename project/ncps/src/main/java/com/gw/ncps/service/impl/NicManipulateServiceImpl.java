package com.gw.ncps.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.INicManipulateDao;
import com.gw.ncps.service.INewsIndustryClassifyService;

public class NicManipulateServiceImpl implements INewsIndustryClassifyService {
	@Resource(name = "nicDao")
	private INicManipulateDao nicDao;

	@Override
	public Object queryHistory(Object o) {
		return nicDao.queryHistory(o);
	}

	@Override
	public Object queryContentById(Object o) {
		String s = (String) nicDao.queryContentById(o);
		return s;
	}

	@Override
	public Object queryIndustryById(Object o) {
		String s = "";
		Map<?, ?> map = (Map<?, ?>) nicDao.queryIndustryById(o);
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
		return nicDao.queryFilterAll(dto);
	}

	@Override
	public Page queryMatchAll(SearchDTO dto) {
		return nicDao.queryMatchAll(dto);
	}

	@Override
	public Page queryClassifySample(SearchDTO dto) {
		return nicDao.queryClassifySample(dto);
	}

	@Override
	public String delClassifySampleById(String id) {
		return nicDao.delClassifySampleById(id);
	}

	@Override
	public Object queryClassifyName() {
		return nicDao.queryClassifyName();
	}


}
