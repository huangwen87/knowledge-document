package com.gw.ncps.dto;

import java.sql.Date;
import java.util.Arrays;

import org.apache.ibatis.type.Alias;

/**
 * @function：情感 数据源实时日志   
 * @author Darwen
 * @data:2013-10-15下午3:04:40
 *
 */
@Alias("NewsEmotionDataLogDTO")
public class NewsEmotionDataLogDTO {
	
	private String[] ids;
	private String id;
	private Date date;
	private String dateStr;
	
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
	
	@Override
	public String toString() {
		return "NewsEmotionDataLogDTO [ids=" + Arrays.toString(ids) + ", id="
				+ id + ", date=" + date + ", dateStr=" + dateStr + "]";
	}
	
	

}
