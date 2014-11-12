package com.gw.ps.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CleanDaoImpl extends SqlSessionDaoSupport {

	private static final String NAMESPACE = "com.gw.ps.dao.NdrCleanDaoImpl.";

	public int clean(Object tableName, Object date) {
		return getSqlSession().delete(NAMESPACE + tableName, date);
	}
	
	public int update(Object tableName, String str) {
		return getSqlSession().update(NAMESPACE + tableName, str);
	}

	public int query(Object tableName) {
		return getSqlSession().selectOne(NAMESPACE + tableName);
	}
}
