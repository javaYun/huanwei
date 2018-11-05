package com.sczh.platform.po;

public class Depts {
	private String deptId;
	private String deptName;
	private String describe;
	private String variety;
	private String license;
	
	
	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * @param describe the describe to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * @return the variety
	 */
	public String getVariety() {
		return variety;
	}
	/**
	 * @param variety the variety to set
	 */
	public void setVariety(String variety) {
		this.variety = variety;
	}
	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * @param license the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	
	public Depts() {
		super();
	}
	
	public Depts(String deptId, String deptName, String describe, String variety, String license) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.describe = describe;
		this.variety = variety;
		this.license = license;
	}
	
	public Depts(String deptName, String describe, String variety, String license) {
		super();
		this.deptName = deptName;
		this.describe = describe;
		this.variety = variety;
		this.license = license;
	}
	
}
