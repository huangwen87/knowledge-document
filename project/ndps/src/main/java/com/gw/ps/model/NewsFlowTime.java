/**
 * 
 */
package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

/**
 * @function：开始、结束时间
 * @author Darwen
 * @data:2013-9-5下午2:00:55
 *
 */
@Alias("NewsFlowTime")
public class NewsFlowTime {
	
	private String dateStr_start;
	private String dateStr_end;
	private int inflow;
	private int outflow;
	
	
	public String getDateStr_start() {
		return dateStr_start;
	}
	public void setDateStr_start(String dateStr_start) {
		this.dateStr_start = dateStr_start;
	}
	public String getDateStr_end() {
		return dateStr_end;
	}
	public void setDateStr_end(String dateStr_end) {
		this.dateStr_end = dateStr_end;
	}
	public int getInflow() {
		return inflow;
	}
	public void setInflow(int inflow) {
		this.inflow = inflow;
	}
	public int getOutflow() {
		return outflow;
	}
	public void setOutflow(int outflow) {
		this.outflow = outflow;
	}
	
	
	

}
