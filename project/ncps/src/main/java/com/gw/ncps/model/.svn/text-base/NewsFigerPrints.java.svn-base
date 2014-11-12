package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ncps.model.NewsLogMessage.LogMessage;

/**
 * 指纹数量
 * @author darwen
 * */
@Alias("NewsFigerPrints")
public class NewsFigerPrints implements BaseNews {
	
	private int id;
	private String log;
	private long timestamp;
	

	@Override
	public Object manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.log = lm.getText().getLog(0);
		return this;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLog() {
		return log;
	}


	public void setLog(String log) {
		this.log = log;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "NewsFigerPrints [id=" + id + ", log=" + log + ", timestamp="
				+ timestamp + "]";
	}

}
