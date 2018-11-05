package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.Model.PageModelDomain;

public interface LjflStaffDetailService {

	int deleteByPrimaryKey(String id);

    int insert(LjflStaffDetail record);

    int insertSelective(LjflStaffDetail record);

    LjflStaffDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflStaffDetail record);

    int updateByPrimaryKeyWithBLOBs(LjflStaffDetail record);

    int updateByPrimaryKey(LjflStaffDetail record);
    
    List<LjflStaffDetail> selectStaffDetailInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
    List<LjflStaffDetail> selectStaffDetailInfoListTotal();
    
    List<LjflStaffDetail> selectByName(String name);  
    
    /**
	 * 分页查询。LjflStaffDetail	List数据
	 * @param pageModelDomain
	 * @return
	 */
	List<LjflStaffDetail> selectStaffDetailList(PageModelDomain pageModelDomain);

	int selecStaffDetailtTotalSize(PageModelDomain pageModelDomain);
	
	LjflStaffDetail selectByPhone(String phone);
}
