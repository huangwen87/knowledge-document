package com.gw.ncps.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

/**
 * 新闻代码匹配结果查询DTO
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsCodeDTO")
public class NewsMatchDTO {
	private String[] ids;
	private String id;
	private Date date;
	private String dateStr;
	private String content;
	private String organCode;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

}
