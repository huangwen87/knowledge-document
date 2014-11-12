package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTimeWeight")
public class NewsTimeWeight implements BaseNews {
	private int id;
	private long timeweight;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimeweight() {
		return timeweight;
	}

	public void setTimeweight(long timeweight) {
		this.timeweight = timeweight;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "NewsTimeWeight [id=" + id + ", timeweight=" + timeweight + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.timeweight = Long.parseLong(lm.getText().getLog(0));
		return this;
	}
}
