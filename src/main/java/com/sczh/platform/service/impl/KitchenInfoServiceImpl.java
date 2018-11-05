package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.KitchenInfoMapper;
import com.sczh.platform.po.KitchenInfo;
import com.sczh.platform.service.KitchenInfoService;

@Service
public class KitchenInfoServiceImpl implements KitchenInfoService{

	
	@Autowired
	private KitchenInfoMapper kitcheninfoMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(KitchenInfo record) {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.insert(record);
	}

	@Override
	public int insertSelective(KitchenInfo record) {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.insertSelective(record);
	}

	@Override
	public KitchenInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(KitchenInfo record) {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(KitchenInfo record) {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<KitchenInfo> selectKitchenInfoList(String startSize,
			String endSize) {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.selectKitchenInfoList(startSize, endSize);
	}

	@Override
	public List<KitchenInfo> selectKitchenInfoListTotal() {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.selectKitchenInfoListTotal();
	}

	@Override
	public KitchenInfo selectByCardno(String cardno) {
		// TODO Auto-generated method stub
		return kitcheninfoMapper.selectByCardno(cardno);
	}

}
