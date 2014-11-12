package com.gw.ps.dao.impl;


import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TotalDaoImpl extends SqlSessionDaoSupport {

	private static final String NAMESPACE = "com.gw.ps.dao.impl.TotalDaoImpl.";

	public int inflow(Object date) {
		return getSqlSession().selectOne(NAMESPACE + "totalInFlow", date);
	}
	
	public int outflow(Object date) {
		return getSqlSession().selectOne(NAMESPACE + "totalOutFlow", date);
	}
	
	//从王情感数据流入本地数据库
	public int setData(Object obj){
		return getSqlSession().insert(NAMESPACE+"newsSentimentFlowTotal", obj);
	}
	
	//经朱那边处理完流出时间
	public int batchUpdate(Object obj){
		return getSqlSession().update(NAMESPACE + "batchUpdate", obj);
	}
	

}
