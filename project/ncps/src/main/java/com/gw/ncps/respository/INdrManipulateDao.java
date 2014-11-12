package com.gw.ncps.respository;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;

public interface INdrManipulateDao {
	int addTrash(Object obj);
	int addEmotionSample(Object obj);
	int addEmotionTitleSample(Object obj);
	int insertWord(Object obj);
	Object queryHistory(Object o);
	public Page queryMatchAll(SearchDTO dto);
	public Page queryMatchAllTrash(SearchDTO dto);
	public Page queryMatchAllEmotion(SearchDTO dto);
	public Page queryMatchAllEmotionContent(SearchDTO dto);
	
	public String delEmotionTitleSampleById(String id);
	public String delEmotionContentSampleById(String id);
	public String delDuplicateRemovalSampleById(String id);
	public String delWordLibById(String id);
}
