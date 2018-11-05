package com.yunlao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class SimpleDateUtil {
 
 // 短日期格式
 public static String DATE_FORMAT = "yyyy-MM-dd";
 
 // 长日期格式
 public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
//a integer to xx:xx:xx
 /*
  * 秒转换成0时0分0秒的格式
  */
 public static String secToTime(int time) {
     String timeStr = null;
     int hour = 0;
     int minute = 0;
     int second = 0;
     if (time <= 0)
         return "00:00:00";
     else {
         minute = time / 60;
         if (minute < 60) {
             second = time % 60;
             timeStr = "00:"+unitFormat(minute) + ":" + unitFormat(second);
         } else {
             hour = minute / 60;
//             if (hour > 99)
//                 return "99:59:59";
             int d = 0;
             int h = 0;
             if(hour >24){
            	 d = hour/24;
            	 h = hour/24;
             }
             minute = minute % 60;
             second = time - hour * 3600 - minute * 60;
             if(d != 0 ){
            	 timeStr = d+"天"+ unitFormat(h) + ":" + unitFormat(minute) + ":" + unitFormat(second);
             }else{
            	  timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
             }
           
         }
     }
     return timeStr;
 }

 public static String unitFormat(int i) {
     String retStr = null;
     if (i >= 0 && i < 10)
         retStr = "0" + Integer.toString(i);
     else
         retStr = "" + i;
     return retStr;
 }

 /**
  * 求时间差 
  * @param startTime 开始时间
  * @param endTime 结束时间
  * @return long类型
  */
 public static long getDateChange(Date startTime,Date endTime){
	 long days = 0l;
	 try
	 {
	     Date d1 = endTime;
	     Date d2 = startTime;
	     long diff = d1.getTime() - d2.getTime();
	     days = diff / (1000 * 60 * 60 * 24);
	     
	 }
	 catch (Exception e)
	 {
	 }
	 return days;
 }
 /**
  * 求得时间差  单位秒
  * @param startTime
  * @param endTime
  * @return
  */
 public static long getDateChangeSeconds(Date startTime,Date endTime) {

	 long from = startTime.getTime();
	 long to = endTime.getTime();
	 int minutes = (int) ((to - from)/(1000 * 60));

	   long s=minutes*60;

	   return s;
}
 /**
  * 根据时间差，获取字符串具体的天、时、分、秒
  * @param startTime
  * @param endTime
  * @return
  * @throws Exception
  */
 public static String getDateChangeMillons(Date startTime,Date endTime) throws Exception{

	   java.util.Date now = endTime;

	   java.util.Date date=startTime;

	   long l=now.getTime()-date.getTime();

	   long day=l/(24*60*60*1000);

	   long hour=(l/(60*60*1000)-day*24);

	   long min=((l/(60*1000))-day*24*60-hour*60);

	   long s=(l/1000-day*24*60*60-hour*60*60-min*60);

	   return day+"天"+hour+"小时"+min+"分"+s+"秒";
 }
 /**
  * 字符串转换成日期
  * @param str
  * @return
  * @throws Exception
  */
 public static Date getDateByString(String str){
	 SimpleDateFormat sdf=new SimpleDateFormat(TIME_FORMAT);//小写的mm表示的是分钟
	 java.util.Date date = null;
	try {
		date = sdf.parse(str);
	} catch (java.text.ParseException e) {
		e.printStackTrace();
	}
	 return date;
 }
 
 /**
  * 将日期格式的字符串转换为长整型
  * 
  * @param date
  * @param format
  * @return
 * @throws Exception 
  */
 public static long convert2long(String date, String format) throws Exception {
  try {
   if (StringUtils.isNotBlank(date)) {
    if (StringUtils.isBlank(format))
     format = SimpleDateUtil.TIME_FORMAT;
    SimpleDateFormat sf = new SimpleDateFormat(format);
    return sf.parse(date).getTime();
   }
  } catch (ParseException e) {
   e.printStackTrace();
  }
  return 0l;
 }
 
 /**
  * 将长整型数字转换为日期格式的字符串
  * 
  * @param time
  * @param format
  * @return
  */
 public static String convert2String(long time, String format) {
  if (time > 0l) {
   if (StringUtils.isBlank(format))
   format = SimpleDateUtil.TIME_FORMAT;
   SimpleDateFormat sf = new SimpleDateFormat(format);
   Date date = new Date(time);
   return sf.format(date);
  }
  return "";
 }
 
 /**
  * 获取当前系统的日期
  * 
  * @return
  */
 public static long curTimeMillis() {
  return System.currentTimeMillis();
 }
 
 /**
  * 示例函数
  * 
  * @param args
 * @throws Exception 
  */
 public static void main(String[] args) throws Exception {
  System.out.println(SimpleDateUtil.convert2long("2000-01-01 01:01:01",
    SimpleDateUtil.DATE_FORMAT));
  System.out.println(SimpleDateUtil.convert2String(SimpleDateUtil
    .curTimeMillis(), SimpleDateUtil.TIME_FORMAT));
  
 }
}