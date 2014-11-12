package com.gw.ncps.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 新闻分词样本
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsWordDTO")
public class NewsWordDTO {
	
	
	private String word;
	private String wordStr;
	private String[] ids;
	private String id;
	private Date date;
	private String dateStr;
	private String content;
	private String timestamp;
	
	
	
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public NewsWordDTO()
	{}
	
	public NewsWordDTO(String word)
	{
	   this.word = word;
	}
	
	public NewsWordDTO(String word, String wordStr)
	{
	   this.word = word;
	   this.wordStr = wordStr;
	}
	
	public NewsWordDTO(String word, Date date)
	{
	   this.word = word;
	   this.date = date;
	}
	
	
	
	
	public String getWordStr() {
		return wordStr;
	}
	public void setWordStr(String wordStr) {
		this.wordStr = wordStr;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	@Override
	public String toString() {
		return "NewsWordDTO [word=" + word + ", wordStr=" + wordStr + "]";
	}
	


}
