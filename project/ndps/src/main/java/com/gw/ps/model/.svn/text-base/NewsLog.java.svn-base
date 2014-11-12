package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsLog")
public class NewsLog implements BaseNews {
	private String className;
	private String type;
	private String level;
	private String times;
	private String log;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Override
	public Object manipulate(LogMessage lm) {
		this.className = lm.getText().getClassName();
		this.level = lm.getText().getLevel();
		this.log = lm.getText().getLog(0);
		this.times = lm.getText().getTimes();
		this.type = lm.getText().getType();
		return this;
	}

}
