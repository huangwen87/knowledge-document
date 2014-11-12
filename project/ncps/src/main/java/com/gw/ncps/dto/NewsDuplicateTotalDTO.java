/**
 * 
 */
package com.gw.ncps.dto;

import org.apache.ibatis.type.Alias;

/**
 * @function：新闻去重量统计查询DTO
 * @author Darwen
 * @data:2013-9-11上午10:12:51
 *
 */
@Alias("NewsDuplicateTotalDTO")
public class NewsDuplicateTotalDTO {
      
	private String[] ids;
	private String id;
	private String dateStr_Start;
	private String dateStr_End;
	
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
	public String getDateStr_Start() {
		return dateStr_Start;
	}
	public void setDateStr_Start(String dateStr_Start) {
		this.dateStr_Start = dateStr_Start;
	}
	public String getDateStr_End() {
		return dateStr_End;
	}
	public void setDateStr_End(String dateStr_End) {
		this.dateStr_End = dateStr_End;
	}

	
}
