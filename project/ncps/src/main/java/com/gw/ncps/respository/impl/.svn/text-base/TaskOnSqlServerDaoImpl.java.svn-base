package com.gw.ncps.respository.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

public class TaskOnSqlServerDaoImpl {

	private static final String NAMESPACE = "com.gw.ps.sqlserver.";
	@Resource(name = "sqlSession-1")
	private SqlSession mySqlSession;

	public int getData(Map<?, ?> param) {
		int count = mySqlSession.selectOne(NAMESPACE + "newsextractforemotion", param);
		return count;
	}


}
