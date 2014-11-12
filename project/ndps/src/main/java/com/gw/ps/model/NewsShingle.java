package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

@Alias("NewsShingle")
public class NewsShingle {
	private long id;
	private String shingle;
	private String time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShingle() {
		return shingle;
	}

	public void setShingle(String shingle) {
		this.shingle = shingle;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "NewsShingle [id=" + id + ", shingle=" + shingle + ", time=" + time + "]";
	}

}
