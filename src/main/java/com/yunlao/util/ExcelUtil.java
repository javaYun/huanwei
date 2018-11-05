package com.yunlao.util;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * 导出Excel文档工具类
 * @author java 先生
 * @date 2014-8-6
 * */
public class ExcelUtil {

    /**
     * 创建excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }
        sheet.createFreezePane(0, 1);//冻结首行
        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLUE.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
        

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

//        Font f3=wb.createFont();
//        f3.setFontHeightInPoints((short) 10);
//        f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }
//    /**
//	 * 导出excel数据文件(居民时间段投放记录接口查询)
//	 * 
//	 * @param pageModel
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/exportExcel")
//	public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		PageModelDomain pageModel = new PageModelDomain();
//		/**
//		 * 封装PageModelDomain
//		 */
//		String startTime = request.getParameter("startTime");
//		String endTime = request.getParameter("endTime");
//		String pageNo = request.getParameter("pageNo");
//		String pageSize = request.getParameter("pageSize");
//		String cishubiaozhun = request.getParameter("cishubiaozhun");
//		String weightNum = request.getParameter("weightNum");
//		String staffName = request.getParameter("staffName");
//		staffName = new String(staffName.getBytes("ISO-8859-1"), "UTF-8");
//		pageModel.setStartTime(startTime);
//		pageModel.setEndTime(endTime);
//		pageModel.setPageNo(pageNo);
//		pageModel.setPageSize(Integer.parseInt(pageSize));
//		if (cishubiaozhun != null && !"".equals(cishubiaozhun)) {
//			pageModel.setCishubiaozhun(Integer.parseInt(cishubiaozhun));
//		}
//
//		if (weightNum != null && !"".equals(weightNum)) {
//			pageModel.setWeightNum(Integer.parseInt(weightNum));
//		}
//
//		pageModel.setStaffName(staffName);
//		PageModelDomain page = getLjflPutRecordGroupByWeight(pageModel);
//		/**
//		 * 获取到数据
//		 */
//		List<PutRecordModel> putRecordList = page.getPutRecordModellist();
//
//		// 填充Objects数据
//		// List<Object> Objects=createData(request);
//		List<Map<String, Object>> list = createExcelRecord(putRecordList);
//		String columnNames[] = { "排序", "居民", "投放次数(次)", "次数变化率(%)", "投放重量(kg)", "重量变化率(%)" };// 列名
//		String keys[] = { "num", "staffName", "putRecordNo", "putRecordNorate", "putRecordWeightData",
//				"putRecordWeightDatarate" };// map中的key
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		try {
//			ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		byte[] content = os.toByteArray();
//		InputStream is = new ByteArrayInputStream(content);
//		// 设置response参数，可以打开下载页面
//		response.reset();
//		response.setContentType("application/vnd.ms-excel;charset=utf-8");
//		String fileName = "居民时间段投放记录表";
//		response.setHeader("Content-Disposition", "attachment;filename="
//				+ new String((processFileName(request, fileName) + ".xls").getBytes(), "iso-8859-1"));
//		ServletOutputStream out = response.getOutputStream();
//		BufferedInputStream bis = null;
//		BufferedOutputStream bos = null;
//		try {
//			bis = new BufferedInputStream(is);
//			bos = new BufferedOutputStream(out);
//			byte[] buff = new byte[2048];
//			int bytesRead;
//			// Simple read/write loop.
//			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
//				bos.write(buff, 0, bytesRead);
//			}
//		} catch (final IOException e) {
//			throw e;
//		} finally {
//			if (bis != null)
//				bis.close();
//			if (bos != null)
//				bos.close();
//		}
//		return null;
//	}
    public static void main(String[] args) {
		
	}

}
