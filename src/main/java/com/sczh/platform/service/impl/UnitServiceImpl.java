package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.UnitInfoMapper;
import com.sczh.platform.po.UnitInfo;
import com.sczh.platform.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService{

	@Autowired
	private UnitInfoMapper unitMapper;

	@Override
	public int deleteByPrimaryKey(String unitcode) {
		return unitMapper.deleteByPrimaryKey(unitcode);
	}

	@Override
	public int insert(UnitInfo record) {
		// TODO Auto-generated method stub
		return unitMapper.insert(record);
	}

	@Override
	public int insertSelective(UnitInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UnitInfo selectByPrimaryKey(String unitcode) {
		// TODO Auto-generated method stub
		return unitMapper.selectByPrimaryKey(unitcode);
	}

	@Override
	public int updateByPrimaryKeySelective(UnitInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UnitInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UnitInfo> selectUnitInfoList() {
		// TODO Auto-generated method stub
		return unitMapper.selectUnitInfoList();
	}

	@Override
	public List<UnitInfo> selectByAreaId(String areaId) {
		return unitMapper.selectByAreaId(areaId);
	}

	@Override
	public List<UnitInfo> selectByArea(String areaId) {
		return unitMapper.selectByArea(areaId);
	}

}
