package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.PersonInfoMapper;
import com.sczh.platform.po.PersonInfo;
import com.sczh.platform.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonInfoMapper personMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return personMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PersonInfo record) {
		// TODO Auto-generated method stub
		return personMapper.insert(record);
	}

	@Override
	public int insertSelective(PersonInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PersonInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return personMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PersonInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(PersonInfo record) {
		// TODO Auto-generated method stub
		return personMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PersonInfo> selectPersonInfoList() {
		// TODO Auto-generated method stub
		return personMapper.selectPersonInfoList();
	}

	@Override
	public List<PersonInfo> selectPersonInfoList1(String unitname,
			String departmentname, String startSize, String endSize) {
		// TODO Auto-generated method stub
		return personMapper.selectPersonInfoList1(unitname, departmentname,
				startSize, endSize);
	}

	@Override
	public List<PersonInfo> selectPersonInfoList2(String startSize,
			String endSize) {
		// TODO Auto-generated method stub
		return personMapper.selectPersonInfoList2(startSize, endSize);
	}

	@Override
	public List<PersonInfo> selectPersonInfoListTotal(String unitname,
			String departmentname) {
		// TODO Auto-generated method stub
		return personMapper.selectPersonInfoListTotal(unitname, departmentname);
	}

	@Override
	public PersonInfo selectByUserId(String userId) {
		return personMapper.selectByUserId(userId);
	}

	@Override
	public List<PersonInfo> selectByUnit(String unitname) {
		return personMapper.selectByUnit(unitname);
	}

	@Override
	public List<PersonInfo> selectByUnitandDepartment(String unitname,
			String departmentname) {
		return personMapper.selectByUnitandDepartment(unitname, departmentname);
	}

}
