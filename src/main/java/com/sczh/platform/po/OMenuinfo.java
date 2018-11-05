package com.sczh.platform.po;

public class OMenuinfo {
    private Integer oid;

    private String id;

    private String name;

    private String pId;

    public String getPID() {
		return pId;
	}

	public void setPID(String pId) {
		this.pId = pId;
	}

	private String onclick;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

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

    

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick == null ? null : onclick.trim();
    }

	@Override
	public String toString() {
		return "OMenuinfo [oid=" + oid + ", id=" + id + ", name=" + name
				+ ", pId=" + pId + ", onclick=" + onclick + "]";
	}
    
    
    
}