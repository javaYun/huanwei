package com.sczh.platform.po;

public class Et100Model {
	
	private String msgCode;
	private String occurTime;
	private Object params;//数据
	private String sourceDeviceId;
	private String sourceDeviceType;
	private String tag;
	private String targetDeviceId;
	private String targetDeviceType;
	
	
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}


	public String getSourceDeviceId() {
		return sourceDeviceId;
	}
	public void setSourceDeviceId(String sourceDeviceId) {
		this.sourceDeviceId = sourceDeviceId;
	}
	public String getSourceDeviceType() {
		return sourceDeviceType;
	}
	public void setSourceDeviceType(String sourceDeviceType) {
		this.sourceDeviceType = sourceDeviceType;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTargetDeviceId() {
		return targetDeviceId;
	}
	public void setTargetDeviceId(String targetDeviceId) {
		this.targetDeviceId = targetDeviceId;
	}
	public String getTargetDeviceType() {
		return targetDeviceType;
	}
	public void setTargetDeviceType(String targetDeviceType) {
		this.targetDeviceType = targetDeviceType;
	}
	public Object getParams() {
		return params;
	}
	public void setParams(Object params) {
		this.params = params;
	}

	
	
}
