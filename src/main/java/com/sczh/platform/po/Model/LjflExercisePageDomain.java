package com.sczh.platform.po.Model;

import java.util.Date;
import java.util.List;

public class LjflExercisePageDomain {
	private String name;//活动名称
	
	private String ljflHousesId;//所属小区
	
	private String startTime;//开始时间
	
	private String endtime;//结束时间
	
	private int id;

	private int pageSize;// 每页大小

	private int totalNum; // 总条数

	private int totalPageNum;// 总页码

	private int currentPage;// 当前页面

	/**
	 * content 每页的内容
	 */
	private List<LjflActivityModel> content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<LjflActivityModel> getContent() {
		return content;
	}

	public void setContent(List<LjflActivityModel> content) {
		this.content = content;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLjflHousesId() {
		return ljflHousesId;
	}

	public void setLjflHousesId(String ljflHousesId) {
		this.ljflHousesId = ljflHousesId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Override
	public String toString() {
		return "LjflExercisePageDomain [name=" + name + ", ljflHousesId="
				+ ljflHousesId + ", starttime=" + startTime + ", endtime="
				+ endtime + ", id=" + id + ", pageSize=" + pageSize
				+ ", totalNum=" + totalNum + ", totalPageNum=" + totalPageNum
				+ ", currentPage=" + currentPage + ", content=" + content + "]";
	}

	

}
