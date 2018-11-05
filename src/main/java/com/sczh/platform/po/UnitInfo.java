package com.sczh.platform.po;

import java.util.Date;

public class UnitInfo {
    private String unitcode;

    private String unitname;

    private String unitdepict;

    private String address;

    private String corporate;

    private String phone;

    private String unitadmin;

    private Date setuptime;

    private String state;

    private Date changetime;

    private String areaid;

    private String areaname;
    
    private String unittype;
    

    public String getUnittype() {
		return unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

	public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode == null ? null : unitcode.trim();
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname == null ? null : unitname.trim();
    }

    public String getUnitdepict() {
        return unitdepict;
    }

    public void setUnitdepict(String unitdepict) {
        this.unitdepict = unitdepict == null ? null : unitdepict.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate == null ? null : corporate.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUnitadmin() {
        return unitadmin;
    }

    public void setUnitadmin(String unitadmin) {
        this.unitadmin = unitadmin == null ? null : unitadmin.trim();
    }

    public Date getSetuptime() {
        return setuptime;
    }

    public void setSetuptime(Date setuptime) {
        this.setuptime = setuptime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getChangetime() {
        return changetime;
    }

    public void setChangetime(Date changetime) {
        this.changetime = changetime;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

	@Override
	public String toString() {
		return "UnitInfo [unitcode=" + unitcode + ", unitname=" + unitname
				+ ", unitdepict=" + unitdepict + ", address=" + address
				+ ", corporate=" + corporate + ", phone=" + phone
				+ ", unitadmin=" + unitadmin + ", setuptime=" + setuptime
				+ ", state=" + state + ", changetime=" + changetime
				+ ", areaid=" + areaid + ", areaname=" + areaname
				+ ", unittype=" + unittype + "]";
	}
    
    
    
}