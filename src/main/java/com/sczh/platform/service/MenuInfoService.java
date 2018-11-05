package com.sczh.platform.service;

import java.util.List;

import com.sczh.platform.po.MenuInfo;

public interface MenuInfoService {
	
	int insert(MenuInfo record);

    int insertSelective(MenuInfo record);
    
    List<MenuInfo> selectMenuInfos();
    
    List<MenuInfo> selectMenuInfosByPid(String pid);
    
    MenuInfo selectMenuInfo(String id);
}
