package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflStaffDetailMapper;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.service.ResidentService;

@Service
public class ResidentServiceImpl implements ResidentService{

	
	@Autowired
	private LjflStaffDetailMapper ljflStaffDetailMapper;

	@Override
	public List<LjflStaffDetail> selectStaffDetailData(String name,
			String housesId, String communityId, String phone,
			String startSize, String endSize) {
		return ljflStaffDetailMapper.selectStaffDetailData(name, housesId, communityId, phone, startSize, endSize);
	}

	@Override
	public List<LjflStaffDetail> selectStaffDetailDataTotal(String name,
			String housesId, String communityId, String phone) {
		return ljflStaffDetailMapper.selectStaffDetailDataTotal(name, housesId, communityId, phone);
	}

}
