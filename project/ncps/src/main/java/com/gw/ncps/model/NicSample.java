package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

/**
 * 新闻行业分类结果
 * 
 * @author Darwen
 * */

@Alias("NicSample")
public class NicSample {

	private int id;
	private String title;
	private String news;
	private String industry;
	private String timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "NicSample [id=" + id + ", title=" + title + ", news=" + news + ", industry=" + industry + ", timestamp=" + timestamp + "]";
	}

}
