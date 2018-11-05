package com.sczh.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.DeptsMapper;
import com.sczh.platform.po.Depts;
import com.sczh.platform.service.DeptsService;

@Service
public class DeptsServiceImpl implements DeptsService{
	
	@Autowired
	private DeptsMapper deptsMapper;

	@Override
	public int insertRegisterDepts(Depts depts) {
		return deptsMapper.insertRegisterDepts(depts);
	}

}
