
package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ncps.model.NewsLogMessage.LogMessage;

/**
 * 新闻去重比例
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsWeight")
public class NewsWeight implements BaseNews {
	private int id;
	private double weight;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "NewsWeight [id=" + id + ", weight=" + weight + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.weight = Double.parseDouble(lm.getText().getLog(0));
		return this;
	}

}
