package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflStaffDetailMapper;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.service.LjflStaffDetailService;

@Service
public class LjflStaffDetailServiceImpl implements LjflStaffDetailService {

	@Autowired
	private LjflStaffDetailMapper ljflstaffdetailmapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(LjflStaffDetail record) {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.insert(record);
	}

	@Override
	public int insertSelective(LjflStaffDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LjflStaffDetail selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LjflStaffDetail record) {
		return ljflstaffdetailmapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(LjflStaffDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(LjflStaffDetail record) {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LjflStaffDetail> selectStaffDetailInfoList(String startSize,
			String endSize) {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.selectStaffDetailInfoList(startSize, endSize);
	}

	@Override
	public List<LjflStaffDetail> selectStaffDetailInfoListTotal() {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.selectStaffDetailInfoListTotal();
	}

	@Override
	public List<LjflStaffDetail> selectByName(String name) {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.selectByName(name);
	}

	@Override
	public List<LjflStaffDetail> selectStaffDetailList(
			PageModelDomain pageModelDomain) {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.selectStaffDetailList(pageModelDomain);
	}

	@Override
	public int selecStaffDetailtTotalSize(PageModelDomain pageModelDomain) {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.selecStaffDetailtTotalSize(pageModelDomain);
	}

	@Override
	public LjflStaffDetail selectByPhone(String phone) {
		// TODO Auto-generated method stub
		return ljflstaffdetailmapper.selectByPhone(phone);
	}

}
