package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ncps.model.NewsLogMessage.LogMessage;

/**
 * Shingling
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsShingling")
public class NewsShingling implements BaseNews {
	private int id;
	private double timeshingling;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTimeshingling() {
		return timeshingling;
	}

	public void setTimeshigling(double timeshingling) {
		this.timeshingling = timeshingling;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "NewsTimeShingling [id=" + id + ", timeshingling=" + timeshingling + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.timeshingling = Double.parseDouble(lm.getText().getLog(0));
		return this;
	}

}
