package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

/**
 * 新闻正负面预判
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NeaPredict")
public class NeaPredict {
	private long newsCode; // 新闻ID
	private int sentiment; // 新闻情感识别结果，-1表示负面，1表示正面，0不确定
	private double posRate; // 新闻是正面的概率
	private double negRate; // 新闻是负面的概率
	private long processTime; // 处理这篇新闻所用的时间
	private String logTime; // 记录时间
	private String title;// 标题
	private String text;// 新闻内容
	private String result; // 具体分类分数
	private String classNameList;// 公司分类名称
	private String companyNames;// 公司
	
	//为了王安宇那边，队列多下面几个字段
	private String source;// 新闻流出模块
	private String dataFrom;// 新闻数据来源
	private String publishdate; //新闻发布时间
	private String accessTime;
	private boolean isFiltered;

	public long getNewsCode() {
		return newsCode;
	}

	public void setNewsCode(long newsCode) {
		this.newsCode = newsCode;
	}

	public int getSentiment() {
		return sentiment;
	}

	public void setSentiment(int sentiment) {
		this.sentiment = sentiment;
	}

	public double getPosRate() {
		return posRate;
	}

	public void setPosRate(double posRate) {
		this.posRate = posRate;
	}

	public double getNegRate() {
		return negRate;
	}

	public void setNegRate(double negRate) {
		this.negRate = negRate;
	}

	public long getProcessTime() {
		return processTime;
	}

	public void setProcessTime(long processTime) {
		this.processTime = processTime;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getClassNameList() {
		return classNameList;
	}

	public void setClassNameList(String classNameList) {
		this.classNameList = classNameList;
	}

	public String getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(String companyNames) {
		this.companyNames = companyNames;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public boolean isFiltered() {
		return isFiltered;
	}

	public void setFiltered(boolean isFiltered) {
		this.isFiltered = isFiltered;
	}

	@Override
	public String toString() {
		return "NeaPredict [newsCode=" + newsCode + ", sentiment=" + sentiment
				+ ", posRate=" + posRate + ", negRate=" + negRate
				+ ", processTime=" + processTime + ", logTime=" + logTime
				+ ", title=" + title + ", text=" + text + ", result=" + result
				+ ", classNameList=" + classNameList + ", companyNames="
				+ companyNames + ", source=" + source + ", dataFrom="
				+ dataFrom + ", publishdate=" + publishdate + ", accessTime="
				+ accessTime + ", isFiltered=" + isFiltered + "]";
	}
	
}
