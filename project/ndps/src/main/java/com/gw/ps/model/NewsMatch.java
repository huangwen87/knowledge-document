package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

/**
 * 新闻代码匹配结果
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsMatch")
public class NewsMatch {
	private long matchId;
	private String content;
	private String timestamp;
	private String organCode;

	private String timeIn;
	private String timeOut;

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
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

	@Override
	public String toString() {
		return "NewsMatch [matchId=" + matchId + ", content=" + content + ", timestamp=" + timestamp + ", organCode=" + organCode + ", timeIn=" + timeIn + ", timeOut=" + timeOut
				+ "]";
	}

}
