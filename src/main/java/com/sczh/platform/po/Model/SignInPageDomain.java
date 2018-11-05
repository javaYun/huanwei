package com.sczh.platform.po.Model;

import java.util.Date;
import java.util.List;

import com.sczh.platform.po.SignInRecord;

public class SignInPageDomain  {

	private Date time;//查询时间
	
	private int id;
	
	private int pageSize ;//每页大小
	
	private int totalNum; // 总条数
	
	private int totalPageNum;//总页码
	
	private int currentPage;//当前页面
    /**
     * content 每页的内容
     */
    private List<SignInRecord> content;
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
	public List<SignInRecord> getContent() {
		return content;
	}
	public void setContent(List<SignInRecord> content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "SignInPageDomain [time=" + time + ", id=" + id + ", pageSize="
				+ pageSize + ", totalNum=" + totalNum + ", totalPageNum="
				+ totalPageNum + ", currentPage=" + currentPage + ", content="
				+ content + "]";
	}
}
