package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

/**
 * @function：去重量、保留量查询 结果类
 * @author Darwen
 * @data:2013-10-23下午4:54:50
 *
 */
@Alias("NewsInfoSourceDuplicateRatio")
public class NewsInfoSourceDuplicateRatio {
	
	private int radio;  //0-去重     1--保留   ……
	private int dupCount;  //量
	private String dupRatioStr; //重比例
	private int totalDupCount;  //所有垃圾
	
	public int getDupCount() {
		return dupCount;
	}
	public void setDupCount(int dupCount) {
		this.dupCount = dupCount;
	}
	public String getDupRatioStr() {
		return dupRatioStr;
	}
	public void setDupRatioStr(String dupRatioStr) {
		this.dupRatioStr = dupRatioStr;
	}
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}

	public int getTotalDupCount() {
		return totalDupCount;
	}
	public void setTotalDupCount(int totalDupCount) {
		this.totalDupCount = totalDupCount;
	}
	@Override
	public String toString() {
		return "NewsInfoSourceDuplicateRatio [radio=" + radio + ", dupCount="
				+ dupCount + ", dupRatioStr=" + dupRatioStr
				+ ", totalDupCount=" + totalDupCount + "]";
	}
}
