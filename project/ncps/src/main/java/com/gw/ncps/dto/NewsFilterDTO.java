package com.gw.ncps.dto;

import java.sql.Date;
import java.util.Arrays;

import org.apache.ibatis.type.Alias;

/**
 * 新闻过滤结果查询DTO
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsFilterDTO")
public class NewsFilterDTO {
	private String[] ids;
	private String id;
	private Date date;
	private String dateStr;
	private String isdup;
	private String isdust;
	private String title;

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

	public String getIsdup() {
		return isdup;
	}

	public void setIsdup(String isdup) {
		this.isdup = isdup;
	}

	public String getIsdust() {
		return isdust;
	}

	public void setIsdust(String isdust) {
		this.isdust = isdust;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
