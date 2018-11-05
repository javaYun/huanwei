package com.yunlao.uti.timerl;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;  
  @Component
public class ContextListener implements ServletContextListener{  
  
    public ContextListener() {  
    }  
      
     private java.util.Timer timer = null;    
       
    /** 
     * 初始化定时器 
     * web 程序运行时候自动加载   
     */  
    @Override  
    public void contextInitialized(ServletContextEvent arg0) {  
    	timer = new java.util.Timer(true);
    	arg0.getServletContext().log("定时器已启动"); 
        timer.schedule(new StatisticsTask(arg0.getServletContext()), 0, 10*1*1000);//每隔10秒
      
        arg0.getServletContext().log("已经添加任务调度表");    
          
    }  
  
    /** 
     * 销毁 
     */  
    @Override  
    public void contextDestroyed(ServletContextEvent arg0){  
  
        timer.cancel();  
        arg0.getServletContext().log("定时器销毁");  
    }  
}