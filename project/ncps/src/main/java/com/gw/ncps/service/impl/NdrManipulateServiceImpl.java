package com.gw.ncps.service.impl;

import javax.annotation.Resource;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.INdrManipulateDao;
import com.gw.ncps.service.INdrManipulateService;

public class NdrManipulateServiceImpl implements INdrManipulateService {
	@Resource(name = "ndrManipulateDao")
	private INdrManipulateDao ndrManipulateDao;

	@Override
	public int addTrash(Object obj) {
		return ndrManipulateDao.addTrash(obj);
	}

	@Override
	public Object insertWord(Object o) {
		return ndrManipulateDao.insertWord(o);
	}

	@Override
	public Object queryHistory(Object o) {
		return ndrManipulateDao.queryHistory(o);
	}

	@Override
	public Page queryMatchAll(SearchDTO dto) {
		return ndrManipulateDao.queryMatchAll(dto);
	}

	@Override
	public Page queryMatchAllTrash(SearchDTO dto) {
		return ndrManipulateDao.queryMatchAllTrash(dto);
	}

	@Override
	public int addEmotionSample(Object obj) {
		return ndrManipulateDao.addEmotionSample(obj);
	}

	@Override
	public Page queryMatchAllEmotion(SearchDTO dto) {
		return ndrManipulateDao.queryMatchAllEmotion(dto);
	}

	@Override
	public Page queryMatchAllEmotionContent(SearchDTO dto) {
		return ndrManipulateDao.queryMatchAllEmotionContent(dto);
	}

	@Override
	public int addEmotionTitleSample(Object obj) {
		return ndrManipulateDao.addEmotionTitleSample(obj);
	}

	@Override
	public String delEmotiontTitleSampleById(String id) {
		return ndrManipulateDao.delEmotionTitleSampleById(id);
	}


	@Override
	public String delEmotionContentSampleById(String id) {
	    return ndrManipulateDao.delEmotionContentSampleById(id);
	}

	@Override
	public String delDuplicateRemovalSampleById(String id) {
		return ndrManipulateDao.delDuplicateRemovalSampleById(id);
	}

	@Override
	public String delWordLibById(String id) {
		return ndrManipulateDao.delWordLibById(id);
	}

}
