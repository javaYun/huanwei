package com.sczh.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.MenuInfoMapper;
import com.sczh.platform.po.MenuInfo;
import com.sczh.platform.service.MenuInfoService;

@Service
public class MenuInfoServiceImpl implements MenuInfoService{

	@Autowired
	private MenuInfoMapper menuinfomapper;
	
	@Override
	public int insert(MenuInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(MenuInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MenuInfo> selectMenuInfos() {
		return menuinfomapper.selectMenuInfos();
	}

	@Override
	public List<MenuInfo> selectMenuInfosByPid(String pid) {
		return menuinfomapper.selectMenuInfosByPid(pid);
	}

	@Override
	public MenuInfo selectMenuInfo(String id) {
		return menuinfomapper.selectMenuInfo(id);
	}

	

}
