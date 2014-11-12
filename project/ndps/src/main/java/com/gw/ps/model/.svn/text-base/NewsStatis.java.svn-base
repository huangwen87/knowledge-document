package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 新闻去重统计
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsStatis")
public class NewsStatis implements BaseNews {
	private int id;
	private long newsnum;
	private long timestamp;

	public NewsStatis(int id, long newsnum, long timestamp) {
		super();
		this.id = id;
		this.newsnum = newsnum;
		this.timestamp = timestamp;
	}

	public NewsStatis() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LogStatis [id=" + id + ", newsnum=" + newsnum + ", timestamp=" + timestamp + "]";
	}

	public long getNewsnum() {
		return newsnum;
	}

	public void setNewsnum(long newsnum) {
		this.newsnum = newsnum;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.newsnum = Long.parseLong(lm.getText().getLog(0));
		return this;
	}
}
