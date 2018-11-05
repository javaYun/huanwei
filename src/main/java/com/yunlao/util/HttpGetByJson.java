package com.yunlao.util;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;

/** 
 * @author QiaoJiafei 
 * @version 创建时间：2015年11月4日 下午1:55:45 
 * 类说明 
 */
public class HttpGetByJson {
      public static void main(String args[]) throws Exception{
          final String CONTENT_TYPE_TEXT_JSON = "text/json";
          DefaultHttpClient client = new DefaultHttpClient(
                  new PoolingClientConnectionManager());
          
          String url = "http://192.168.1.207:18088/cloud/jcss/rest/np/car/getCarsByCondiction.read";
        String js = "{\"tenantId\":\"4eaf9423eb0043648e502134586b088a\",\"typeCode\":\"car\"}";
//        String js = "{\"tenantId\":\"4eaf9423eb0043648e502134586b088a\"}";  
        HttpPost httpPost = new HttpPost(url);       
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
               
        StringEntity se = new StringEntity(js);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);

        httpPost.setEntity(se);
        
        CloseableHttpResponse response2 = null;
        
        response2 = client.execute(httpPost);
        HttpEntity entity2 = null;
        entity2 = response2.getEntity();
        String s2 = EntityUtils.toString(entity2, "UTF-8");
        System.out.println(s2);
      }
      

}