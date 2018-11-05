package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.LjflAccountModifyMapper;
import com.sczh.platform.po.LjflAccountModify;
import com.sczh.platform.service.LjflAccountModifyService;

@Service
public class LjflAccountModifyServiceImpl implements LjflAccountModifyService{

	@Autowired
	private LjflAccountModifyMapper ljflaccountmodifymapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ljflaccountmodifymapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LjflAccountModify record) {
		// TODO Auto-generated method stub
		return ljflaccountmodifymapper.insert(record);
	}

	@Override
	public int insertSelective(LjflAccountModify record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LjflAccountModify selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ljflaccountmodifymapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LjflAccountModify record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(LjflAccountModify record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LjflAccountModify> selectAccoountModityInfoList(
			String modifyusername) {
		// TODO Auto-generated method stub
		return ljflaccountmodifymapper.selectAccoountModityInfoList(modifyusername);
	}

	@Override
	public List<LjflAccountModify> selectAccoountModityInfoList1() {
		// TODO Auto-generated method stub
		return ljflaccountmodifymapper.selectAccoountModityInfoList1();
	}

	@Override
	public List<LjflAccountModify> selectAccoountModityInfoListTotal(
			String modifyusername) {
		// TODO Auto-generated method stub
		return ljflaccountmodifymapper.selectAccoountModityInfoListTotal(modifyusername);
	}

	@Override
	public List<LjflAccountModify> selectAccoountModityInfoList2(
			String fstaffdetailid) {
		// TODO Auto-generated method stub
		return ljflaccountmodifymapper.selectAccoountModityInfoList2(fstaffdetailid);
	}



	
}
