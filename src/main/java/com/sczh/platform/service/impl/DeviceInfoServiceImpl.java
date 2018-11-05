package com.sczh.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.DeviceInfoMapper;
import com.sczh.platform.po.DeviceInfo;
import com.sczh.platform.service.DeviceInfoService;
@Service("deviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService {
	@Autowired
	private DeviceInfoMapper deviceInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		return deviceInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DeviceInfo record) {
		return deviceInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(DeviceInfo record) {
		return deviceInfoMapper.insertSelective(record);
	}

	@Override
	public DeviceInfo selectByPrimaryKey(String id) {
		return deviceInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceInfo record) {
		return deviceInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DeviceInfo record) {
		return deviceInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<DeviceInfo> getDeviceInfoList(String startSize, String endSize) {
		return deviceInfoMapper.getDeviceInfoList(startSize, endSize);
	}

	@Override
	public List<DeviceInfo> getDeviceInfoListTotal() {
		return deviceInfoMapper.getDeviceInfoListTotal();
	}

	@Override
	public int deleteDeviceInfoByPrimaryKeys(Map<String, Object> params) {
		return deviceInfoMapper.deleteDeviceInfoByPrimaryKeys(params);
	}

	@Override
	public List<DeviceInfo> getDeviceInfoListByUnit(String string, String string2, String unitId) {
		return deviceInfoMapper.getDeviceInfoListByUnit(string, string2, unitId);
	}

	@Override
	public List<DeviceInfo> getDeviceInfoListTotalByUnit(String unitId) {
		return deviceInfoMapper.getDeviceInfoListTotalByUnit(unitId);
	}

	@Override
	public List<DeviceInfo> getDeviceInfoListTotalByUnitAndDeviceType(String unitId,String deviceType) {
		return deviceInfoMapper.getDeviceInfoListTotalByUnitAndDeviceType(unitId, deviceType);
	}

	@Override
	public List<DeviceInfo> getDeviceInfoListTotalByDeviceType(String deviceType) {
		return deviceInfoMapper.getDeviceInfoListTotalByDeviceType(deviceType);
	}

}
