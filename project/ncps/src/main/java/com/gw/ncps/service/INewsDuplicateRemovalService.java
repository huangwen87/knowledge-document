package com.gw.ncps.service;

import com.gw.ncps.dto.NewsDuplicateInfoSourceDTO;
import com.gw.ncps.dto.SearchDTO;
import com.gw.ncps.model.Page;

public interface INewsDuplicateRemovalService {

	Object queryHistory(Object o);

	Object queryContentById(Object o);

	Page queryFilterAll(SearchDTO dto);

	Page queryMatchAll(SearchDTO dto);

	Object queryDustById(Object o);
	
	Page queryVolumeIn(SearchDTO dto);
	
	Page queryVolumeOut(SearchDTO dto);
	
    Object queryDust(Object o);
    
    Page queryDup(SearchDTO dto);
    
	Page queryFilterInfoSource(SearchDTO dto);
	
	Object queryFilterInfoSourceTotal(NewsDuplicateInfoSourceDTO o);
	
	Object queryFilterInfoSourceDuplicateRatio(NewsDuplicateInfoSourceDTO o);
	
	Object queryFilterInfoSourceUNDuplicateRatio(NewsDuplicateInfoSourceDTO dto);
	
	Object queryFilterInfoSourceDustRatio(NewsDuplicateInfoSourceDTO o);
	
	Object queryFilterInfoSourceTotalDustRatio(NewsDuplicateInfoSourceDTO o);
	
	Object queryFilterInfoSourceContentNULLRatio(NewsDuplicateInfoSourceDTO o);
	
	Object queryContentAll();
	
	Object queryContentFilterAll(Object o);

}
