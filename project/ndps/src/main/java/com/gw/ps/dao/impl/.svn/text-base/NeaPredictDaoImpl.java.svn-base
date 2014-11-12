package com.gw.ps.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gw.ps.dao.INeaPredictDao;

public class NeaPredictDaoImpl extends SqlSessionDaoSupport implements INeaPredictDao {

	private static final String NAMESPACE = "com.gw.ps.dao.INeaPredictDao.";

	@Override
	public int addNewsClass(Object obj) {
		return getSqlSession().insert(NAMESPACE + "add" + obj.getClass().getSimpleName(), obj);
	}

	@Override
	public int addNewsSentiClass(Object obj) {
		return getSqlSession().insert(NAMESPACE + "addNewsSentiClass", obj);
	}

	@Override
	public int addNewsSenanaIndcls(Object obj) {
		return getSqlSession().insert(NAMESPACE + "addNewsSenanaIndcls", obj);
	}

	@Override
	public int addNewsSenanaFiltered(Object obj) {
		return getSqlSession().insert(NAMESPACE + "addNewsSenanaFiltered", obj);
	}

	@Override
	public int addNewsSentestSample(Object obj) {
		return getSqlSession().insert(NAMESPACE + "addNewsSentestSample", obj);
	}


	@Override
	public int addNewsSenWeiboSample(Object obj) {
		return getSqlSession().insert(NAMESPACE + "addNewsSenWeiboSample", obj);
	}

}
