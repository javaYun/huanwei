package com.sczh.platform.po;

public class Tub01 {
	private String connected;//连接状态
	private String fuleId;//燃料id
	private String heightValue;//油耗高度
	private String latitude;//纬度
	private String longitude;//经度
	private String msgCode;
	private String occurTime;//发生时间
	private String params;
	private String sourceDeviceId;
	private String sourceDeviceType;
	private String yingJianType;
	private String rate;//速度  米/s
	
	private String gps_description;//GPS方向 0~359.99°范围  正北为0°
	
	private String str1;
	
	private String str2;
	
	private String str3;
	

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getGps_description() {
		return gps_description;
	}

	public void setGps_description(String gps_description) {
		this.gps_description = gps_description;
	}

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public String getFuleId() {
		return fuleId;
	}

	public void setFuleId(String fuleId) {
		this.fuleId = fuleId;
	}

	public String getConnected() {
		return connected;
	}

	public void setConnected(String connected) {
		this.connected = connected;
	}

	public String getHeightValue() {
		return heightValue;
	}

	public void setHeightValue(String heightValue) {
		this.heightValue = heightValue;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

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

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
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

	public String getYingJianType() {
		return yingJianType;
	}

	public void setYingJianType(String yingJianType) {
		this.yingJianType = yingJianType;
	}
    

 
}