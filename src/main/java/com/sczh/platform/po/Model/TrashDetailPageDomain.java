package com.sczh.platform.po.Model;

import java.util.List;

import com.sczh.platform.po.LjflTrashCanWorkPoint;

public class TrashDetailPageDomain  {
	
	private int id;
	
	private int pageSize ;//每页大小
	
	private int totalNum; // 总条数
	
	private int totalPageNum;//总页码
	
	private int currentPage;//当前页面
	
    /**
     * content 每页的内容
     */
    private List<LjflTrashCanWorkPoint> content;
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
	public List<LjflTrashCanWorkPoint> getContent() {
		return content;
	}
	public void setContent(List<LjflTrashCanWorkPoint> content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "TrashCleanPageDomain [ id=" + id
				+ ", pageSize=" + pageSize + ", totalNum=" + totalNum
				+ ", totalPageNum=" + totalPageNum + ", currentPage="
				+ currentPage + ", content=" + content + "]";
	}
	
	
	
	

    
    

}
