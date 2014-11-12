package com.gw.ncps.dto;

import org.apache.ibatis.type.Alias;

/**
 * 新闻垃圾样本
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsTrashDTO")
public class NewsTrashDTO {
	private String dateStr;
	private String trash;

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getTrash() {
		return trash;
	}

	public void setTrash(String trash) {
		this.trash = trash;
	}

	@Override
	public String toString() {
		return "NewsTrashDTO [dateStr=" + dateStr + ", trash=" + trash + "]";
	}

}
