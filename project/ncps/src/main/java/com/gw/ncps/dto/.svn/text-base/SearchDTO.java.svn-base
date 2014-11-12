package com.gw.ncps.dto;

import com.gw.ncps.model.Page;


/**
 * 查询DTO
 * 
 * 
 */
public class SearchDTO {

	/**
	 * 当前页
	 */
	private int pageNo;

	/**
	 * 页大小
	 */
	private int pageSize;

	/**
	 * 排序字段名
	 */
	private String sortKey;

	/**
	 * 排序方式
	 */
	private String sortType;

	/**
	 * 总行数
	 */
	private String totalCount;

	/**
	 * 查询条件
	 */
	private Object obj;

	private int startRowNum;

	private int endRowNum;

	public SearchDTO() {
		this.pageNo=1;
		this.pageSize=15;
	}

	public SearchDTO(int pageNo, int pageSize, String sortKey, String sortType, Object obj) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.sortKey = sortKey;
		this.sortType = sortType;
		this.obj = obj;
		this.startRowNum = (pageNo-1) * pageSize +1;
		this.endRowNum = pageNo * pageSize;
	}
	
	public SearchDTO(Page page, Object obj) {
		super();
		this.pageNo = page.getPage();
		this.pageSize = page.getRows();
		this.sortKey = page.getSidx();
		this.sortType = page.getSord();
		this.obj = obj;
		this.startRowNum = (pageNo-1) * pageSize +1;
		this.endRowNum = pageNo * pageSize;
	}



	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortKey() {
		return sortKey;
	}

	public void setSortKey(String sortKey) {
		this.sortKey = sortKey;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
