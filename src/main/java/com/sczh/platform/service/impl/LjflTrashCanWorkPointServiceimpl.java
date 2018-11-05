package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.*;
import com.sczh.platform.po.LjflTrashCanWorkPoint;
import com.sczh.platform.service.*;

@Service
public class LjflTrashCanWorkPointServiceimpl implements LjflTrashCanWorkPointService {

	@Autowired
	private LjflTrashCanWorkPointMapper LjflTrashCanWorkPointMapper;
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(LjflTrashCanWorkPoint record) {
		// TODO Auto-generated method stub
		return LjflTrashCanWorkPointMapper.insert(record);
	}

	public int insertSelective(LjflTrashCanWorkPoint record) {
		// TODO Auto-generated method stub
		return LjflTrashCanWorkPointMapper.insert(record);
	}

	public LjflTrashCanWorkPoint selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return LjflTrashCanWorkPointMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(LjflTrashCanWorkPoint record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(LjflTrashCanWorkPoint record) {
		// TODO Auto-generated method stub
		return LjflTrashCanWorkPointMapper.updateByPrimaryKey(record);
	}

	public List<LjflTrashCanWorkPoint> selectLjflTrashCanWorkPointList() {
		// TODO Auto-generated method stub
		return LjflTrashCanWorkPointMapper.selectLjflTrashCanWorkPointList();
	}

	@Override
	public LjflTrashCanWorkPoint selectLjflTrashCanWorkPointList1(String trashcanid) {
		// TODO Auto-generated method stub
		return LjflTrashCanWorkPointMapper.selectLjflTrashCanWorkPointList1(trashcanid);
	}

	@Override
	public List<LjflTrashCanWorkPoint> selectLjflTrashCanWorkPointInfoList(String startSize,
			String endSize) {
		// TODO Auto-generated method stub
		return LjflTrashCanWorkPointMapper.selectLjflTrashCanWorkPointInfoList(startSize, endSize);
	}

	@Override
	public List<LjflTrashCanWorkPoint> selectLjflTrashCanWorkPointInfoListTotal() {
		// TODO Auto-generated method stub
		return LjflTrashCanWorkPointMapper.selectLjflTrashCanWorkPointInfoListTotal();
	}

}
