package com.gw.ncps.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.gw.ncps.dto.NewsDuplicateInfoSourceDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.INewsDuplicateRemovalDao;
import com.gw.ncps.service.INewsDuplicateRemovalService;

public class NewsDuplicateRemovalServiceImpl implements INewsDuplicateRemovalService {
	@Resource(name = "ndrDao")
	private INewsDuplicateRemovalDao ndrDao;

	@Override
	public Object queryHistory(Object o) {
		return ndrDao.queryHistory(o);
	}

	@Override
	public Object queryContentById(Object o) {
		String s = "";
		Map<?, ?> map = (Map<?, ?>) ndrDao.queryContentById(o);
		if (null != map) {
			byte[] b = (byte[]) map.get("text");
			try {
				s = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	@Override
	public Object queryDustById(Object o) {
		String s = "";
		Map<?, ?> map = (Map<?, ?>) ndrDao.queryDustById(o);
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
		return ndrDao.queryFilterAll(dto);
	}

	@Override
	public Page queryMatchAll(SearchDTO dto) {
		return ndrDao.queryMatchAll(dto);
	}

	@Override
	public Page queryVolumeIn(SearchDTO dto) {
		return ndrDao.queryVolumeIn(dto);
	}

	@Override
	public Page queryVolumeOut(SearchDTO dto) {
		return ndrDao.queryVolumeOut(dto);
	}

	@Override
	public Object queryDust(Object o) {
		return ndrDao.queryDust(o);
	}

	@Override
	public Page queryDup(SearchDTO dto) {
		// TODO Auto-generated method stub
		return ndrDao.queryDup(dto);
	}

	@Override
	public Page queryFilterInfoSource(SearchDTO dto) {
		return ndrDao.queryFilterInfoSource(dto);
	}

	@Override
	public Object queryFilterInfoSourceDuplicateRatio(NewsDuplicateInfoSourceDTO o) {
		return ndrDao.queryFilterInfoSourceDuplicateRatio(o);
	}

	@Override
	public Object queryFilterInfoSourceUNDuplicateRatio(NewsDuplicateInfoSourceDTO o) {
		return ndrDao.queryFilterInfoSourceUNDuplicateRatio(o);
	}

	@Override
	public Object queryFilterInfoSourceTotal(NewsDuplicateInfoSourceDTO o) {
		return ndrDao.queryFilterInfoSourceTotal(o);
	}

	@Override
	public Object queryFilterInfoSourceContentNULLRatio(NewsDuplicateInfoSourceDTO o) {
		return ndrDao.queryFilterInfoSourceContentNULLRatio(o);
	}

	@Override
	public Object queryFilterInfoSourceDustRatio(NewsDuplicateInfoSourceDTO o) {
		return ndrDao.queryFilterInfoSourceDustRatio(o);
	}

	@Override
	public Object queryFilterInfoSourceTotalDustRatio(NewsDuplicateInfoSourceDTO o) {
		return ndrDao.queryFilterInfoSourceTotalDustRatio(o);
	}

	@Override
	public Object queryContentAll() {
		return ndrDao.queryContentAll();
	}

	@Override
	public Object queryContentFilterAll(Object o) {
		return ndrDao.queryContentFilterAll(o);
	}

}
