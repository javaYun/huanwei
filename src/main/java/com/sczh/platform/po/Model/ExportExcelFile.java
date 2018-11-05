package com.sczh.platform.po.Model;

import javax.servlet.http.HttpServletRequest;




public class ExportExcelFile {
	
	


	public static String processFileName(HttpServletRequest request, String fileNames) {
		String codedfilename = null;
		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
				String name = java.net.URLEncoder.encode(fileNames, "UTF8");
				codedfilename = name;
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
				codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codedfilename;
	}

//	/**
//	 * double保留两位小数
//	 * 
//	 * @param d
//	 * @return
//	 */
//	private static double formatDouble2(double d, int num) {
//		// 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
//		BigDecimal bg = new BigDecimal(d).setScale(num, RoundingMode.UP);
//		return bg.doubleValue();
//	}
//
//	/*
//	 * 获取动态数据库表名集合
//	 */
//	private static List<String> getTableName(String tableName, String startTime, String endTime) throws Exception {
//		List<String> list = new ArrayList<String>();
//		Date d1 = new SimpleDateFormat("yyyy-MM").parse(startTime.substring(0, 6));// 定义起始日期
//		Date d2 = new SimpleDateFormat("yyyy-MM").parse(endTime.substring(0, 7));// 定义结束日期
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(d2);
//		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
//		d2 = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//		Calendar dd = Calendar.getInstance();// 定义日期实例
//		dd.setTime(d1);// 设置日期起始时间
//		while (dd.getTime().before(d2)) {// 判断是否到结束日期
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//			String str = sdf.format(dd.getTime());
//			list.add(tableName + str);
//
//			dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1搜索
//
//		}
//		return list;
//	}
//
//	/*
//	 * 获取动态数据库表名集合
//	 */
//	private static String getTableNameString(String tableName, String endTime) throws Exception {
//
//		Date d2 = new SimpleDateFormat("yyyy-MM").parse(endTime.substring(0, 7));// 定义日期
//
//		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(d2);
//		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
//		d2 = calendar.getTime(); // 这个时间就是日期往后推一天的结果
//
//		Calendar dd = Calendar.getInstance();// 定义日期实例
//
//		dd.setTime(d2);// 设置日期起始时间
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//
//		String str = sdf.format(dd.getTime());
//
//		tableName = tableName + str;
//
//		return tableName;
//	}


}
