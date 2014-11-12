package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ncps.model.NewsLogMessage.LogMessage;

/**
 * Shingle
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsShingle")
public class NewsShingle implements BaseNews {
	private int id;
	private double timeshingle;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTimeshingle() {
		return timeshingle;
	}

	public void setTimeshigle(double timeshingle) {
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
		this.timeshingle = Double.parseDouble(lm.getText().getLog(0));
		return this;
	}

}
