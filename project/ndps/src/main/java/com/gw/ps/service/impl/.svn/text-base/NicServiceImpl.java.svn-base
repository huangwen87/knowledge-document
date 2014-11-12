package com.gw.ps.service.impl;

import javax.annotation.Resource;

import com.gw.ps.dao.INicDao;
import com.gw.ps.service.INicService;

public class NicServiceImpl implements INicService {

	@Resource(name = "nicDao")
	private INicDao nicDao;

	public int addNewsClass(Object obj) {

//		if (nicDao.queryCountById(obj) != 0) {
//			return nicDao.updateById(obj);
//		} else {
			return nicDao.save(obj);
//		}

	}

}
