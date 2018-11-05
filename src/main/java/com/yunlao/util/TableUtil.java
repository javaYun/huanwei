package com.yunlao.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/**
 * 数据库表工具类
 * @author yunluo
 *
 */
public class TableUtil {
	/*
	 * 获取动态数据库表名集合
	 */
	public static List<String> getTableName(String tableName, String startTime, String endTime) throws Exception {
		List<String> list = new ArrayList<String>();
		Date d1 = new SimpleDateFormat("yyyy-MM").parse(startTime.substring(0, 7));// 定义起始日期
		Date d2 = new SimpleDateFormat("yyyy-MM").parse(endTime.substring(0, 7));// 定义结束日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d2);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		d2 = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		Calendar dd = Calendar.getInstance();// 定义日期实例
		dd.setTime(d1);// 设置日期起始时间
		while (dd.getTime().before(d2)) {// 判断是否到结束日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String str = sdf.format(dd.getTime());
			list.add(tableName + str);

			dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1搜索

		}
		return list;
	}
	/*
	 * 获取动态数据库表名
	 */
	public static String getTableNameString(String tableName, String endTime) throws Exception {

		Date d2 = new SimpleDateFormat("yyyy-MM").parse(endTime.substring(0, 7));// 定义日期

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d2);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		d2 = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		Calendar dd = Calendar.getInstance();// 定义日期实例

		dd.setTime(d2);// 设置日期起始时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		String str = sdf.format(dd.getTime());

		tableName = tableName + str;

		return tableName;
	}
	
	public static String getTodayTime( String endTime) throws Exception {

		Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime.substring(0, 10));// 定义日期

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d2);
		calendar.add(calendar.DATE,0);// 把日期往后增加一天.整数往后推,负数往前移动
		d2 = calendar.getTime(); // 这个时间就是日期往后推一天的结果

		Calendar dd = Calendar.getInstance();// 定义日期实例

		dd.setTime(d2);// 设置日期起始时间

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String str = sdf.format(dd.getTime());


		return str;
	}

}
