package com.gw.ps.monitor.yt;

public class TempMessage {
	private String level;
	private String desc;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "TempMessage [level=" + level + ", desc=" + desc + "]";
	}

}
