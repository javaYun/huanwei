package com.sczh.platform.po.Model;

import java.util.Date;
import java.util.List;

import com.sczh.platform.po.LjflAccountModify;

public class LjflScoreAmendPageDomain {
	private String name;//所属人员
	
	private String modifyUserName;//操作人
	
	private int id;

	private int pageSize;// 每页大小

	private int totalNum; // 总条数

	private int totalPageNum;// 总页码

	private int currentPage;// 当前页面

	/**
	 * content 每页的内容
	 */
	private List<LjflScoreAmendModel> content;

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

	public List<LjflScoreAmendModel> getContent() {
		return content;
	}

	public void setContent(List<LjflScoreAmendModel> content) {
		this.content = content;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	@Override
	public String toString() {
		return "LjflScoreAmendPageDomain [name=" + name + ", modifyUserName="
				+ modifyUserName + ", id=" + id + ", pageSize=" + pageSize
				+ ", totalNum=" + totalNum + ", totalPageNum=" + totalPageNum
				+ ", currentPage=" + currentPage + ", content=" + content + "]";
	}

	
	

}
