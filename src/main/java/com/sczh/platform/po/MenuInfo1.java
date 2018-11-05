package com.sczh.platform.po;

public class MenuInfo1 {
	 private int mid;

	    private String id;

	    private String name;

	    private String pId;

	    private String onclick;
	    
	    private String code;

	    public int getMid() {
	        return mid;
	    }

	    public void setMid(int mid) {
	        this.mid = mid;
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
	    
	    
	    
	    public String getPID() {
			return pId;
		}

		public void setPID(String pId) {
			this.pId = pId == null ? null : pId.trim();
		}

		public String getOnclick() {
	        return onclick;
	    }

	    public void setOnclick(String onclick) {
	        this.onclick = onclick == null ? null : onclick.trim();
	    }
	    
	    public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return "MenuInfo [mid=" + mid + ", id=" + id + ", name=" + name
					+ ", pId=" + pId + ", onclick=" + onclick + ", code=" + code
					+ "]";
		}

    
    
}