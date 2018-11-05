package com.yunlao.util;

import java.util.List;

import com.sczh.platform.po.CarInfo;
import com.sczh.platform.po.DeviceInfo;
import com.sczh.platform.po.DriverInfo;

public class PageModelDomain {

	private int id;
	
	private int pageSize ;//每页大小
	
	private int totalNum; // 总条数
	
	private int totalPageNum;//总页码
	
	private int currentPage;//当前页面
	
	private List<CarInfo> carinfos;
	
	private List<DeviceInfo> deviceInfos;
	
	
	
	public List<DeviceInfo> getDeviceInfos() {
		return deviceInfos;
	}

	public void setDeviceInfos(List<DeviceInfo> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

	private List<DriverInfo> driverInfos;

	public List<DriverInfo> getDriverInfos() {
		return driverInfos;
	}

	public void setDriverInfos(List<DriverInfo> driverInfos) {
		this.driverInfos = driverInfos;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

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

	public List<CarInfo> getCarinfos() {
		return carinfos;
	}

	public void setCarinfos(List<CarInfo> carinfos) {
		this.carinfos = carinfos;
	}
}
