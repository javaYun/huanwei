package com.yunlao.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	
	public static Boolean uploadFile(HttpServletRequest request, MultipartFile file) {  
        System.out.println("开始");  
        //文件上传路径
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if (!targetFile.exists()) {
            targetFile.mkdirs();  
        }  
        // 保存  
        try {  
            file.transferTo(targetFile);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    
   }  
	
	public static Boolean uploadContracts(HttpServletRequest request, MultipartFile file) {  
        System.out.println("开始");  
        //文件上传路径
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if (!targetFile.exists()) {
            targetFile.mkdirs();  
        }  
        // 保存  
        try {  
            file.transferTo(targetFile);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    
   }  
	
}
