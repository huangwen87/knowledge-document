package com.gw.ncps.dto;

import org.apache.ibatis.type.Alias;

/**
 * @function：信息源查询DTO
 * @author Darwen
 * @data:2013-10-23下午2:35:16
 *
 */
@Alias("NewsDuplicateInfoSourceDTO")
public class NewsDuplicateInfoSourceDTO {
	
	private String dateStr_Start;
	private String dateStr_End;
	private String groupName;
	private String siteName;
	private String channel;
	private String count;
	
	
	
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "NewsDuplicateInfoSourceDTO [dateStr_Start=" + dateStr_Start
				+ ", dateStr_End=" + dateStr_End + ", groupName=" + groupName
				+ ", siteName=" + siteName + ", channel=" + channel
				+ ", count=" + count + "]";
	}


}
