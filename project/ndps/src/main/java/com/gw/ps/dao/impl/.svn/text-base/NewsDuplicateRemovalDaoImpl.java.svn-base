package com.gw.ps.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gw.ps.dao.INewsDuplicateRemovalDao;

public class NewsDuplicateRemovalDaoImpl extends SqlSessionDaoSupport implements INewsDuplicateRemovalDao {

	private static final String NAMESPACE = "com.gw.ps.dao.INewsDuplicateRemovalDao.";

	@Override
	public int addNewsClass(Object obj) {
		return getSqlSession().insert(NAMESPACE + "add" + obj.getClass().getSimpleName().split("News")[1].toLowerCase(), obj);
	}

}
