package com.sczh.platform.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sczh.platform.po.LjflScoreDetail;
import com.sczh.platform.po.LjflScoreRecord;
import com.sczh.platform.po.LjflScoreRecord1;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.LjflType;
import com.sczh.platform.po.Model.PageModelDomain;

public interface LjflScoreService {
	/**
	 * 分页查询。LjflStaffDetail	List数据
	 * @param pageModelDomain
	 * @return
	 */
	List<LjflStaffDetail> selectStaffDetailList(PageModelDomain pageModelDomain);

	int selecStaffDetailtTotalSize(PageModelDomain pageModelDomain);
	/**
	 * 分页查询  居民积分，可以查询当日，也可以跨月查询
	 * @param pageModelDomain
	 * @return
	 */

	int selecScoreByDateTotalSize(PageModelDomain pageModelDomain);
	/**
	 * 查询积分集合
	 * @param pageModelDomain
	 * @return
	 */
	List<LjflScoreRecord> selecScoreByDateTotal(PageModelDomain pageModelDomain);
	/**
	 * 根据id获取staffDetail
	 * @param StaffDetailId
	 * @return
	 */
	LjflStaffDetail selectStaffDetailById(String StaffDetailId);
	/**
	 * 分页查询现场活动记录
	 * @param pageModelDomain
	 * @return
	 */
	int selecScoreDetailByDateTotalSize(PageModelDomain pageModelDomain);

	List<LjflScoreDetail> selecScoreDetailByDateTotal(PageModelDomain pageModelDomain);

	LjflType selectLjflTypeById(String getfConfirmRubbishTypeId);
	int insert(LjflScoreRecord1 record);

	List<LjflScoreRecord> selectBydate(@Param("tablename") String tablename,@Param("todayTime") String todayTime,@Param("staffDetailId")String staffDetailId);

	int deleteByPrimaryKey(String id);
}
