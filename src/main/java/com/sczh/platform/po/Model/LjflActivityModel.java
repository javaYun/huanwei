package com.sczh.platform.po.Model;

import java.util.Date;

public class LjflActivityModel {

	private String id;//活动id
	private String name;//活动名称
	private int peoplenum;//刷卡人数
	private String fhousesname;//活动开展小区
	private String activitytypename;//活动类型
	private int minscore;//活动可获得积分
	private Date starttime;//活动开始时间
	private Date endtime;//活动结束时间
	private String scoretype;
	private String st;
	private String et;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPeoplenum() {
		return peoplenum;
	}
	public void setPeoplenum(int peoplenum) {
		this.peoplenum = peoplenum;
	}
	public String getFhousesname() {
		return fhousesname;
	}
	public void setFhousesname(String fhousesname) {
		this.fhousesname = fhousesname;
	}
	public String getActivitytypename() {
		return activitytypename;
	}
	public void setActivitytypename(String activitytypename) {
		this.activitytypename = activitytypename;
	}
	public int getMinscore() {
		return minscore;
	}
	public void setMinscore(int minscore) {
		this.minscore = minscore;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getScoretype() {
		return scoretype;
	}
	public void setScoretype(String scoretype) {
		this.scoretype = scoretype;
	}
	
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getEt() {
		return et;
	}
	public void setEt(String et) {
		this.et = et;
	}
	@Override
	public String toString() {
		return "LjflActivityModel [id=" + id + ", name=" + name
				+ ", peoplenum=" + peoplenum + ", fhousesname=" + fhousesname
				+ ", activitytypename=" + activitytypename + ", minscore="
				+ minscore + ", starttime=" + starttime + ", endtime="
				+ endtime + ", scoretype=" + scoretype + ", st=" + st + ", et="
				+ et + "]";
	}
	
}
