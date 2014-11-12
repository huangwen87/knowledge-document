package com.gw.ps.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;


public class GetSentimentSampleDaoImpl extends SqlSessionDaoSupport{
	
	private static final String NAMESPACE = "com.gw.ps.dao.INeaPredictDao.";
	
	public int insertData(Object o) {
		return getSqlSession().insert(NAMESPACE + "addNewsSentimentSample", o);
	}
}
