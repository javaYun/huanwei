package com.sczh.platform.po;

import java.math.BigDecimal;

public class KitchenInfo {
    private String id;

    private String cardno;

    private String name;

    private String phone;

    private String address;

    private String updatetime;

    private String integral;

    private String money;

    private String principal;

    private String principalphone;
    
    private String deviceno;
    
    private String string1;

    private String string2;

    private String string3;

    private String string4;

    private String string5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral == null ? null : integral.trim();
    }

  



	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getPrincipalphone() {
        return principalphone;
    }

    public void setPrincipalphone(String principalphone) {
        this.principalphone = principalphone == null ? null : principalphone.trim();
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

    
    
	public String getDeviceno() {
		return deviceno;
	}

	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}

	@Override
	public String toString() {
		return "KitchenInfo [id=" + id + ", cardno=" + cardno + ", name="
				+ name + ", phone=" + phone + ", address=" + address
				+ ", updatetime=" + updatetime + ", integral=" + integral
				+ ", money=" + money + ", principal=" + principal
				+ ", principalphone=" + principalphone + ", string1=" + string1
				+ ", string2=" + string2 + ", string3=" + string3
				+ ", string4=" + string4 + ", string5=" + string5 + "]";
	}
    
   
    
}