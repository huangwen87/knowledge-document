package com.gw.ps.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.gw.ps.model.NewsSentimentSample;

public class TaskOnSqlServerGetSentimentSampleDaoImpl {

	private static final String NAMESPACE = "com.gw.ps.sqlserversample.";
	
	@Resource(name = "sqlSession-2")
	private SqlSession mySqlSession;

	public List<NewsSentimentSample> getData(String param) {
		List<NewsSentimentSample> list = mySqlSession.selectList(NAMESPACE + "querysample", param);
		return list;
	}

}
