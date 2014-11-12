package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTimeDust")
public class NewsTimeDust implements BaseNews {
	private int id;
	private long timedust;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimedust() {
		return timedust;
	}

	public void setTimedust(long timedust) {
		this.timedust = timedust;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "NewsTimeDust [id=" + id + ", timedust=" + timedust + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.timedust = Long.parseLong(lm.getText().getLog(0));
		return this;
	}
}
