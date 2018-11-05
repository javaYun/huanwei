package com.yunlao.util.excel;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws Exception {
		String date = "2017-12-25";
		String fileDate = "20171225";
		File file = new File("../excel.xlsx");
		ArrayList<ArrayList<Object>> result = ExcelUtil.readExcel(file);
		for(int i = 0 ;i < result.size() ;i++){
			ArrayList<Object> list = result.get(i);
			if(!isContainNumber(list.get(1).toString())){
				String scoreRecordId = MyLong.getRandStr(12)+""+i*2;
				String scoreDeatilId = MyLong.getRandStr(13)+""+i*1;
				String putId = MyLong.getRandStr(11)+""+i*2;
				String time ="";
				if(i%2 ==0){
					time = date+" 1"+MyLong.getRandStr(3)+":4"+MyLong.getRandStr(3)+":31";
				}else if(i%3 ==0 && i%2 !=0){
					time = date+" 1"+MyLong.getRandStr(3)+":3"+MyLong.getRandStr(3)+":21";
				}else{
					time = date+" 1"+MyLong.getRandStr(3)+":2"+MyLong.getRandStr(3)+":54";
				}
				String staffDetailId = list.get(0).toString();
				String householdId = list.get(2).toString();
				String putValue = "";
				if(i%2 ==0){
					putValue = 2+"."+MyLong.getRandStr(3);
				}else if(i%3 ==0 && i%2 !=0){
					putValue = 1+"."+MyLong.getRandStr(3);
				}else{
					putValue = 0+"."+MyLong.getRandStr(3);
				}
				
				String scoreValue = Double.parseDouble(putValue)*2+"";
				String fileName = "../ka"+fileDate+".sql";
				File file1 = new File(fileName);
				if(!file1.exists()){
					file1.createNewFile();
				}
				FileWriter writer = new FileWriter(fileName,true);
				String str1 ="INSERT INTO `ljfl_score_record201712` VALUES ('"+scoreRecordId+"', '"+time+"', '"+time+"', '0', '0', null, null, '4eaf9423eb0043648e502134586b088a', '"+scoreDeatilId+"', '"+scoreValue+"', '"+scoreValue+"', 'swingCard', null, '"+time+"', '"+staffDetailId+"', '"+time+"', null, null);\r";
				writer.write(str1);
				String str2 = "INSERT INTO `ljfl_score_detail201712` VALUES ('"+scoreDeatilId+"', '"+time+"', '"+time+"', '0', '0', null, null, '4eaf9423eb0043648e502134586b088a', null, '"+scoreValue+"', '"+time+"', 'adfdc378f01f47019e79d6395263a95b', '"+staffDetailId+"', '"+time+"', '50f1a51d6e984ef98c4855f4984125cf');\r";
				writer.write(str2);
				String str3 = "INSERT INTO `ljfl_put_record201712` VALUES ('"+putId+"', '"+time+"', '"+time+"', '0', '0', null, null, '4eaf9423eb0043648e502134586b088a', '"+time+"', null, '39.889169', null, '116.458794', '1', '2de111cc004441b280283afc5180989e', null, '0', '"+time+"', '"+putValue+"', 'eaae774e1599445f9570532b7870d686', '"+householdId+"', '"+staffDetailId+"', null);\r";
				writer.write(str3);
				writer.write("\r");
				writer.flush();
				writer.close();
			}
			
	
		
		}
		for(int i = 300 ;i <430 ;i++){
			ArrayList<Object> list = result.get(i);
			if(isContainNumber(list.get(1).toString())){
				String scoreRecordId = MyLong.getRandStr(12)+""+i*2;
				String scoreDeatilId = MyLong.getRandStr(13)+""+i*1;
				String putId = MyLong.getRandStr(11)+""+i*2;
				String time ="";
				if(i%2 ==0){
					time = date+" 1"+MyLong.getRandStr(3)+":4"+MyLong.getRandStr(3)+":31";
				}else if(i%3 ==0 && i%2 !=0){
					time = date+" 1"+MyLong.getRandStr(3)+":3"+MyLong.getRandStr(3)+":21";
				}else{
					time = date+" 1"+MyLong.getRandStr(3)+":2"+MyLong.getRandStr(3)+":54";
				}
				String staffDetailId = list.get(0).toString();
				String householdId = list.get(2).toString();
				String putValue = "";
				if(i%2 ==0){
					putValue = 2+"."+MyLong.getRandStr(3);
				}else if(i%3 ==0 && i%2 !=0){
					putValue = 1+"."+MyLong.getRandStr(3);
				}else{
					putValue = 0+"."+MyLong.getRandStr(3);
				}
				
				String scoreValue = Double.parseDouble(putValue)*2+"";
				String fileName = "../wuka"+fileDate+".sql";
				File file1 = new File(fileName);
				if(!file1.exists()){
					file1.createNewFile();
				}
				FileWriter writer = new FileWriter(fileName,true);
				String str1 ="INSERT INTO `ljfl_score_record201712` VALUES ('"+scoreRecordId+"', '"+time+"', '"+time+"', '0', '0', null, null, '4eaf9423eb0043648e502134586b088a', '"+scoreDeatilId+"', '"+scoreValue+"', '"+scoreValue+"', 'swingCard', null, '"+time+"', '"+staffDetailId+"', '"+time+"', null, null);\r";
				writer.write(str1);
				String str2 = "INSERT INTO `ljfl_score_detail201712` VALUES ('"+scoreDeatilId+"', '"+time+"', '"+time+"', '0', '0', null, null, '4eaf9423eb0043648e502134586b088a', null, '"+scoreValue+"', '"+time+"', 'adfdc378f01f47019e79d6395263a95b', '"+staffDetailId+"', '"+time+"', '50f1a51d6e984ef98c4855f4984125cf');\r";
				writer.write(str2);
				String str3 = "INSERT INTO `ljfl_put_record201712` VALUES ('"+putId+"', '"+time+"', '"+time+"', '0', '0', null, null, '4eaf9423eb0043648e502134586b088a', '"+time+"', null, '39.889169', null, '116.458794', '1', '2de111cc004441b280283afc5180989e', null, '0', '"+time+"', '"+putValue+"', 'eaae774e1599445f9570532b7870d686', '"+householdId+"', '"+staffDetailId+"', null);\r";
				writer.write(str3);
				writer.write("\r");
				writer.flush();
				writer.close();
			}
			
	
		
		}
		
	}
	public static boolean isContainNumber(String company) {

        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(company);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
