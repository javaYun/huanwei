package com.sczh.platform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo selectByUserNameAndPassword(@Param("username") String username,@Param("password") String password);

    List<UserInfo> selectListUserInfos();

}