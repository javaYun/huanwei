package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.DepartmentInfoMapper;
import com.sczh.platform.po.DepartmentInfo;
import com.sczh.platform.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentInfoMapper departmentMapper;

	@Override
	public int deleteByPrimaryKey(String departmentcode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DepartmentInfo record) {
		// TODO Auto-generated method stub
		return departmentMapper.insert(record);
	}

	@Override
	public int insertSelective(DepartmentInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DepartmentInfo selectByPrimaryKey(String departmentcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DepartmentInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DepartmentInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DepartmentInfo> selectDepartmentinfoList() {
		// TODO Auto-generated method stub
		return departmentMapper.selectDepartmentinfoList();
	}

	@Override
	public List<DepartmentInfo> selectByUnitCode(String unitcode) {
		return departmentMapper.selectByUnitCode(unitcode);
	}

	@Override
	public DepartmentInfo selectByDepartmentCode(String departmentcode) {
		return departmentMapper.selectByDepartmentCode(departmentcode);
	}

}
