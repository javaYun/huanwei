package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.KitchenInfo;

public interface KitchenInfoService {
	
	 int deleteByPrimaryKey(String id);

	    int insert(KitchenInfo record);

	    int insertSelective(KitchenInfo record);

	    KitchenInfo selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(KitchenInfo record);

	    int updateByPrimaryKey(KitchenInfo record);
	    
	    List<KitchenInfo> selectKitchenInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
	    
	    List<KitchenInfo> selectKitchenInfoListTotal();
	    KitchenInfo selectByCardno(String cardno);
}
