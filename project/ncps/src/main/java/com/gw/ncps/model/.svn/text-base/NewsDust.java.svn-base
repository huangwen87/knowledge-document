
package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ncps.model.NewsLogMessage.LogMessage;

/**
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsDust")
public class NewsDust implements BaseNews {
	private int id;
	private double timedust;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTimedust() {
		return timedust;
	}

	public void setTimedust(double timedust) {
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
		return "Newsdust [id=" + id + ", timedust=" + timedust + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.timedust = Double.parseDouble(lm.getText().getLog(0));
		return this;
	}

}
