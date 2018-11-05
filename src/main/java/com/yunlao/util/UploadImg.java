package com.yunlao.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadImg {
	/**
	 * 上传图片文件，并返回url或者Id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
    public String uploadImg(MultipartHttpServletRequest request,HttpServletResponse response) throws IOException{
		String str ="";
    	request.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        //保存路径
	       // String fileName = System.currentTimeMillis()+file.getOriginalFilename();//上传的文件名
	   	 String savePath = request.getSession().getServletContext().getRealPath("/resources/upload");  //文件上传路径
	     System.out.println("savePath::::::::::"+savePath); 

	        String updateP = request.getParameter("updateP");
	        String fileName= null;
	        if(null!=updateP&&!"".equals(updateP)){
	            try {
	                MultipartFile mf = request.getFile(updateP);
	                fileName=mf.getOriginalFilename();
	                if(null!=mf&&!"".equals(mf)){
	                    String uploadfileName = System.currentTimeMillis()+fileName;//上传的文件名
	                    FileUtils.copyInputStreamToFile(mf.getInputStream(), new File(savePath,uploadfileName   
		                         )); 
	                    str = uploadfileName;
	                    out.write(uploadfileName);
	                }
	            } catch (Exception e) {
	                out.write("{'state':'1'}");
	                e.printStackTrace();
	            }
	        }    
	        return str;
    }
    public static void main(String[] args) {
//    	<!-- 点击图片，打开文件选择器，确定，上传。(这是网络上的一个图片) -->  
//    	<img id="upload"  alt="" style="width: 100px; height: 100px" src="${user.photourl}">  
//    	 <img id="imgUrl"  alt="" style="width: 100px; display: none;height: 100px" src="<%=basePath%>resources/upload/">  
//    	  
//    	<!-- 隐藏file标签 -->  
//    	<input id="uploadc" style="display: none" type="file" name="uploadc" value="" onclick="changeImg()"> 
//    	function changeImg(){
//    		 $('#uploadc').on('change', function() { 
//    	  	   debugger;
//    	  	   $.ajaxFileUpload({
//    		         //跟具updateP得到不同的上传文本的ID
//    		                 url:"/BMS/user/uploadImg?updateP="+'uploadc',             //需要链接到服务器地址
//    		                 secureuri:false,
//    		                 fileElementId:''+'uploadc'+'',                         //文件选择框的id属性（必须）
//    		                 dataType: 'text',  
//    		                 success: function (data, status){    
//    		                	$("#uploadc").val($("#imgUrl")[0].src+data);
//    		                	$("#upload").attr("src",$("#imgUrl")[0].src+data);
//    		                  },
//    		                  error: function (data, status, e){  
//    		                      alert('上传失败');
//    		                  }
//    		                }
//    		             );
//    	  	   
//    	  	   
//    	     });  
//    	}
//    	$(function(){  
//    	    //点击打开文件选择器  
//    	    $("#upload").on('click', function() {  
//    	        $('#uploadc').click();  
//    	    });  
//    	    });  
	}
}
