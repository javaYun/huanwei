package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.OMenuinfoMapper;
import com.sczh.platform.po.OMenuinfo;
import com.sczh.platform.service.OMenuInfoService;

@Service
public class OMenuInfoServiceImpl implements OMenuInfoService{

	@Autowired
	private OMenuinfoMapper omenuinfoMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer oid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OMenuinfo record) {
		// TODO Auto-generated method stub
		return omenuinfoMapper.insert(record);
	}

	@Override
	public int insertSelective(OMenuinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OMenuinfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return omenuinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OMenuinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OMenuinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OMenuinfo> selectOMenuInfoList() {
		// TODO Auto-generated method stub
		return omenuinfoMapper.selectOMenuInfoList();
	}

	@Override
	public List<OMenuinfo> selectByPid(String pid) {
		return omenuinfoMapper.selectByPid(pid);
	}

	
	
	
}
