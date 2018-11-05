package com.sczh.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.AreaRoleManagerMapper;
import com.sczh.platform.dao.CloudWxLoginUserMapper;
import com.sczh.platform.dao.ContractInfoMapper;
import com.sczh.platform.dao.LjflHandDeviceMapper;
import com.sczh.platform.dao.UserInfoMapper;
import com.sczh.platform.po.AreaRoleManager;
import com.sczh.platform.po.CloudWxLoginUser;
import com.sczh.platform.po.ContractInfo;
import com.sczh.platform.po.LjflHandDevice;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private ContractInfoMapper contractInfoMapper;
	
	@Autowired
	private AreaRoleManagerMapper areaRoleManagerMapper;
	
	@Autowired
	private LjflHandDeviceMapper ljflHandDeviceMapper;
	
	@Autowired
	private CloudWxLoginUserMapper CloudWxLoginUserMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return userInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserInfo record) {
		return userInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(UserInfo record) {
		return 0;
	}

	@Override
	public UserInfo selectByPrimaryKey(String id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(UserInfo record) {
		return userInfoMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(UserInfo record) {
		return userInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public UserInfo selectByUserNameAndPassword(String username, String password) {
		return userInfoMapper.selectByUserNameAndPassword(username, password);
	}

	@Override
	public List<UserInfo> selectListUserInfos() {
		return userInfoMapper.selectListUserInfos();
	}
	
	
	

		@Override
		public void insertRoleManagerInfo(AreaRoleManager role) {
			areaRoleManagerMapper.insert(role);
		}



		@Override
		public List<AreaRoleManager> selectAreaRoleManagerListByUserId(PageModelDomain pageModelDomain) {
			Map<String, String> map = new HashMap<String,String>();
			if(pageModelDomain.getUnitId() == null ||pageModelDomain.getUnitId().isEmpty()){
				map.put("unitId", null);
			}else{
				map.put("unitId", pageModelDomain.getUnitId());
			}
		
			map.put("currentNum", pageModelDomain.getCurrentNum()+"");
			map.put("pageSize", pageModelDomain.getPageSize()+"");
			return areaRoleManagerMapper.selectAreaRoleManagerListByUserId(map);
		}
		@Override
		public int selectAreaRoleManagerByUnitId(String unitId) {
			Map<String, String> map = new HashMap<String,String>();
			map.put("unitId", unitId);
			return areaRoleManagerMapper.selectAreaRoleManagerByUnitId(map);
		}



		@Override
		public void deleteAreaRoleManageInfo(String id) {
			areaRoleManagerMapper.deleteByPrimaryKey(id);		
		}



		@Override
		public List<AreaRoleManager> selectAreaRoleManager() {
			return areaRoleManagerMapper.selectAreaRoleManager();
		}

		@Override
		public List<LjflHandDevice> selecthanddeviceinfos(String startSize,
				String endSize,String name,String code) {
			return ljflHandDeviceMapper.selecthanddeviceinfos(startSize, endSize, code, name);
		}



		@Override
		public List<LjflHandDevice> selecthanddeviceinfostotal(String name,String code) {
			return ljflHandDeviceMapper.selecthanddeviceinfostotal(code, name);
		}


		//合同和报表
		@Override
		public ContractInfo selectByPrimaryKeyfile(String id) {
			return contractInfoMapper.selectByPrimaryKey(id);
		}
		
		@Override
		public int deleteByPrimaryKeyfile(String id) {
			return contractInfoMapper.deleteByPrimaryKey(id);
		}
		
		
		@Override
		public int insert(ContractInfo record) {
			return contractInfoMapper.insert(record);
		}
		
		@Override
		public int insertSelective(ContractInfo record) {
			return contractInfoMapper.insertSelective(record);
		}
		
		@Override
		public int updateByPrimaryKeySelective(ContractInfo record) {
			return contractInfoMapper.updateByPrimaryKeySelective(record);
		}
		
		@Override
		public int updateByPrimaryKey(ContractInfo record) {
			return 0;
		}
		
		@Override
		public List<ContractInfo> selectContractInfos(String startSize,
				String endSize) {
			return contractInfoMapper.selectContractInfos(startSize, endSize);
		}
		
		@Override
		public List<ContractInfo> selectContractInfosss(String startSize, String endSize, String uploadname) {
			return contractInfoMapper.selectContractInfosss(startSize, endSize, uploadname);
		}
		
		@Override
		public List<ContractInfo> selectReportformsTotal(String startSize,
				String endSize,String name) {
			return contractInfoMapper.selectReportformsTotal(startSize,endSize,name);
		}
		
		@Override
		public List<ContractInfo> selectReportforms(String startSize,
				String endSize,String name) {
			return contractInfoMapper.selectReportforms(startSize, endSize, name);
		}
		
		@Override
		public List<ContractInfo> selectReportformsUploadname(String startSize,
				String endSize,String uploadname) {
			return contractInfoMapper.selectReportformsUploadname(startSize,endSize,uploadname);
		}
		
		
		
		@Override
		public List<ContractInfo> selectContractInfosTotal(String name) {

			return contractInfoMapper.selectContractInfosTotal(name);
		}



		@Override
		public int deleteById(String id) {
			return ljflHandDeviceMapper.deleteByPrimaryKey(id);
		}



		@Override
		public CloudWxLoginUser selectBystaffId(String id) {
			return CloudWxLoginUserMapper.selectBystaffId(id);
		}



		@Override
		public int updateByPrimaryKey(CloudWxLoginUser record) {
			return CloudWxLoginUserMapper.updateByPrimaryKey(record);
		}


}
