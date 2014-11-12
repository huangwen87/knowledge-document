package com.gw.ps.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.gw.ps.model.NeaSentimentNews;

public class TaskOnSqlServerGetDataDaoImpl {

	private static final String NAMESPACE = "com.gw.ps.sqlserver.";
	@Resource(name = "sqlSession-3")
	private SqlSession mySqlSession;

	public List<NeaSentimentNews> getData(Map<?, ?> param) {
		List<NeaSentimentNews> list = mySqlSession.selectList(NAMESPACE + "newsextractforemotion", param);
		return list;
	}

}
