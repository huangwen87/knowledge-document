package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTimeShingling")
public class NewsTimeShingling implements BaseNews {
	private int id;
	private long timeshingling;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimeshingling() {
		return timeshingling;
	}

	public void setTimeshingling(long timeshingling) {
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
		this.timeshingling = Long.parseLong(lm.getText().getLog(0));
		return this;
	}
}
