package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

@Alias("NewsSignal")
public class NewsSignal {
	private long id;
	private long signal;
	private String time;
	private String slevel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSignal() {
		return signal;
	}

	public void setSignal(long signal) {
		this.signal = signal;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSlevel() {
		return slevel;
	}

	public void setSlevel(String slevel) {
		this.slevel = slevel;
	}

	@Override
	public String toString() {
		return "NewsSignal [id=" + id + ", signal=" + signal + ", time=" + time
				+ ", slevel=" + slevel + "]";
	}

}
