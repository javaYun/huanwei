package com.sczh.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.CarInfoMapper;
import com.sczh.platform.dao.DriverInfoMapper;
import com.sczh.platform.po.CarInfo;
import com.sczh.platform.po.DriverInfo;
import com.sczh.platform.service.CdsService;

@Service
public class CdsServiceImpl implements CdsService{

	@Autowired
	private CarInfoMapper carInfoMapper;
	@Autowired
	private DriverInfoMapper driverInfoMapper;
	@Override
	public int insertCarInfo(CarInfo record) {
		return carInfoMapper.insert(record);
	}
	@Override
	public List<CarInfo> getCarInfoList(String startSize, String endSize) {
		return carInfoMapper.getCarInfoList(startSize, endSize);
	}
	@Override
	public int deleteByPrimaryKeys(String ids) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<String> list = new ArrayList<String>();
		String[] idss = ids.split(",");
		for (String str : idss) {
			list.add(str);
		}
		map.put("idsList", list);
		return carInfoMapper.deleteByPrimaryKeys(map);
	}
	@Override
	public CarInfo selectCarInfoByPrimaryKey(String id) {
		return carInfoMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateCarInfoByPrimaryKey(CarInfo record) {
		return carInfoMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<CarInfo> getCarInfoListTotal() {
		return carInfoMapper.getCarInfoListTotal();
	}
	@Override
	public int insertDriverInfo(DriverInfo record) {
		return driverInfoMapper.insert(record);
	}
	@Override
	public List<DriverInfo> getDriverInfoListTotal() {
		return driverInfoMapper.getDriverInfoListTotal();
	}
	@Override
	public List<DriverInfo> getDriverInfoList(String startSize, String endSize) {
		return driverInfoMapper.getDriverInfoList(startSize, endSize);
	}
	@Override
	public int deleteDriverInfoByPrimaryKeys(Map<String, Object> params) {
		return driverInfoMapper.deleteDriverInfoByPrimaryKeys(params);
	}
	@Override
	public DriverInfo selectDriverInfoByPrimaryKey(String id) {
		return driverInfoMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateDriverInfoByPrimaryKey(DriverInfo record) {
		return driverInfoMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<CarInfo> getCarInfoList(String startSize, String endSize, String unitId) {
		return carInfoMapper.getCarInfoListByUnit(startSize, endSize, unitId);
	}
	@Override
	public  List<CarInfo> getCarInfoListTotal(String unitId) {
		return carInfoMapper.getCarInfoListTotalByUnit(unitId);
	}
	@Override
	public List<DriverInfo> getDriverInfoListByUnit(String startSize, String endSize, String unitId) {
		return driverInfoMapper.getDriverInfoListByUnit(startSize, endSize, unitId);
	}
	@Override
	public List<DriverInfo> getDriverInfoListTotalByUnit(String unitId) {
		return driverInfoMapper.getDriverInfoListTotalByUnit(unitId);
	}

}
