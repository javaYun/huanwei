package com.sczh.platform.po;

import java.util.Date;

public class ContractInfo {
    private String id;

    private String name;

    private Date createtime;

    private String contracturl;

    private Date lastchangtime;

    private String uploadname;
    
    private String type;
    
    private String originalname;

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContracturl() {
        return contracturl;
    }

    public void setContracturl(String contracturl) {
        this.contracturl = contracturl == null ? null : contracturl.trim();
    }

    public Date getLastchangtime() {
        return lastchangtime;
    }

    public void setLastchangtime(Date lastchangtime) {
        this.lastchangtime = lastchangtime;
    }

    public String getUploadname() {
        return uploadname;
    }

    public void setUploadname(String uploadname) {
        this.uploadname = uploadname == null ? null : uploadname.trim();
    }

    public String getType() {
    	return type;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public String getOriginalname() {
    	return originalname;
    }
    
    public void setOriginalname(String originalname) {
    	this.originalname = originalname;
    }
    
	@Override
	public String toString() {
		return "ContractInfo [id=" + id + ", name=" + name + ", createtime="
				+ createtime + ", contracturl=" + contracturl
				+ ", lastchangtime=" + lastchangtime + ", uploadname="
				+ uploadname + "]";
	}
    
}