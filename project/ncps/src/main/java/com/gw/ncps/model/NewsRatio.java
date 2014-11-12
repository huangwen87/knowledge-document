package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ncps.model.NewsLogMessage.LogMessage;

/**
 * 新闻去重比例
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsRatio")
public class NewsRatio implements BaseNews {
	private int id;
	private double ratio;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Newsratio [id=" + id + ", ratio=" + ratio + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.ratio = Double.parseDouble(lm.getText().getLog(0));
		return this;
	}

}
