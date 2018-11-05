package com.sczh.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sczh.platform.dao.Et100Mapper;
import com.sczh.platform.dao.KitchenInfoMapper;
import com.sczh.platform.dao.KitchenInforecordMapper;
import com.sczh.platform.dao.PointMapper;
import com.sczh.platform.dao.Tub01Mapper;
import com.sczh.platform.dao.WorkPointMapper;
import com.sczh.platform.po.Et100;
import com.sczh.platform.po.KitchenInfo;
import com.sczh.platform.po.KitchenInforecord;
import com.sczh.platform.po.Point;
import com.sczh.platform.po.Tub01;
import com.sczh.platform.po.WorkPoint;
import com.sczh.platform.service.Tub01Service;
import com.sczh.platform.service.redis.RedisService;
@Service("tub01Service")
public class Tub01ServiceImpl implements Tub01Service {
	
	@Autowired
	private Tub01Mapper tub01Mapper;
	@Autowired
	private Et100Mapper et100Mapper;
	@Resource
	private RedisService redisService;
	@Autowired
	private PointMapper pointMapper;
	@Autowired
	private WorkPointMapper workPointMapper;
	
	@Autowired
	private KitchenInforecordMapper kitchenInforecordMapper;
	@Autowired
	private KitchenInfoMapper kitchenInfoMapper;
	
	public List<String> getList(String key,int startNum, int endNum){
		return redisService.getList(key, startNum, endNum);
	}
	@Override
	public int deleteByPrimaryKey(String fuleId) {
		return kitchenInforecordMapper.deleteByPrimaryKey(fuleId);
	}

	@Override
	public int insert(Tub01 record) {
		return tub01Mapper.insert(record);
	}

	@Override
	public int insertSelective(Tub01 record) {
		return 0;
	}

	@Override
	public Tub01 selectByPrimaryKey(String fuleId) {
		return tub01Mapper.selectByPrimaryKey(fuleId);
	}

	@Override
	public int updateByPrimaryKeySelective(Tub01 record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Tub01 record) {
		return 0;
	}
	@Override
	public List<Tub01> getTub01ListTotal() {
		return tub01Mapper.getTub01ListTotal();
	}
	@Override
	public int insertEt100Recode(Et100 record) {
		return et100Mapper.insert(record);
	}
	@Override
	public List<Et100> getEt100ListTotal() {
		return et100Mapper.getEt100ListTotal();
	}
	@Override
	public List<Et100> getEt100ListTotalByTimeAndSourceDId(Et100 record) {
		return et100Mapper.getEt100ListTotalByTimeAndSourceDId(record);
	}
	@Override
	public int insertPoint(Point record) {
		return pointMapper.insert(record);
	}
	@Override
	public Point selectPointByPrimaryKey(String id) {
		return pointMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updatePointByPrimaryKeySelective(Point record) {
		return pointMapper.updateByPrimaryKey(record);
	}
	@Override
	public int insertWorkPointData(WorkPoint point) {
		return workPointMapper.insert(point);
	}
	@Override
	public WorkPoint selectWorkPointByPrimaryKey(String id) {
		return workPointMapper.selectByPrimaryKey(id);
	}
	@Override
	public WorkPoint selectBypointName(String pointName) {
		return workPointMapper.selectBypointName(pointName);
	}
	@Override
	public List<WorkPoint> getWorkPointList(String startSize, String endSize) {
		return workPointMapper.getWorkPointList(startSize, endSize);
	}
	@Override
	public List<Et100> getEt100ListByTime(String startTime, String endTime) {
		List<Et100> list = new ArrayList<Et100>();
		 List<HashMap<String, Object>> maps = et100Mapper.getEt100ListByTime(startTime, endTime);
		 for (HashMap<String, Object> map : maps) {
			 Et100  e = new Et100();
			 e.setGpsDatetime(map.get("gps_datetime").toString());
			 e.setGpsLatitude(map.get("gps_latitude").toString());
			 e.setGpsLongitude(map.get("gps_longitude").toString());
			 e.setSourcedeviceid(map.get("sourceDeviceId").toString());
			 list.add(e);
		}
		return list;
	}
	@Override
	public List<WorkPoint> getWorkPointList() {
		return workPointMapper.getWorkPointListNoPrameter();
	}
	@Override
	public List<Et100> getEt100ListTotalBySourcedeviceid(String sourcedeviceid) {
		return et100Mapper.getEt100ListTotalBySourcedeviceid(sourcedeviceid);
	}
	@Override
	public Et100 getEt00ByPrimaryKey(String id) {
		return et100Mapper.selectByPrimaryKey(id);
	}
	@Override
	public List<WorkPoint> getWorkPointListByUnit(String unitId) {
		return workPointMapper.getWorkPointListByUnit(unitId);
	}
	@Override
	public KitchenInforecord getKitchenInforecordId(String id) {
		return kitchenInforecordMapper.selectByPrimaryKey(id);
	}
	@Override
	public void insertKitchenInforecord(KitchenInforecord record) {
		kitchenInforecordMapper.insert(record);		
	}
	@Override
	public KitchenInfo getKitchenInfoByCarNo(String cardno) {
		return kitchenInfoMapper.selectByCardno(cardno);
	}
	@Override
	public void updatekitchenInfo(KitchenInfo kitchenInfo) {
		kitchenInfoMapper.updateByPrimaryKey(kitchenInfo);
	}
	
	  public List<Et100> getEt100sBetweenTimeAndSouceDeviceId(String startDate, String endDate, String sourceDeviceId)
	  {
	    List<Et100> list = new ArrayList();
	    List<HashMap<String, Object>> maps = this.et100Mapper.getEt100ListByTimeAndsourceDeviceID(startDate, endDate, sourceDeviceId);
	    for (HashMap<String, Object> map : maps)
	    {
	      Et100 e = new Et100();
	      e.setGpsDatetime(map.get("gps_datetime").toString());
	      e.setGpsLatitude(map.get("gps_latitude").toString());
	      e.setGpsLongitude(map.get("gps_longitude").toString());
	      e.setSourcedeviceid(map.get("sourceDeviceId").toString());
	      list.add(e);
	    }
	    return list;
	  }
	@Override
	public List<KitchenInforecord> kitchenrecordinfoList(String startSize,
			String endSize) {
		// TODO Auto-generated method stub
		return kitchenInforecordMapper.kitchenrecordinfoList(startSize, endSize);
	}
	@Override
	public List<KitchenInforecord> kitchenrecordinfoListTotal() {
		// TODO Auto-generated method stub
		return kitchenInforecordMapper.kitchenrecordinfoListTotal();
	}
}
