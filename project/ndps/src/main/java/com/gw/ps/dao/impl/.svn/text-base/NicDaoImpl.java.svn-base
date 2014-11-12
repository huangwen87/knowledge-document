package com.gw.ps.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gw.ps.dao.INicDao;

public class NicDaoImpl extends SqlSessionDaoSupport implements INicDao {

	private static final String NAMESPACE = "com.gw.ps.dao.INicDao.";

	@Override
	public Integer save(Object obj) {
		return getSqlSession().insert(NAMESPACE + "save" + obj.getClass().getSimpleName(), obj);
	}

	@Override
	public Integer queryCountById(Object obj) {
		return getSqlSession().selectOne(NAMESPACE + "queryCountById" + obj.getClass().getSimpleName(), obj);
	}

	@Override
	public Integer updateById(Object obj) {
		return getSqlSession().update(NAMESPACE + "updateById" + obj.getClass().getSimpleName(), obj);
	}

}
