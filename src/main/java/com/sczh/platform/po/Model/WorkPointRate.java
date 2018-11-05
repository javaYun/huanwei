package com.sczh.platform.po.Model;

/**
 * 工作站点工作频率model
 * @author yunluo
 *
 */
public class WorkPointRate implements Comparable<WorkPointRate>{
	
	
	private String workTimeRate;//工作时间
	private String pointId;// 工作站点id
	private int workNum;
	private String pointName;//站点名称
	private String workTimeone;//每次工作时间
	private boolean isWorking;//true 表示当前尚在工作  false 表示你已经离开
	
	public boolean isWorking() {
		return isWorking;
	}
	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
	public String getWorkTimeone() {
		return workTimeone;
	}
	public void setWorkTimeone(String workTimeone) {
		this.workTimeone = workTimeone;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getPointId() {
		return pointId;
	}
	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
	public int getWorkNum() {
		return workNum;
	}
	public void setWorkNum(int workNum) {
		this.workNum = workNum;
	}
	public String getWorkTimeRate() {
		return workTimeRate;
	}
	public void setWorkTimeRate(String workTimeRate) {
		this.workTimeRate = workTimeRate;
	}
	
	@Override
	public int compareTo(WorkPointRate arg0) {
		 double thisworkNum =this.getWorkNum();
		 double oworkNum = arg0.getWorkNum();
		 String thisworkTimeRate =this.getWorkTimeRate();
		 String arg0workTimeRate =arg0.getWorkTimeRate();
		 if(thisworkNum>oworkNum){
			 return -1;
		 }else if(thisworkNum==oworkNum){
			 if(thisworkTimeRate.compareTo(arg0workTimeRate)>0){
				 return -1;
			 }else if(thisworkTimeRate.compareTo(arg0workTimeRate)==0){
				 return 0;
			 }else{
				 return 1;
			 }
			
		 }else{
			 return 1;
		 }
	}


}
