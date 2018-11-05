package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflPrize;
import com.sczh.platform.po.LjflPrizeProvide;
import com.sczh.platform.po.LjflPrizeProvideRecord;
import com.sczh.platform.po.LjflPrizeProvideRecord1;
import com.sczh.platform.po.LjflScoreRecord;
import com.sczh.platform.po.Model.LjflPrizePageDomain;
import com.sczh.platform.po.Model.PageModelDomain;

public interface LjflPrizeService {

	 List<LjflPrizeProvide> selectPrizeProvideInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize,@Param("name")String name,@Param("code")String code,@Param("starttime")String starttime,@Param("endtime")String endtime);
	    
	    List<LjflPrizeProvide> selectPrizeProvideInfoListTotal(@Param("name")String name,@Param("code")String code,@Param("starttime")String starttime,@Param("endtime")String endtime);
	    
	    List<LjflPrizeProvide>  selectPrizeProvideInfoListTotal1();
    List<LjflPrize> selectByBeenDeleted();
    
    int insert(LjflPrizeProvide record);
    
    int insert(LjflPrize record);
    
    LjflPrizeProvide selectByPrimaryKey(String id);
    
    LjflPrize selectByPrimaryKey1(String id);
    
    int updateByPrimaryKey(LjflPrizeProvide record);
    
    int selecScoreByDateTotalSize(LjflPrizePageDomain ljflPrizePageDomain);
    
    /**
	 * 查询积分集合
	 * @param pageModelDomain
	 * @return
	 */
	List<LjflPrizeProvideRecord> selecScoreByDateTotal(LjflPrizePageDomain pageModelDomain);
	
	List<LjflPrize> selectPrizeInfoList(@Param("startSize")String startSize,@Param("endSize")String endSize);
	
	List<LjflPrize> selectPrizeInfoListTotal();
	
	List<LjflPrize> selectPrizeInfoList1(@Param("startSize") String startSize,
			@Param("endSize") String endSize,@Param("code") String code,
			@Param("name") String name);

	List<LjflPrize> selectPrizeInfoListTotal1(@Param("code") String code,
			@Param("name") String name);
	
	LjflPrize selectByPrimaryKey2(String id);
	int updateByPrimaryKey(LjflPrize record);
	
	
	
int selecScoreByDateTotalSize1(LjflPrizePageDomain ljflPrizePageDomain);
    
    /**
	 * 查询积分集合
	 * @param pageModelDomain
	 * @return
	 */
	List<LjflPrizeProvide> selecScoreByDateTotal1(LjflPrizePageDomain pageModelDomain);
	
	LjflPrizeProvide selectByPrizeId(String prizeid);
	int insert(LjflPrizeProvideRecord1 record);
	int deletebyid(@Param("id")String id,@Param("tablename") String tablename);
	
	LjflPrizeProvideRecord selectBycdKey(@Param("tablename") String tablename,@Param("cdkey") String cdkey);
	
	int updateByPrimaryKey(LjflPrizeProvideRecord record);
	
	List<LjflPrize> selectljflprizebyname(String name);

	
}
