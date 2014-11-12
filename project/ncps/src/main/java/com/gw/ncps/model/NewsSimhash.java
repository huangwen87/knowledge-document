package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ncps.model.NewsLogMessage.LogMessage;

/**
 * Shingling
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsSimhash")
public class NewsSimhash implements BaseNews {
	private int id;
	private double timesimhash;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTimesimhash() {
		return timesimhash;
	}

	public void setTimesimhash(double timesimhash) {
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
		this.timesimhash = Double.parseDouble(lm.getText().getLog(0));
		return this;
	}

}
