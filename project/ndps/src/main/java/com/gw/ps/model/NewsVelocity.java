package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 新闻去重速率
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsVelocity")
public class NewsVelocity implements BaseNews {
	private int id;
	private double velocity;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "NewsVelocity [id=" + id + ", velocity=" + velocity + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.velocity = Double.parseDouble(lm.getText().getLog(0));
		return this;
	}

}
