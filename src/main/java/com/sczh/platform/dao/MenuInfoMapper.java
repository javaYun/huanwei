package com.sczh.platform.dao;

import java.util.List;

import com.sczh.platform.po.MenuInfo;

public interface MenuInfoMapper {
    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);
    
    List<MenuInfo> selectMenuInfos();

    List<MenuInfo> selectMenuInfosByPid(String pid);

    MenuInfo selectMenuInfo(String id);
    
}