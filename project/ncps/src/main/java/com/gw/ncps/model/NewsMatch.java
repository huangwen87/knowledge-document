package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

/**
 * 新闻代码匹配结果
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsMatch")
public class NewsMatch {
	private int matchId;
	private String content;
	private String timestamp;
	private String organCode;

	private String timeIn;
	private String timeOut;

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

}
