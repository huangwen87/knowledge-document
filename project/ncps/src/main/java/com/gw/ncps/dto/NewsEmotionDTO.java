package com.gw.ncps.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

/**
 * 新闻情感查询
 * 
 * @author Darwen
 * */
@Alias("NewsEmotionDTO")
public class NewsEmotionDTO {

	private String[] ids;
	private String id;
	private Date date;
	private String dateStr;
	private String posORneg;
	private String title;
	private String classify;
	private String organization;
	private Date publishdate;
	private String publishdateStr;

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getPosORneg() {
		return posORneg;
	}

	public void setPosORneg(String posORneg) {
		this.posORneg = posORneg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Date getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}

	public String getPublishdateStr() {
		return publishdateStr;
	}

	public void setPublishdateStr(String publishdateStr) {
		this.publishdateStr = publishdateStr;
	}
	
	

}
