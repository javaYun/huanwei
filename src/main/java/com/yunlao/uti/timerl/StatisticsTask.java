package com.yunlao.uti.timerl;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.sczh.platform.po.Et100;
import com.sczh.platform.po.Et100Model;
import com.sczh.platform.po.Et100Model1;
import com.sczh.platform.po.Et100Model2;
import com.sczh.platform.po.KitchenInfo;
import com.sczh.platform.po.KitchenInforecord;
import com.sczh.platform.po.TotalModel;
import com.sczh.platform.po.Tub01;
import com.sczh.platform.service.Tub01Service;
import com.yunlao.util.MyLong;
import com.yunlao.util.SimpleDateUtil;
import com.yunlao.util.GoogleBaidu.CoordinateConversion;
import com.yunlao.util.GoogleBaidu.Point;

import net.sf.json.JSONArray;

public class StatisticsTask extends TimerTask
{

    private static boolean isRunning = false;
    private ServletContext context = null;
   private static final Logger logger = LogManager.getLogger(StatisticsTask.class.getName());

    public StatisticsTask(ServletContext context)
    {
        this.context = context;
    }
    
    @Override
    public void run()
    {
        //System.out.println(isRunning);
        if (!isRunning) 
        { 
//            if (STATISTICS_SCHEDULE_HOUR == cal.get(Calendar.HOUR_OF_DAY)) //查看是否为凌晨
//            { 
                isRunning = true; 
            //    context.log("开始执行指定任务");
                //System.out.println("任务开始执行");
                executeTask();
                
                
                isRunning = false;
//                System.out.println("任务执行完毕");
//                context.log("指定任务执行结束"); 
//            } 
        } 
        else 
        {
            context.log("上一次任务执行还未结束");
        }
    
    }
	
    /**
     * 执行任务
     */
    public void executeTask()
    {
    	
          ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(context);
          Tub01Service tub01Service=(Tub01Service)ctx.getBean("tub01Service");
          int end = 1;//索引2
          int size = 1;//终止字符
          List<String> list = new ArrayList<String>();
          while(size>=end){//根据key获取所有的redis缓存数据
        	  list =  tub01Service.getList("das:das->dms",0,end);  
        	  if(list.size() == (end+1)){
        		  end++;
        		  size++;
        	  }else{
        		  size = size-1; 
        	  }
          }
          int num = 0 ;
        for (String str : list) {//将获取到的json数据保存到mysql
        	num++;
        	JSONObject jsonObject = null;
        	logger.error("str:"+str);
        	try {
        		jsonObject = JSONObject.parseObject(str);
			} catch (Exception e) {
				jsonObject = null;
			}
        	if(jsonObject != null){
			TotalModel model = (TotalModel)jsonObject.toJavaObject(jsonObject, TotalModel.class);
			JSONObject tubObject = JSONObject.parseObject(model.getMsgContent());
			Tub01 tub01 = (Tub01)jsonObject.toJavaObject(tubObject, Tub01.class);
			if(tub01.getFuleId() != null){
				tub01.setOccurTime(SimpleDateUtil.convert2String(Long.parseLong(tub01.getOccurTime()),"yyyy-MM-dd hh:mm:ss"));
				if(tub01.getSourceDeviceType().equals("SCZHGPS")){//将sczhGps由Google转为百度坐标
					try {
						double lat = Double.parseDouble(tub01.getLatitude())/100;
						double lon = Double.parseDouble(tub01.getLongitude())/100;
						Point point = CoordinateConversion.google_bd_encrypt(lat, lon);
						tub01.setLatitude(point.getLat()+"");
						tub01.setLongitude(point.getLng()+"");
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				
				if(tub01Service.getEt00ByPrimaryKey(tub01.getFuleId()) == null){
					Et100 et100 = dealTub100Data(tub01);
					if(tub01Service.getEt100ListTotalByTimeAndSourceDId(et100).size()<=0){//同一时间内，同一设备只需要存储同一条数据
							tub01Service.insertEt100Recode(et100);
							logger.error("插入sczh数据成功:"+str);
					}
				}
				}else if(tub01.getSourceDeviceType().equals("electronicScale")){
					Et100 et100 = dealTub100Data(tub01);
					if(tub01Service.getKitchenInforecordId(et100.getId())==null){
						KitchenInforecord record = new KitchenInforecord();
						record.setId(et100.getId());
						record.setWeight(et100.getHeightvalue());//kg 
						record.setCardno(et100.getSourcedeviceid());
						record.setRecordtime(et100.getGpsDatetime());
						tub01Service.insertKitchenInforecord(record);
						KitchenInfo kitchenInfo = tub01Service.getKitchenInfoByCarNo(record.getCardno());
						if(kitchenInfo != null){
							double money = Double.parseDouble(kitchenInfo.getMoney());
							double weight = Double.parseDouble(record.getWeight());
							money = money - weight;
							kitchenInfo.setMoney(money+"");
							tub01Service.updatekitchenInfo(kitchenInfo);
						}
					
					}

				}
			}else{//	解析Et100并储存到mysql
				JSONObject et100Object = JSONObject.parseObject(model.getMsgContent());
				Et100Model et100Model = (Et100Model)jsonObject.toJavaObject(et100Object, Et100Model.class);
				JSONObject et100Object1 =JSONObject.parseObject( et100Model.getParams().toString());
				Et100Model1 et100Model1 = (Et100Model1)jsonObject.toJavaObject(et100Object1, Et100Model1.class);
				JSONArray myJsonArray = JSONArray.fromObject(et100Model1.getDataContent());
				List<Et100Model2> et100List = JSONArray.toList(myJsonArray, Et100Model2.class);
				for (Et100Model2 e : et100List) {
					if(e!=null){
						Et100 et100 = dealData(et100Model,et100Model1,e);
						if(tub01Service.getEt100ListTotalByTimeAndSourceDId(et100).size()<=0){//同一时间内，同一设备只需要存储同一条数据
							tub01Service.insertEt100Recode(et100);
						}
					}
				}
			}
        	}else{//当jsonObject为null的时候
        		
        	}
        }
       
    }
    /**
     * 将油耗等其他设备的数据组装到et100表中，所有的数据组合在同一张表中
     * @param tub01
     * @return
     */
    private Et100 dealTub100Data(Tub01 t){
    	Et100 e = new Et100();
    	e.setId(t.getFuleId());
    	e.setSourcedeviceid(t.getSourceDeviceId());
    	e.setSourcedevicetype(t.getSourceDeviceType());
    	e.setGpsDirection(t.getGps_description());
    	e.setGpsSpeed(t.getRate());
    	e.setGpsLatitude(t.getLatitude());
    	e.setGpsLongitude(t.getLongitude());
    	e.setGpsDatetime(t.getOccurTime());
    	e.setGpsvalid(t.getConnected());
    	e.setHeightvalue(t.getHeightValue());
    	return e;
    }
    /**
     * 
     * @param model
     * @param model1
     * @param e
     * @return
     */
	private Et100 dealData(Et100Model et100Model, Et100Model1 et100Model1, Et100Model2 e) {
		Et100 et100 = new Et100();
		String id = MyLong.getRand().toString();
		et100.setId(id);
		et100.setGpsLatitude(e.getGps_latitude());
		et100.setGpsLongitude(e.getGps_longitude());
		et100.setSourcedeviceid(et100Model.getSourceDeviceId());
		et100.setGpsDatetime(SimpleDateUtil.convert2String(Long.parseLong(e.getGps_datetime()),"yyyy-MM-dd hh:mm:ss"));
		et100.setCellid(null);
		et100.setDatauploadmode(null);
		et100.setGpsDirection(null);
		et100.setGpsNum(e.getGps_num());
		et100.setGpsSpeed(e.getGps_speed());
		et100.setMsgcode(et100Model.getMsgCode());
		et100.setSourcedevicetype(et100Model.getSourceDeviceType());
		et100.setTag(et100Model.getTag());
		et100.setTargetdeviceid(et100Model.getTargetDeviceId());
		et100.setTargetdevicetype(et100Model.getTargetDeviceType());
		return et100;
	}
   
}