package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.SignInRecordMapper;
import com.sczh.platform.po.SignInRecord;
import com.sczh.platform.service.SignInRecordService;

@Service
public class SignInRecordServiceImpl implements SignInRecordService{

	@Autowired
	private SignInRecordMapper signinRecordMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(SignInRecord record) {
		// TODO Auto-generated method stub
		return signinRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(SignInRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SignInRecord selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SignInRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SignInRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SignInRecord> selectListSignInList(String startSize,
			String endSize, String time) {
		// TODO Auto-generated method stub
		return signinRecordMapper.selectListSignInList(startSize, endSize, time);
	}

	@Override
	public List<SignInRecord> selectListSignInListTotal(String time) {
		// TODO Auto-generated method stub
		return signinRecordMapper.selectListSignInListTotal(time);
	}	
}
