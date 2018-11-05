package com.sczh;

import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
  
public class MainApp {

	 public static void main(String[] args){  
	          
	        Person p1=new Person();  
	        p1.setName("张斌");  
	        p1.setYear(24);;  
	        p1.setCity("江西");  
	        p1.setBirth(new Date(94,1,2));  
	          
	          
	        Person p2=new Person();  
	        p2.setName("福国");  
	        p2.setYear(30);  
	        p2.setCity("吉林");  
	        p2.setBirth(new Date(95,4,23));  
	          
	        Person p3=new Person();  
	        p3.setName("羿赫");  
	        p3.setYear(20);  
	        p3.setCity("海南");  
	        p3.setBirth(new Date(93,9,29));  
	          
	        DBHelper db=new DBHelper();  
	        List<Person> slist=new ArrayList<Person>();  
	        slist.add(p1);  
	        slist.add(p2);  
	        slist.add(p3);  
	          
	        db.savePerson(slist);  
	          
	        List<Person> glist=db.getPerson();  
	          
	        for(int i=0;i<glist.size();i++){  
	            System.out.println(glist.get(i).toString());  
	        }  
	    }  
}
