package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflAccountModify;

public interface LjflAccountModifyService {

	int deleteByPrimaryKey(String id);

	int insert(LjflAccountModify record);

	int insertSelective(LjflAccountModify record);

	LjflAccountModify selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(LjflAccountModify record);

	int updateByPrimaryKey(LjflAccountModify record);
	
	List<LjflAccountModify> selectAccoountModityInfoList(@Param("modifyusername")String modifyusername);
	List<LjflAccountModify> selectAccoountModityInfoList2(@Param("fstaffdetailid")String fstaffdetailid);
	List<LjflAccountModify> selectAccoountModityInfoList1();
    List<LjflAccountModify> selectAccoountModityInfoListTotal(@Param("modifyusername")String modifyusername);
}
