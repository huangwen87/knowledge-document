package com.gw.ncps.dto;

import org.apache.ibatis.type.Alias;

@Alias("NewsDTO")
public class NewsDTO {
	private String tablename;
	private long timestamp;

	public NewsDTO(String tableName) {
		this.tablename = tableName;
	}

	public NewsDTO() {
	}

	public NewsDTO(String tableName, long timestamp) {
		this.tablename = tableName;
		this.timestamp = timestamp;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
