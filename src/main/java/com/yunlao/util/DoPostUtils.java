package com.yunlao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * 调用webService 远程服务 接口
 * 
 * @author yunluo
 *
 */
public class DoPostUtils {
	public static void main(String[] args) {
	     Map<String, String> map =new HashMap<String, String>();
 
//	 	try {
//	     String js = "{\"tenantId\":\"4eaf9423eb0043648e502134586b088a\"}"; 
//			map.put("parameters", URLEncoder.encode(js, "utf-8"));
//	 		String response1 =  doPost("http://118.178.182.41/80/cloud/ljfl/api/np/v1/houses/loadHousesByFilter.smvc",map,"utf-8");
//	     System.out.println(response1);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	 	
//	 	String js = "{\"tenantId\":\"4eaf9423eb0043648e502134586b088a\",\"carId\":\"19416a2eaf284df189d12778c570d9da\",\"startTime\":\"2017-07-10 05:00:30\",\"endTime\":\"2017-07-10 06:57:39\"}";
//		map.put("parameters", URLEncoder.encode(js, "utf-8"));
	 	try {
	 		String js = "{\"tenantId\":\"4eaf9423eb0043648e502134586b088a\"}";
			map.put("parameters", URLEncoder.encode(js, "utf-8"));
	 		String response1 =  doPost("http://118.178.182.41:80/cloud/ljfl/api/np/v1/carRealTimeStatusRest/loadCarList",map,"utf-8");
	     System.out.println(response1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}



//	     try {
//        	 String str = "{\"userId\":\"977a349b3ff0459fa478cd4817ee427d\"}";
//				map.put("parameters", URLEncoder.encode(str, "utf-8"));
//	 		String response1 =  doPost("http://118.178.184.24:9397/cloud/management/rest/np/function/getFunctionsByUsreIdAndSystem.read",map,"utf-8");
//	     System.out.println(response1);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	     
	    
	}

	
	public static String doPost(String url, Map<String, String> params, String charset) {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		if (params != null) {
			Set<String> keySet = params.keySet();
			NameValuePair[] param = new NameValuePair[keySet.size()];
			int i = 0;
			for (String key : keySet) {
				param[i] = new NameValuePair(key, params.get(key));
				i++;
			}
			method.setRequestBody(param);
		}
		InputStream responseBodyStream = null;
		InputStreamReader streamReader = null;
		BufferedReader reader = null;
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				responseBodyStream = method.getResponseBodyAsStream();
				streamReader = new InputStreamReader(responseBodyStream, charset);
				reader = new BufferedReader(streamReader);
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
			}
		} catch (IOException e) {
			System.out.println("ִ��HTTP Post����" + url + "ʱ�������쳣��");
		} finally {
			try {
			
				responseBodyStream.close();
				streamReader.close();
				reader.close();
			} catch (IOException e) {
				System.out.println("ִ��HTTP Post����" + url + "ʱ�������쳣���ر����쳣��");
				e.printStackTrace();
			}
			method.releaseConnection();
		}
		return response.toString();
	}
	

	
}
