package com.gw.ncps.model;


/**
 * @function：
 * @author Darwen
 * @data:2013-11-8下午2:38:06
 *
 */
public class NewsTotal {
	
	private int count;   //新闻总量  ---key相同的   
	private int dupCount;   //去重量    即 isdup=1
	private int resCount;   //保留量   即isdup=0;
	private int ctxnCount;  //正文空量  即 dustflag=0;
	private int pureDustCount;  //纯垃圾量  即 dustflag=1;
	private int allDustCount;  //所有垃圾量  即 dustflag=2;
	
	
	private String dupRatio;  //去重量百分比
	private String resRatio;  //保留量百分比
	private String ctxnRatio;  //正文空量百分比
	private String pureRatio;  //纯垃圾百分比
	
	private String groupName;
	private String siteName;
	private String channel;
	private String trssource;
	private String time;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getDupCount() {
		return dupCount;
	}
	public void setDupCount(int dupCount) {
		this.dupCount = dupCount;
	}
	public int getResCount() {
		return resCount;
	}
	public void setResCount(int resCount) {
		this.resCount = resCount;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTrssource() {
		return trssource;
	}
	public void setTrssource(String trssource) {
		this.trssource = trssource;
	}
	public int getCtxnCount() {
		return ctxnCount;
	}
	public void setCtxnCount(int ctxnCount) {
		this.ctxnCount = ctxnCount;
	}
	public int getPureDustCount() {
		return pureDustCount;
	}
	public void setPureDustCount(int pureDustCount) {
		this.pureDustCount = pureDustCount;
	}
	public String getDupRatio() {
		return dupRatio;
	}
	public void setDupRatio(String dupRatio) {
		this.dupRatio = dupRatio;
	}
	public String getResRatio() {
		return resRatio;
	}
	public void setResRatio(String resRatio) {
		this.resRatio = resRatio;
	}
	public String getCtxnRatio() {
		return ctxnRatio;
	}
	public void setCtxnRatio(String ctxnRatio) {
		this.ctxnRatio = ctxnRatio;
	}
	public String getPureRatio() {
		return pureRatio;
	}
	public void setPureRatio(String pureRatio) {
		this.pureRatio = pureRatio;
	}
	public int getAllDustCount() {
		return allDustCount;
	}
	public void setAllDustCount(int allDustCount) {
		this.allDustCount = allDustCount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "NewsTotal [count=" + count + ", dupCount=" + dupCount
				+ ", resCount=" + resCount + ", ctxnCount=" + ctxnCount
				+ ", pureDustCount=" + pureDustCount + ", allDustCount="
				+ allDustCount + ", dupRatio=" + dupRatio + ", resRatio="
				+ resRatio + ", ctxnRatio=" + ctxnRatio + ", pureRatio="
				+ pureRatio + ", groupName=" + groupName + ", siteName="
				+ siteName + ", channel=" + channel + ", trssource="
				+ trssource + ", time=" + time + "]";
	}

	

}
