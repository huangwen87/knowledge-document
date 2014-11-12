package com.gw.ncps.respository;

import com.gw.ncps.dto.NewsDuplicateInfoSourceDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;

public interface INewsDuplicateRemovalDao {

	public Object queryContentById(Object obj);

	public Object queryDustById(Object obj);

	public Page queryFilterAll(SearchDTO dto);

	public int addNewsClass(Object obj);

	public Page queryMatchAll(SearchDTO dto);

	public Object queryHistory(Object o);
	
	public Page queryVolumeIn(SearchDTO dto);
	
	public Page queryVolumeOut(SearchDTO dto);
	
	public Object queryDust(Object o);
	
	public Page queryDup(SearchDTO dto);
	
	public Page queryFilterInfoSource(SearchDTO dto);
	
	public Object queryFilterInfoSourceTotal(NewsDuplicateInfoSourceDTO o);
	
	public Object queryFilterInfoSourceDuplicateRatio(NewsDuplicateInfoSourceDTO o);
	
	public Object queryFilterInfoSourceUNDuplicateRatio(NewsDuplicateInfoSourceDTO dto);
	
	public Object queryFilterInfoSourceContentNULLRatio(NewsDuplicateInfoSourceDTO o);
	
	public Object queryFilterInfoSourceDustRatio(NewsDuplicateInfoSourceDTO o);
	
	public Object queryFilterInfoSourceTotalDustRatio(NewsDuplicateInfoSourceDTO o);
	
	public Object queryContentAll();
	
	public Object queryContentFilterAll(Object o);
	
}
