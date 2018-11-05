package com.sczh;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable{

	private String name;                                 //名字  
    private int year;                                    //年龄  
    private String city;                                 //城市  
    private Date birth;                                  //生日  
      
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
        this.year=year;  
        this.city=city;  
    }  
    public int getYear() {  
        return year;  
    }  
    public void setYear(int year) {  
        this.year = year;  
    }  
    public String getCity() {  
        return city;  
    }  
    public void setCity(String city) {  
        this.city = city;  
    }  
      
      
    public Date getBirth() {  
        return birth;  
    }  
    public void setBirth(Date birth) {  
        this.birth = birth;  
    }  
    /*  
     * (non-Javadoc)  
     * @see java.lang.Object#toString()  
     * 重写toString，不然序列化之后显示的是内存地址  
     */  
    public String toString(){  
        return this.name+" "+this.year+" "+this.city+" "+this.birth.toString();  
    }  
}
