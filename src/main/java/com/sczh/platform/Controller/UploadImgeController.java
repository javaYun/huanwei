package com.sczh.platform.Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadImgeController {
	/**
	 * 上传图片文件，并返回url或者Id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("uploadImg")
	   public void uploadImg(MultipartHttpServletRequest request,HttpServletResponse response) throws IOException{
			 request.setCharacterEncoding("UTF-8");
		        response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        SimpleDateFormat simpleFormat = new SimpleDateFormat("MMddHHmmsss");
		        String generationfileName = simpleFormat.format(new Date())+new Random().nextInt(1000);
		        //保存路径
		       // String fileName = System.currentTimeMillis()+file.getOriginalFilename();//上传的文件名
		        String savePath2 = "E:/tomcatPhoto/photo";
		   	 String savePath = request.getSession().getServletContext().getRealPath("/resources/upload");  //文件上传路径
		     System.out.println("savePath::::::::::"+savePath); 

		        String updateP = request.getParameter("updateP");
		        String fileNameSuffix=null;
		        String fileName= null;
		        if(null!=updateP&&!"".equals(updateP)){
		            try {
		                MultipartFile mf = request.getFile(updateP);
		                fileName=mf.getOriginalFilename();
		                if(null!=mf&&!"".equals(mf)){
		                    fileNameSuffix=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		                    String uploadfileName = System.currentTimeMillis()+fileName;//上传的文件名
		                    FileUtils.copyInputStreamToFile(mf.getInputStream(), new File(savePath,uploadfileName   
			                         ));  
		                    // SaveFileFromInputStream(mf.getInputStream(),savePath,generationfileName+"."+fileNameSuffix);
		                   // out.write("{'fileUrl':'"+savePath+"','"+"'fileName:'"+uploadfileName+"','}");
		                    out.write(uploadfileName);
		                }
		            } catch (Exception e) {
		                out.write("{'state':'1'}");
		                e.printStackTrace();
		            }
		        }    
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
