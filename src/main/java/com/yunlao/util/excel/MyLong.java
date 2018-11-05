package com.yunlao.util.excel;

import java.util.Date;

public class MyLong {
	 public static final long MIN_VALUE = 0x8000000000000000L;

	    public static final long MAX_VALUE = 0x7fffffffffffffffL;

	    final static char[] digits = { '0', '1', '2', '3', '4',
	            '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
	            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
	            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
	            'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
	            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
	            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
	            'X', 'Y', 'Z', '-', '_' };

	    private static String toUnsignedString(long i, int shift) {
	        char[] buf = new char[64];
	        int charPos = 64;
	        int radix = 1 << shift;
	        long mask = radix - 1;
	        do {
	            buf[--charPos] = digits[(int) (i & mask)];
	            i >>>= shift;
	        } while (i != 0);
	        return new String(buf, charPos, (64 - charPos));
	    }
	    
	     
	     // j涓�2鐨勬鏂癸紝濡傝浆鎴�16杩涘埗灏辨槸4锛�32杩涘埗灏辨槸5...
	     public static String getRand(long i,int j){
	         return toUnsignedString(i, j); 
	     }
	     
	     // 闅忔満鐮侊紜鏃堕棿鎴筹紜闅忔満鐮佺殑鐢熸垚
	     public static Long getRand(){
	         String str1,str2,str3;
	         str1=getRandStr(2);
	         str3=getRandStr(3);
	         str2=(new Date()).getTime()+"";
	         //System.out.println(str1+str2+str3);
	         return Long.parseLong(str1+str2+str3);
	     }
	     
	     // 涓婚敭鐢熸垚
	     public static String getKey(){
	         return getRand(getRand(),6);
	     }
	     
	     //    鐢熸垚鎸囧畾闀垮害鐨勯殢鏈轰覆
	     public static String getRandStr(Integer length){
	    	 length = length-2;
	         String str="";
	         while(str.length()!=length){
	             str=(Math.random()+"").substring(2,2+length);
	         }
	         return str;
	     }
	     public static void main(String[] args) {
	    	 String time = getRandStr(18)+getRandStr(4);
 			System.out.println(time);
//	    	 for (int i = 0; i < 1; i++) {
//	    			String time = "2017-12-22 1"+MyLong.getRandStr(1)+":3"+MyLong.getRandStr(1)+":31";
//	    			System.out.println(time);
//	    		 System.out.println(getRandStr(12)+""+i*2);
//			}
	    	 
	    
	    	 
		}
}
