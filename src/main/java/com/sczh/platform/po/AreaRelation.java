package com.sczh.platform.po;

public class AreaRelation {
    private String arearelationid;

    private String id;

    private String name;

    private String pId;

    private String onclick;

    private String isarea;
    
    private String state;

    private String iconcls;

    public String getArearelationid() {
        return arearelationid;
    }

    public void setArearelationid(String arearelationid) {
        this.arearelationid = arearelationid == null ? null : arearelationid.trim();
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

 

    public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick == null ? null : onclick.trim();
    }

    public String getIsarea() {
        return isarea;
    }

    public void setIsarea(String isarea) {
        this.isarea = isarea == null ? null : isarea.trim();
    }

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconcls() {
		return iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	@Override
	public String toString() {
		return "AreaRelation [arearelationid=" + arearelationid + ", id=" + id
				+ ", name=" + name + ", pId=" + pId + ", onclick=" + onclick
				+ ", isarea=" + isarea + ", state=" + state + ", iconcls="
				+ iconcls + "]";
	}
    
    
}