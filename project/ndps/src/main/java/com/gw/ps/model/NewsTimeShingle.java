package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTimeShingle")
public class NewsTimeShingle implements BaseNews {
	private int id;
	private long timeshingle;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimeshingle() {
		return timeshingle;
	}

	public void setTimeshingle(long timeshingle) {
		this.timeshingle = timeshingle;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "NewsTimeShingle [id=" + id + ", timeshingle=" + timeshingle + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.timeshingle = Long.parseLong(lm.getText().getLog(0));
		return this;
	}
}
