package com.gw.ncps.model;

import java.util.List;

import com.gw.news.NewsDupRes;

public class NewsDupCoverter {
	protected boolean isdup; // 两篇新闻是否重复
	protected int dis_signal; // 两篇新闻的海明距离

	// 新闻1的相关信息
	protected long signal1; // 指纹值
	protected String flag1; // 重复类型的标记（何种算法判定该新闻是重复的还是不是重复的）
	protected long TimeDust1; // 分词及垃圾过滤的耗时
	protected long TimeWeight1; // 权重的耗时
	protected long TimeSimhash1; // SimHash算法去重的耗时
	protected long TimeShingle1; // Shingle算法去重的耗时
	protected long TimeShingling1; // TimeShingling算法去重的耗时
	protected boolean hasdust1; // 是否有垃圾句子
	protected List<String> dusts1; // 垃圾句子集合

	// 新闻2的相关信息
	protected long signal2; // 指纹值
	protected String flag2; // 重复类型的标记（何种算法判定该新闻是重复的还是不是重复的）
	protected long TimeDust2; // 分词及垃圾过滤的耗时
	protected long TimeWeight2; // 权重的耗时
	protected long TimeSimhash2; // SimHash算法去重的耗时
	protected long TimeShingle2; // Shingle算法去重的耗时
	protected long TimeShingling2; // TimeShingling算法去重的耗时
	protected boolean hasdust2; // 是否有垃圾句子
	protected List<String> dusts2; // 垃圾句子集合

	public boolean isIsdup() {
		return isdup;
	}

	public void setIsdup(boolean isdup) {
		this.isdup = isdup;
	}

	public int getDis_signal() {
		return dis_signal;
	}

	public void setDis_signal(int dis_signal) {
		this.dis_signal = dis_signal;
	}

	public long getSignal1() {
		return signal1;
	}

	public void setSignal1(long signal1) {
		this.signal1 = signal1;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public long getTimeDust1() {
		return TimeDust1;
	}

	public void setTimeDust1(long timeDust1) {
		TimeDust1 = timeDust1;
	}

	public long getTimeWeight1() {
		return TimeWeight1;
	}

	public void setTimeWeight1(long timeWeight1) {
		TimeWeight1 = timeWeight1;
	}

	public long getTimeSimhash1() {
		return TimeSimhash1;
	}

	public void setTimeSimhash1(long timeSimhash1) {
		TimeSimhash1 = timeSimhash1;
	}

	public long getTimeShingle1() {
		return TimeShingle1;
	}

	public void setTimeShingle1(long timeShingle1) {
		TimeShingle1 = timeShingle1;
	}

	public long getTimeShingling1() {
		return TimeShingling1;
	}

	public void setTimeShingling1(long timeShingling1) {
		TimeShingling1 = timeShingling1;
	}

	public boolean isHasdust1() {
		return hasdust1;
	}

	public void setHasdust1(boolean hasdust1) {
		this.hasdust1 = hasdust1;
	}

	public List<String> getDusts1() {
		return dusts1;
	}

	public void setDusts1(List<String> dusts1) {
		this.dusts1 = dusts1;
	}

	public long getSignal2() {
		return signal2;
	}

	public void setSignal2(long signal2) {
		this.signal2 = signal2;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public long getTimeDust2() {
		return TimeDust2;
	}

	public void setTimeDust2(long timeDust2) {
		TimeDust2 = timeDust2;
	}

	public long getTimeWeight2() {
		return TimeWeight2;
	}

	public void setTimeWeight2(long timeWeight2) {
		TimeWeight2 = timeWeight2;
	}

	public long getTimeSimhash2() {
		return TimeSimhash2;
	}

	public void setTimeSimhash2(long timeSimhash2) {
		TimeSimhash2 = timeSimhash2;
	}

	public long getTimeShingle2() {
		return TimeShingle2;
	}

	public void setTimeShingle2(long timeShingle2) {
		TimeShingle2 = timeShingle2;
	}

	public long getTimeShingling2() {
		return TimeShingling2;
	}

	public void setTimeShingling2(long timeShingling2) {
		TimeShingling2 = timeShingling2;
	}

	public boolean isHasdust2() {
		return hasdust2;
	}

	public void setHasdust2(boolean hasdust2) {
		this.hasdust2 = hasdust2;
	}

	public List<String> getDusts2() {
		return dusts2;
	}

	public void setDusts2(List<String> dusts2) {
		this.dusts2 = dusts2;
	}

	public NewsDupCoverter(NewsDupRes ndr) {

		this.dis_signal = ndr.get_dis_signal();
		this.dusts1 = ndr.get_dusts1();
		this.dusts2 = ndr.get_dusts2();
		this.flag1 = ndr.get_flag1();
		this.flag2 = ndr.get_flag2();
		this.hasdust1 = ndr.get_hasdust1();
		this.hasdust2 = ndr.get_hasdust2();
		this.isdup = ndr.get_isdup();
		this.signal1 = ndr.get_signal1();
		this.signal2 = ndr.get_signal2();
		this.TimeDust1 = ndr.get_TimeDust1();
		this.TimeDust2 = ndr.get_TimeDust2();
		this.TimeShingle1 = ndr.get_TimeShingle1();
		this.TimeShingle2 = ndr.get_TimeShingle2();
		this.TimeShingling1 = ndr.get_TimeShingling1();
		this.TimeShingling2 = ndr.get_TimeShingling2();
		this.TimeSimhash1 = ndr.get_TimeSimhash1();
		this.TimeSimhash2 = ndr.get_TimeSimhash2();
		this.TimeWeight1 = ndr.get_TimeWeight1();
		this.TimeWeight2 = ndr.get_TimeWeight2();
	}

	public NewsDupCoverter() {
		super();
	}

}
