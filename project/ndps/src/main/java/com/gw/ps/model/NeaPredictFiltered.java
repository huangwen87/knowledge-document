package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

/**
 * 情感过滤掉的新闻
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NeaPredictFiltered")
public class NeaPredictFiltered {
	private long newsCode; // 新闻ID
	private String result; // 分类结果
	private String classNameList;// 类别名称
	private String title;// 标题
	private String content;// 新闻内容
	private String companyNames;// 公司名称
	private boolean isFiltered;// 是否被过滤
	private String source;// 新闻来源
	private String logTime; // 记录时间

	public long getNewsCode() {
		return newsCode;
	}

	public void setNewsCode(long newsCode) {
		this.newsCode = newsCode;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(String companyNames) {
		this.companyNames = companyNames;
	}

	public boolean isFiltered() {
		return isFiltered;
	}

	public void setFiltered(boolean isFiltered) {
		this.isFiltered = isFiltered;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	@Override
	public String toString() {
		return "NeaPredictFiltered [newsCode=" + newsCode + ", result=" + result + ", classNameList=" + classNameList + ", title=" + title + ", content=" + content.length()
				+ ", companyNames=" + companyNames + ", isFiltered=" + isFiltered + ", source=" + source + ", logTime=" + logTime + "]";
	}

}
