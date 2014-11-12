package com.gw.ncps.model;

import java.util.List;

/**
 * 列表页面对象
 * 
 * 
 */
public class Page {

	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 总页数
	 */
	private int total;

	/**
	 * 页大小
	 */
	private int rows;

	/**
	 * 总条数
	 */
	private int records;

	/**
	 * 数据列表
	 */
	private List<?> dataRows = null;

	private int startRowNum;

	private int endRowNum;
	/**
	 * 排序字段
	 */
	private String sidx;
	/**
	 * 排序方式
	 */
	private String sord;
	

	public Page() {
	}

	/**
	 * @param page
	 *            当前页
	 * @param rows
	 *            页大小
	 * @param total
	 *            总页数
	 * @param records
	 *            总记录数
	 * @param dataRows
	 *            数据列表
	 */
	public Page(int page, int rows, int total, int records, List<?> dataRows) {
		this.page = page;
		this.rows = rows;
		this.total = total;
		this.records = records;
		this.dataRows = dataRows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<?> getDataRows() {
		return dataRows;
	}

	public void setDataRows(List<?> dataRows) {
		this.dataRows = dataRows;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}
}
