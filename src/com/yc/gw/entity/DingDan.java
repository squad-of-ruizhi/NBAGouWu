package com.yc.gw.entity;

public class DingDan {
     private   int orderid;
     private   String odate;
     private  int oamount;
     private String state;
     private String addr;
     private  int spId;
     private int usid;
     private String Dstatus;
     
     
    
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getOdate() {
		return odate;
	}
	public String getDstatus() {
		return Dstatus;
	}
	public void setDstatus(String dstatus) {
		Dstatus = dstatus;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public int getOamount() {
		return oamount;
	}
	public void setOamount(int oamount) {
		this.oamount = oamount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public int getUsid() {
		return usid;
	}
	public void setUsid(int usid) {
		this.usid = usid;
	}
	
      
	 @Override
	public String toString() {
		return "DingDan [orderid=" + orderid + ", odate=" + odate + ", oamount=" + oamount + ", state=" + state
				+ ", addr=" + addr + ", spId=" + spId + ", usid=" + usid + ", Dstatus=" + Dstatus + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + oamount;
		result = prime * result + ((odate == null) ? 0 : odate.hashCode());
		result = prime * result + orderid;
		result = prime * result + spId;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + usid;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DingDan other = (DingDan) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (oamount != other.oamount)
			return false;
		if (odate == null) {
			if (other.odate != null)
				return false;
		} else if (!odate.equals(other.odate))
			return false;
		if (orderid != other.orderid)
			return false;
		if (spId != other.spId)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (usid != other.usid)
			return false;
		return true;
	}
	
	public DingDan() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
	 
      
}
