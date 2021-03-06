package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.OMenuinfo;

public interface OMenuInfoService {
	
	
	int deleteByPrimaryKey(Integer oid);

    int insert(OMenuinfo record);

    int insertSelective(OMenuinfo record);

    OMenuinfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OMenuinfo record);

    int updateByPrimaryKey(OMenuinfo record);
    
    List<OMenuinfo> selectOMenuInfoList();

    List<OMenuinfo> selectByPid(@Param("pId")String pid);
    
}
