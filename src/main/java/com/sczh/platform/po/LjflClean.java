package com.sczh.platform.po;

import java.util.Date;

public class LjflClean {
    private Integer id;

    private String cleancangroupid;

    private String cleancangroup;

    private String cleancanno;

    private String cleancanname;

    private String cleanhousename;

    private String cleantransfername;

    private String cleanname;

    private String cleantrashtype;

    private String cleancondition;

    private Date cleantime;

    private String cleancount;

    private String string1;

    private String string2;

    private String string3;

    private String string4;

    private String string5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCleancangroupid() {
        return cleancangroupid;
    }

    public void setCleancangroupid(String cleancangroupid) {
        this.cleancangroupid = cleancangroupid == null ? null : cleancangroupid.trim();
    }

    public String getCleancangroup() {
        return cleancangroup;
    }

    public void setCleancangroup(String cleancangroup) {
        this.cleancangroup = cleancangroup == null ? null : cleancangroup.trim();
    }

    public String getCleancanno() {
        return cleancanno;
    }

    public void setCleancanno(String cleancanno) {
        this.cleancanno = cleancanno == null ? null : cleancanno.trim();
    }

    public String getCleancanname() {
        return cleancanname;
    }

    public void setCleancanname(String cleancanname) {
        this.cleancanname = cleancanname == null ? null : cleancanname.trim();
    }

    public String getCleanhousename() {
        return cleanhousename;
    }

    public void setCleanhousename(String cleanhousename) {
        this.cleanhousename = cleanhousename == null ? null : cleanhousename.trim();
    }

    public String getCleantransfername() {
        return cleantransfername;
    }

    public void setCleantransfername(String cleantransfername) {
        this.cleantransfername = cleantransfername == null ? null : cleantransfername.trim();
    }

    public String getCleanname() {
        return cleanname;
    }

    public void setCleanname(String cleanname) {
        this.cleanname = cleanname == null ? null : cleanname.trim();
    }

    public String getCleantrashtype() {
        return cleantrashtype;
    }

    public void setCleantrashtype(String cleantrashtype) {
        this.cleantrashtype = cleantrashtype == null ? null : cleantrashtype.trim();
    }

    public String getCleancondition() {
        return cleancondition;
    }

    public void setCleancondition(String cleancondition) {
        this.cleancondition = cleancondition == null ? null : cleancondition.trim();
    }

    public Date getCleantime() {
        return cleantime;
    }

    public void setCleantime(Date cleantime) {
        this.cleantime = cleantime;
    }

    public String getCleancount() {
        return cleancount;
    }

    public void setCleancount(String cleancount) {
        this.cleancount = cleancount == null ? null : cleancount.trim();
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1 == null ? null : string1.trim();
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2 == null ? null : string2.trim();
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3 == null ? null : string3.trim();
    }

    public String getString4() {
        return string4;
    }

    public void setString4(String string4) {
        this.string4 = string4 == null ? null : string4.trim();
    }

    public String getString5() {
        return string5;
    }

    public void setString5(String string5) {
        this.string5 = string5 == null ? null : string5.trim();
    }

	@Override
	public String toString() {
		return "LjflClean [id=" + id + ", cleancangroupid=" + cleancangroupid
				+ ", cleancangroup=" + cleancangroup + ", cleancanno="
				+ cleancanno + ", cleancanname=" + cleancanname
				+ ", cleanhousename=" + cleanhousename + ", cleantransfername="
				+ cleantransfername + ", cleanname=" + cleanname
				+ ", cleantrashtype=" + cleantrashtype + ", cleancondition="
				+ cleancondition + ", cleantime=" + cleantime + ", cleancount="
				+ cleancount + ", string1=" + string1 + ", string2=" + string2
				+ ", string3=" + string3 + ", string4=" + string4
				+ ", string5=" + string5 + "]";
	}
    
    
    
}