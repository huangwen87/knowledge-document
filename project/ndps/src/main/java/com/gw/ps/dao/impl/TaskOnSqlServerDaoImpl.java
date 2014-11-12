package com.gw.ps.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

public class TaskOnSqlServerDaoImpl {

	private static final String NAMESPACE = "com.gw.ps.sqlserver.";
	@Resource(name = "sqlSession-1")
	private SqlSession mySqlSession;

	public int setData(Object obj) {
		return mySqlSession.insert(NAMESPACE + "addResult", obj);
	}

	public int getCount(Object obj) {
		return mySqlSession.selectOne(NAMESPACE + "getCount", obj);
	}

	public int update(Object obj) {
		return mySqlSession.update(NAMESPACE + "update", obj);
	}

}
