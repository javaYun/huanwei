package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflCleanMapper;
import com.sczh.platform.po.LjflClean;
import com.sczh.platform.service.LjflcleanService;

@Service
public class LjflcleanServiceImpl implements LjflcleanService{

	@Autowired
	private LjflCleanMapper ljflcleanMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(LjflClean record) {
		// TODO Auto-generated method stub
		return ljflcleanMapper.insert(record);
	}

	@Override
	public int insertSelective(LjflClean record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LjflClean selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(LjflClean record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(LjflClean record) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public int deleteLjflClean() {
		// TODO Auto-generated method stub
		return ljflcleanMapper.deleteLjflClean();
	}

	@Override
	public List<LjflClean> selectLjflCleanInfoList(String startSize,
			String endSize, String time) {
		// TODO Auto-generated method stub
		return ljflcleanMapper.selectLjflCleanInfoList(startSize, endSize, time);
	}

	@Override
	public List<LjflClean> selectLjflCleanInfoListTotal(String time) {
		// TODO Auto-generated method stub
		return ljflcleanMapper.selectLjflCleanInfoListTotal(time);
	}
	
	

}
