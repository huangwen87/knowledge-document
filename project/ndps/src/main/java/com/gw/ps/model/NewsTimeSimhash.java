package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 新闻去重统计
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTimeSimhash")
public class NewsTimeSimhash implements BaseNews {
	private int id;
	private long timesimhash;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimesimhash() {
		return timesimhash;
	}

	public void setTimesimhash(long timesimhash) {
		this.timesimhash = timesimhash;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "NewsTimeSimhash [id=" + id + ", timesimhash=" + timesimhash + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.timesimhash = Long.parseLong(lm.getText().getLog(0));
		return this;
	}
}
