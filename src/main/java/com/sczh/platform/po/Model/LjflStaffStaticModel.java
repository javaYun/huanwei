package com.sczh.platform.po.Model;

public class LjflStaffStaticModel implements Comparable<LjflStaffStaticModel>{
	
	private String id;
	private String staffId;
	private String staffName;
	private int xuhao;
	private int persontime;
	private Double chuyuzhongliang;
	private Double feipin;
	private Double dajian2;
	private Double jianzhu;
	private Double feijiujinshu;
	private Double youhai;
	private Double feijiusiliao;
	private Double chuyu;
	private Double canchu;
	private Double yuanlin;
	private Double qita;
	
	public LjflStaffStaticModel() {
	
		this.xuhao = 0;
		this.persontime = 0;
		this.chuyuzhongliang = 0.0;
		this.feipin = 0.0;
		this.dajian2 = 0.0;
		this.jianzhu = 0.0;
		this.feijiujinshu = 0.0;
		this.youhai = 0.0;
		this.feijiusiliao = 0.0;
		this.chuyu = 0.0;
		this.canchu = 0.0;
		this.yuanlin = 0.0;
		this.qita = 0.0;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public int getXuhao() {
		return xuhao;
	}
	public void setXuhao(int xuhao) {
		this.xuhao = xuhao;
	}
	public int getPersontime() {
		return persontime;
	}
	public void setPersontime(int persontime) {
		this.persontime = persontime;
	}
	public Double getChuyuzhongliang() {
		return chuyuzhongliang;
	}
	public void setChuyuzhongliang(Double chuyuzhongliang) {
		this.chuyuzhongliang = chuyuzhongliang;
	}
	public Double getFeipin() {
		return feipin;
	}
	public void setFeipin(Double feipin) {
		this.feipin = feipin;
	}
	public Double getDajian2() {
		return dajian2;
	}
	public void setDajian2(Double dajian2) {
		this.dajian2 = dajian2;
	}
	public Double getJianzhu() {
		return jianzhu;
	}
	public void setJianzhu(Double jianzhu) {
		this.jianzhu = jianzhu;
	}
	public Double getFeijiujinshu() {
		return feijiujinshu;
	}
	public void setFeijiujinshu(Double feijiujinshu) {
		this.feijiujinshu = feijiujinshu;
	}
	public Double getYouhai() {
		return youhai;
	}
	public void setYouhai(Double youhai) {
		this.youhai = youhai;
	}
	public Double getFeijiusiliao() {
		return feijiusiliao;
	}
	public void setFeijiusiliao(Double feijiusiliao) {
		this.feijiusiliao = feijiusiliao;
	}
	public Double getChuyu() {
		return chuyu;
	}
	public void setChuyu(Double chuyu) {
		this.chuyu = chuyu;
	}
	public Double getCanchu() {
		return canchu;
	}
	public void setCanchu(Double canchu) {
		this.canchu = canchu;
	}
	public Double getYuanlin() {
		return yuanlin;
	}
	public void setYuanlin(Double yuanlin) {
		this.yuanlin = yuanlin;
	}
	public Double getQita() {
		return qita;
	}
	public void setQita(Double qita) {
		this.qita = qita;
	}
	@Override
	public int compareTo(LjflStaffStaticModel arg0) {
		double thisworkNum =this.getXuhao();
		 double oworkNum = arg0.getXuhao();
		 if(thisworkNum>oworkNum){
			 return 1;
		 }else{
				return -1;
		 }
	
	}
	
}
