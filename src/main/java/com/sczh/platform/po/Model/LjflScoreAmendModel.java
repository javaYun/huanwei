package com.sczh.platform.po.Model;

import java.util.Date;

public class LjflScoreAmendModel {

	private String id;//id
	private String name;//所属人员
	private Double fAddScore;//积分增（减）
	private String fModifyUserName;//操作人名称
	private String fMemo;//备注
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
	public Double getfAddScore() {
		return fAddScore;
	}
	public void setfAddScore(Double fAddScore) {
		this.fAddScore = fAddScore;
	}
	public String getfModifyUserName() {
		return fModifyUserName;
	}
	public void setfModifyUserName(String fModifyUserName) {
		this.fModifyUserName = fModifyUserName;
	}
	public String getfMemo() {
		return fMemo;
	}
	public void setfMemo(String fMemo) {
		this.fMemo = fMemo;
	}
	@Override
	public String toString() {
		return "LjflScoreAmendModel [id=" + id + ", name=" + name
				+ ", fAddScore=" + fAddScore + ", fModifyUserName="
				+ fModifyUserName + ", fMemo=" + fMemo + "]";
	}
	
	
}
