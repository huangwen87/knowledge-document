package com.gw.ncps.service;

import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;

public interface INdrManipulateService {
	int addTrash(Object obj);
	int addEmotionSample(Object obj);
	int addEmotionTitleSample(Object obj);
	Object queryHistory(Object o);
	Object insertWord(Object o);
	Page queryMatchAll(SearchDTO dto);
	Page queryMatchAllTrash(SearchDTO dto);
	Page queryMatchAllEmotion(SearchDTO dto);
	Page queryMatchAllEmotionContent(SearchDTO dto);
	
	public String delEmotiontTitleSampleById(String id);
	public String delEmotionContentSampleById(String id);
	public String delDuplicateRemovalSampleById(String id);
	public String delWordLibById(String id);
}
