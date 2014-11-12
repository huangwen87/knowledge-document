package com.gw.ncps.dto;

import org.apache.ibatis.type.Alias;

/**
 * @function：情感数据源  查询 
 * @author Darwen
 * @data:2013-10-16下午2:39:11
 *
 */
@Alias("NewsEmotionVolumeDTO")
public class NewsEmotionVolumeDTO {

	private long startID;   //开始源自增ID
	private long endID;     //结束源自增ID
	
	public long getStartID() {
		return startID;
	}
	public void setStartID(long startID) {
		this.startID = startID;
	}
	public long getEndID() {
		return endID;
	}
	public void setEndID(long endID) {
		this.endID = endID;
	}
	
	@Override
	public String toString() {
		return "NewsEmotionVolumeDTO [startID=" + startID + ", endID=" + endID
				+ "]";
	}

}
