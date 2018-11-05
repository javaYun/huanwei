package com.yunlao.util;

import java.security.MessageDigest;

public class MD5andKL {

	// MD5加码。32位
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		// String s = new String(inStr);
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解密
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();    
		  for (int i = 0; i < a.length; i++) {    
		   a[i] = (char) (a[i] ^ 't');    
		  }    
		  String k = new String(a);    
		  return k; 
	}

	/**
	 * 解密
	 * @param hexString
	 * @return
	 */
	 public static byte[] hexStringToBytes(String hexString) {
         if (hexString == null || hexString.equals("")) {
             return null;
         }
         hexString = hexString.toUpperCase();
         int length = hexString.length() / 2;
         char[] hexChars = hexString.toCharArray();
         byte[] d = new byte[length];
         for (int i = 0; i < length; i++) {
             int pos = i * 2;
             d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
         }
         return d;
     }
	 
	 private static byte charToByte(char c) {
         return (byte) "0123456789abcdef".indexOf(c);
     }
	 
	 /** 
	     * 加密解密算法 执行一次加密，两次解密 
	     */   
	    public static String convertMD5(String inStr){  

	        char[] a = inStr.toCharArray();  
	        for (int i = 0; i < a.length; i++){  
	            a[i] = (char) (a[i] ^ 't');  
	        }  
	        String s = new String(a);  
	        return s;  

	    }  
	
	// 测试主函数
	public static void main(String args[]) {
		String s = new String("北京嘉宝物业管理有限公司123456");
//		4297f44b13955235245b2497399d7a93
		System.out.println("原始：" + "jinsong123456");
		System.out.println("MD5后：" + MD5(s));
		System.out.println("MD5后再加密：" + KL(MD5(s)));
		System.out.println("解密为MD5后的：" + JM(s));
		System.out.println("解密后："+hexStringToBytes(s).toString());
		
		//System.out.println("加密的：" + convertMD5(s));  
		System.out.println("解密的：" + convertMD5(convertMD5(s)));  
	}
}


