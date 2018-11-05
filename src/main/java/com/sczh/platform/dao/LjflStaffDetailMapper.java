package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.Model.PageModelDomain;

public interface LjflStaffDetailMapper {
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
	
	List<LjflStaffDetail> selectStaffDetailData(@Param("name") String name,
			@Param("housesId") String housesId,
			@Param("communityId") String communityId,
			@Param("phone") String phone, @Param("startSize") String startSize,
			@Param("endSize") String endSize);
	
	List<LjflStaffDetail> selectStaffDetailDataTotal(@Param("name") String name,
			@Param("housesId") String housesId,
			@Param("communityId") String communityId,
			@Param("phone") String phone);

	int selectHouseHoldTotalSize(Map<String, String> map);

	List<LjflStaffDetail> selectHouseHoldData(Map<String, String> map);
	
}