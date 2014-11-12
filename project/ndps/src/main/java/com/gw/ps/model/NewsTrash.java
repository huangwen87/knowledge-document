package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ps.pbbean.PbLogMessage.LogMessage;

/**
 * 新闻去重垃圾收集
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTrash")
public class NewsTrash implements BaseNews {
	private int id;
	private long trash;
	private long timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTrash() {
		return trash;
	}

	public void setTrash(long trash) {
		this.trash = trash;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public NewsTrash(int id, long trash, long timestamp) {
		super();
		this.id = id;
		this.trash = trash;
		this.timestamp = timestamp;
	}

	public NewsTrash() {
		super();
	}

	@Override
	public String toString() {
		return "NewsTrash [id=" + id + ", trash=" + trash + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = Long.parseLong(lm.getText().getTimes());
		this.trash = Long.parseLong(lm.getText().getLog(0));
		return this;
	}
}
