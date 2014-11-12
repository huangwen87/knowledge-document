package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

/**
 * 新闻去重统计
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTimeDust")
public class NewsTimeDust implements BaseNews {
	private int id;
	private long newsnum;
	private long timestamp;

	public NewsTimeDust(int id, long newsnum, long timestamp) {
		super();
		this.id = id;
		this.newsnum = newsnum;
		this.timestamp = timestamp;
	}

	public NewsTimeDust() {
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
	public BaseNews manipulate(NewsLogMessage.LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.newsnum = Long.parseLong(lm.getText().getLog(0));
		return this;
	}
}
