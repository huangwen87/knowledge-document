package com.gw.ps.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * 情感新闻
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NeaSentimentNews")
public class NeaSentimentNews {
	private long newsId; // 新闻ID
	private String entryDate; // 流入时间
	private String newsType; // 新闻类型
	private String title;// 标题
	private String text;// 新闻内容
	private String categoryLists;// 其他分类字段
	private int id; //自增id
	private String publishDate; //新闻发布时间
	private List<String> companyIds;
	private List<String> companyNames;
	private List<String> industryIds;
	private List<String> industryNames;
	private String logTime;
	private String result;

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
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

	public String getCategoryLists() {
		return categoryLists;
	}

	public void setCategoryLists(String categoryLists) {
		this.categoryLists = categoryLists;
	}

	public List<String> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<String> companyIds) {
		this.companyIds = companyIds;
	}

	public List<String> getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(List<String> companyNames) {
		this.companyNames = companyNames;
	}

	public List<String> getIndustryIds() {
		return industryIds;
	}

	public void setIndustryIds(List<String> industryIds) {
		this.industryIds = industryIds;
	}

	public List<String> getIndustryNames() {
		return industryNames;
	}

	public void setIndustryNames(List<String> industryNames) {
		this.industryNames = industryNames;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "NeaSentimentNews [newsId=" + newsId + ", entryDate=" + entryDate + ", newsType=" + newsType + ", title=" + title.length() + ", text=" + text.length()
				+ ", categoryLists=" + categoryLists + ", companyIds=" + companyIds + ", companyNames=" + companyNames + ", industryIds=" + industryIds + ", industryNames="
				+ industryNames + ", logTime=" + logTime + ", result=" + result + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	

}
