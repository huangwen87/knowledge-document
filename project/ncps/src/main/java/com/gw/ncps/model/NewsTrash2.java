package com.gw.ncps.model;

import org.apache.ibatis.type.Alias;

import com.gw.ncps.model.NewsLogMessage.LogMessage;

/**
 * 新闻去重垃圾收集
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTrash2")
public class NewsTrash2 implements BaseNews {
	private int id;
	private String trash;
	private String timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrash() {
		return trash;
	}

	public void setTrash(String trash) {
		this.trash = trash;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public NewsTrash2(int id, String trash, String timestamp) {
		super();
		this.id = id;
		this.trash = trash;
		this.timestamp = timestamp;
	}

	public NewsTrash2() {
		super();
	}

	@Override
	public String toString() {
		return "NewsTrash [id=" + id + ", trash=" + trash + ", timestamp=" + timestamp + "]";
	}

	@Override
	public BaseNews manipulate(LogMessage lm) {
		this.timestamp = lm.getText().getTimes();
		this.trash = lm.getText().getLog(0);
		return this;
	}
}
