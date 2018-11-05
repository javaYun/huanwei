package com.sczh.platform.po.Model;

import java.sql.Timestamp;
import java.util.List;

import com.sczh.platform.po.LjflPrize;
import com.sczh.platform.po.LjflPrizeProvide;
import com.sczh.platform.po.LjflPrizeProvideRecord;

public class LjflPrizePageDomain {
	private String staffDetailId;// 居民

	private String prizeProvideId;// 奖品发放

	private String prizeId;// 奖品

	private String provideStatus;// 发放状态

	private String startTime;// 开始时间

	private String endtime;// 结束时间
	private int id;

	private int pageSize;// 每页大小

	private int totalNum; // 总条数

	private int totalPageNum;// 总页码

	private int currentPage;// 当前页面

	private int endNum;
	
	/**
	 * content 每页的内容
	 */
	private List<LjflPrizeProvide> content;

	private List<LjflPrizeProvideRecord> content1;
	
	private List<LjflPrize> content2;

	// 动态数据库表名
	private String tablename;

	//编码
	private String code;
	
	//名称
	private String name;
	
	
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

	public List<LjflPrizeProvide> getContent() {
		return content;
	}

	public void setContent(List<LjflPrizeProvide> content) {
		this.content = content;
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

	public List<LjflPrizeProvideRecord> getContent1() {
		return content1;
	}

	public void setContent1(List<LjflPrizeProvideRecord> content1) {
		this.content1 = content1;
	}

	public String getStaffDetailId() {
		return staffDetailId;
	}

	public void setStaffDetailId(String staffDetailId) {
		this.staffDetailId = staffDetailId;
	}

	public String getPrizeProvideId() {
		return prizeProvideId;
	}

	public void setPrizeProvideId(String prizeProvideId) {
		this.prizeProvideId = prizeProvideId;
	}

	public String getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

	public String getProvideStatus() {
		return provideStatus;
	}

	public void setProvideStatus(String provideStatus) {
		this.provideStatus = provideStatus;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public List<LjflPrize> getContent2() {
		return content2;
	}

	public void setContent2(List<LjflPrize> content2) {
		this.content2 = content2;
	}

	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	@Override
	public String toString() {
		return "LjflPrizePageDomain [staffDetailId=" + staffDetailId
				+ ", prizeProvideId=" + prizeProvideId + ", prizeId=" + prizeId
				+ ", provideStatus=" + provideStatus + ", startTime="
				+ startTime + ", endtime=" + endtime +  ", id=" + id
				+ ", pageSize=" + pageSize + ", totalNum=" + totalNum
				+ ", totalPageNum=" + totalPageNum + ", currentPage="
				+ currentPage + ", endNum=" + endNum + ", content=" + content
				+ ", content1=" + content1 + ", content2=" + content2
				+ ", tablename=" + tablename + ", code=" + code + ", name="
				+ name + "]";
	}

}
