package com.gw.ps.model;

import org.apache.ibatis.type.Alias;

/**
 * 新闻过滤结果
 * 
 * @author JohnmyWork
 * 
 */
@Alias("NewsFilter")
public class NewsFilter {
	private long filterId;
	private long originId;
	private String content;
	private String timestamp;
	private boolean isdup;
	private boolean istrash;
	private boolean iscd;
	private String dupflag;
	private long signal;
	private String title;
	private String dust;

	private String timeIn;
	private String timeOut;
	private String timedesc;

	private String groupName; // 网站
	private String siteName; // 频道
	private String channel; // 栏目
	private String webgrab; //是否定点抓取  
	private String trssource; //TRS来源
	private String dustflag;  //垃圾类型
	private String slevel;   //信源同类优先级
	
	public long getFilterId() {
		return filterId;
	}

	public void setFilterId(long filterId) {
		this.filterId = filterId;
	}

	public long getOriginId() {
		return originId;
	}

	public void setOriginId(long originId) {
		this.originId = originId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isIsdup() {
		return isdup;
	}

	public void setIsdup(boolean isdup) {
		this.isdup = isdup;
	}

	public boolean isIstrash() {
		return istrash;
	}

	public void setIstrash(boolean istrash) {
		this.istrash = istrash;
	}

	public boolean isIscd() {
		return iscd;
	}

	public void setIscd(boolean iscd) {
		this.iscd = iscd;
	}

	public String getDupflag() {
		return dupflag;
	}

	public void setDupflag(String dupflag) {
		this.dupflag = dupflag;
	}

	public long getSignal() {
		return signal;
	}

	public void setSignal(long signal) {
		this.signal = signal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDust() {
		return dust;
	}

	public void setDust(String dust) {
		this.dust = dust;
	}

	public String getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getTimedesc() {
		return timedesc;
	}

	public void setTimedesc(String timedesc) {
		this.timedesc = timedesc;
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

	public String getWebgrab() {
		return webgrab;
	}

	public void setWebgrab(String webgrab) {
		this.webgrab = webgrab;
	}

	public String getTrssource() {
		return trssource;
	}

	public void setTrssource(String trssource) {
		this.trssource = trssource;
	}

	public String getDustflag() {
		return dustflag;
	}

	public void setDustflag(String dustflag) {
		this.dustflag = dustflag;
	}

	public String getSlevel() {
		return slevel;
	}

	public void setSlevel(String slevel) {
		this.slevel = slevel;
	}

	@Override
	public String toString() {
		return "NewsFilter [filterId=" + filterId + ", originId=" + originId
				+ ", content=" + content + ", timestamp=" + timestamp
				+ ", isdup=" + isdup + ", istrash=" + istrash + ", iscd="
				+ iscd + ", dupflag=" + dupflag + ", signal=" + signal
				+ ", title=" + title + ", dust=" + dust + ", timeIn=" + timeIn
				+ ", timeOut=" + timeOut + ", timedesc=" + timedesc
				+ ", groupName=" + groupName + ", siteName=" + siteName
				+ ", channel=" + channel + ", webgrab=" + webgrab
				+ ", trssource=" + trssource + ", dustflag=" + dustflag
				+ ", slevel=" + slevel + "]";
	}

    
}
