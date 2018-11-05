package com.yunlao.util.excel;

public class MainTotalDay {
	
	public static void run(){
		for (int i = 10; i < 26; i++) {
			String no = "";
			if(i < 10){
				no  = 0 +""+i;
			}else{
				no = i+"";
			}
			String id = MyLong.getRandStr(12+(i%2)*2);
			int f1  = Integer.parseInt(MyLong.getRandStr(3));
			int f2  = Integer.parseInt(MyLong.getRandStr(3));
			int f3  = Integer.parseInt(MyLong.getRandStr(3));
			int f4  = Integer.parseInt(MyLong.getRandStr(4));
			int f5  = Integer.parseInt(MyLong.getRandStr(4));
			int f6  = Integer.parseInt(MyLong.getRandStr(4));
			int num = f1+f2+f3+f4+f5+f6;
			String str = "insert into ljfl_put_record_day_total values ('"+id+"','2017-12-"+no+" 21:04:58','2017-12-"+no+" 21:04:58','0','0',null,null,'4eaf9423eb0043648e502134586b088a','23a8014863094db5bc86253eb188b98a','������','1bfe2e0b22054968991723ec067ff956','���Ų�����','{\"00\":0.0,\"01\":0.0,\"02\":0.0,\"03\":0.0,\"04\":0.0,\"05\":0.0,\"06\":0.0,\"07\":0.0,\"08\":0.0,\"09\":0.0,\"10\":"+f1+".0,\"11\":"+f2+".0,\"12\":"+f4+".0,\"13\":"+f4+".0,\"14\":"+f5+".0,\"15\":"+f6+".0,\"16\":0.0,\"17\":1,\"18\":0.0,\"19\":0.0,\"20\":0.0,\"21\":0.0,\"22\":0.0,\"23\":0.0}','b139f805180041cab740fa74f1cee772','���Ų�����','cef0eaeba7bc4d83b6672f84f1862bf7','���Ľֵ�','2017-12-"+no+"','�˴�','"+num+"',null);";
		System.out.println(str);	
		}
	}
	public static void main(String[] args) {
		run();
	}

}
