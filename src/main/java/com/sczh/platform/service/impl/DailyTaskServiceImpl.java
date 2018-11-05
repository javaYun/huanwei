package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.DailyTaskInfoMapper;
import com.sczh.platform.po.DailyTaskInfo;
import com.sczh.platform.service.DailyTaskService;

@Service
public class DailyTaskServiceImpl implements DailyTaskService{

	@Autowired
	private DailyTaskInfoMapper dailyTaskmapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DailyTaskInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(DailyTaskInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DailyTaskInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DailyTaskInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DailyTaskInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DailyTaskInfo> selectListDailyTaskList(String startSize,
			String endSize) {
		// TODO Auto-generated method stub
		return dailyTaskmapper.selectListDailyTaskList(startSize, endSize);
	}

	@Override
	public List<DailyTaskInfo> selectListDailyTaskListTotal() {
		// TODO Auto-generated method stub
		return dailyTaskmapper.selectListDailyTaskListTotal();
	}

}
