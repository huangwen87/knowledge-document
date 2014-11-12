package com.gw.ncps.respository.impl;

import com.gw.ncps.dto.NewsWordDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;
import com.gw.ncps.respository.INdrManipulateDao;

public class NdrManipulateDaoImpl extends BaseDaoImpl implements INdrManipulateDao {

	private static final String NAMESPACE = "com.gw.ncps.respository.INdrManipulateDao.";
	
	@Override
	public int addTrash(Object obj) {
		String tableName = obj.getClass().getSimpleName();
		return getSqlSession().insert(NAMESPACE + "add" + tableName, obj);
	}
	
	@Override
	public int insertWord(Object obj) {
      return getSqlSession().insert(NAMESPACE + "addword" , obj);
	}

	@Override
	public Object queryHistory(Object obj) {
       String tableName = ((NewsWordDTO) obj).getWord();
	   return getSqlSession().selectList(NAMESPACE + "query" + tableName.toLowerCase(), obj);
	}

	@Override
	public Page queryMatchAll(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryMatch", NAMESPACE + "queryMatchCount", dto);
	}

	@Override
	public Page queryMatchAllTrash(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryMatchTrash", NAMESPACE + "queryMatchTrashCount", dto);
	}

	@Override
	public int addEmotionSample(Object obj) {
		return getSqlSession().insert(NAMESPACE + "addNewsEmotionDTO", obj);
	}

	@Override
	public Page queryMatchAllEmotion(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryMatchEmotion", NAMESPACE + "queryMatchEmotionCount", dto);
	}

	@Override
	public Page queryMatchAllEmotionContent(SearchDTO dto) {
		return queryPage(NAMESPACE + "queryMatchEmotionContent", NAMESPACE + "queryMatchEmotionContentCount", dto);
	}

	@Override
	public int addEmotionTitleSample(Object obj) {
		return getSqlSession().insert(NAMESPACE + "addNewsEmotionTitleDTO", obj);
	}

	@Override
	public String delEmotionTitleSampleById(String id) {
		return String.valueOf(getSqlSession().delete(NAMESPACE + "delEmotionTitleSampleById", id));
	}

	@Override
	public String delEmotionContentSampleById(String id) {
		return String.valueOf(getSqlSession().delete(NAMESPACE + "delEmotiontContentSampleById", id));
	}

	@Override
	public String delDuplicateRemovalSampleById(String id) {
		return String.valueOf(getSqlSession().delete(NAMESPACE + "delDuplicateRemovalSampleById", id));
	}

	@Override
	public String delWordLibById(String id) {
		return String.valueOf(getSqlSession().delete(NAMESPACE + "delWordLibById", id));
	}
	
}
